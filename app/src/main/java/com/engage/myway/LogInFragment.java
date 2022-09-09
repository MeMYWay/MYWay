package com.engage.myway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.engage.myway.api.API;
import com.engage.myway.api.ApiClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LogInFragment extends Fragment {


    private EditText emailId,password;
    private Button logInBtn;
    private TextView moveToSignUp,forgotPassword;
    String TAG="ME";
    private RadioButton rememberMe;
    SharedPreferences sharedPreferences;


    public LogInFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_log_in, container, false);
        emailId=view.findViewById(R.id.emailIdLogIn);
        password=view.findViewById(R.id.password_loginIn);
        logInBtn=view.findViewById(R.id.logInBtn);
        moveToSignUp=view.findViewById(R.id.do_not_have_account);
        rememberMe=view.findViewById(R.id.radioButton_rememberMe);

        sharedPreferences=this.getActivity().getSharedPreferences("USER_DETAILS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInputs()){
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUser_Email(emailId.getText().toString());
                    loginRequest.setUser_Password(password.getText().toString());
                    Call<LoginResponse> call= ApiClient.getUserService().userLogin(loginRequest);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            Log.e(TAG,"on Response"+response.code());
                            LoginResponse loginResponse = response.body();
                            String email=loginResponse.getUser().getUser_Email();
                            Toast.makeText(getContext(),email,Toast.LENGTH_SHORT).show();
                            if(loginResponse.getSuccess()==true){
                                if(rememberMe.isChecked()){
                                    editor.putString("id",loginResponse.getUser().get_id());
                                    editor.putString("name",loginResponse.getUser().getUser_Name());
                                    editor.putString("phone_number",Long.toString(loginResponse.getUser().getUser_Phone_Number()));
                                    editor.putString("email",loginResponse.getUser().getUser_Email());
                                    editor.apply();

                                }else{
//                                    editor.putString("id",loginResponse.getUser().get_id());
//                                    editor.putString("name",loginResponse.getUser().getUser_name());
//                                    editor.putString("phone_number",Long.toString(loginResponse.getUser().getUser_Phone_Number()));
//                                    editor.putString("email",loginResponse.getUser().getUser_email());
//                                    editor.apply();
                                }
                                startActivity(new Intent(getContext(),MainActivity.class));
                                getActivity().finish();
                            }else{
                                // Toast.makeText(getContext(),loginResponse.getError(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
                }

            }
        });

        moveToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.register_frame_layout,new SignUpFragment()).commit();
            }
        });

        return view;
    }
    private boolean checkInputs(){
        if(!TextUtils.isEmpty(emailId.getText())){

            if(!TextUtils.isEmpty(password.getText())){

                if(isEmailValid(emailId.getText().toString())){

                    if(isValidPassword(password.getText().toString())){
                        return true;
                    }else{
                        Toast.makeText(getContext(),"Password Must Contain a Uppercase Letter , a Lowercase Letter, a Special character and must be of length greater than 8",Toast.LENGTH_LONG).show();
                        password.requestFocus();
                        return false;
                    }
                }else{
                    emailId.requestFocus();
                    Toast.makeText(getContext(),"Enter The valid Email Id",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                password.requestFocus();
                return false;
            }
        }else{
            emailId.requestFocus();
            return false;
        }





    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String password)
    {

        // for checking if password length
        // is between 8 and 15
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
            return false;
        }

        // to check space
        if (password.contains(" ")) {
            return false;
        }
//        if (true) {
//            int count = 0;
//
//            // check digits from 0 to 9
//            for (int i = 0; i <= 9; i++) {
//
//                // to convert int to string
//                String str1 = Integer.toString(i);
//
//                if (password.contains(str1)) {
//                    count = 1;
//                }
//            }
//            if (count == 0) {
//                return false;
//            }
//        }

        // for special characters
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return false;
        }

        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char)i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        if (true) {
            int count = 0;

            // checking small letters
            for (int i = 97; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        // if all conditions fails
        return true;
    }
    void showForgotPasswordBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_forgot_password, null);
        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();
    }

}