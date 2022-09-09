package com.engage.myway;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.engage.myway.api.ApiClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.FirebaseException;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment {

    private TextView moveToLogIn;
    private EditText name,email,password,confirmPassword,phoneNumber;
    private Button signUpButton,verifyOtpBtn;
    private EditText etC1,etC2,etC3,etC4,etC5,etC6;
    private ProgressBar progressBarVerify;
    private String verificationId;

    public SignUpFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        moveToLogIn=view.findViewById(R.id.already_have_account_TV);
        name=view.findViewById(R.id.nameSignUp);
        email=view.findViewById(R.id.emailETSignUp);
        password=view.findViewById(R.id.passwordETSignUp);
        confirmPassword=view.findViewById(R.id.confirm_password_ET_sign_up);
        signUpButton=view.findViewById(R.id.signUpBtn);
        phoneNumber=view.findViewById(R.id.editTextPhone);

        moveToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.register_frame_layout,new LogInFragment()).commit();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInputs()){
                    SignUpRequest signUpRequest=new SignUpRequest();
                    signUpRequest.setUser_Name(name.getText().toString());
                    signUpRequest.setUser_Email(email.getText().toString());
                    signUpRequest.setUser_Password(password.getText().toString());
                    String s=phoneNumber.getText().toString();
                    long num=Long.valueOf(s);
                    signUpRequest.setUser_Phone_Number(num);

                    Call<LoginResponse> call= ApiClient.getUserService().createUser(signUpRequest);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();

                            if(loginResponse.getSuccess()==true){
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

        return view;
    }
    private boolean checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){

            if(!TextUtils.isEmpty(password.getText())){

                if(isEmailValid(email.getText().toString())){

                    if(isValidPassword(password.getText().toString())){
                        if(password.getText().toString().equals(confirmPassword.getText().toString())){
                            if(phoneNumber.getText().toString().length()==10){
                                return true;
                            }else{
                                Toast.makeText(getContext(),"Enter Valid Phone Number",Toast.LENGTH_SHORT).show();
                                return false;
                            }

                        }else{
                            Toast.makeText(getContext(), "Confirm Password Does Not matches",Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    }else{
                        Toast.makeText(getContext(),"Password Must Contain a Uppercase Letter , a Lowercase Letter, a Special character and must be of length greater than 8",Toast.LENGTH_LONG).show();
                        password.requestFocus();
                        return false;
                    }
                }else{
                    email.requestFocus();
                    Toast.makeText(getContext(),"Enter The valid Email Id",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                password.requestFocus();
                return false;
            }
        }else{
            email.requestFocus();
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
    void showOtpBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_verification_otp, null);
         etC1 =parentView.findViewById(R.id.add_vehicle_name);
         etC2=parentView.findViewById(R.id.add_vehicle_plateNum);
         etC3=parentView.findViewById(R.id.add_vehicle_plateNum);
         etC4=parentView.findViewById(R.id.add_vehicle_plateNum);
         etC5=parentView.findViewById(R.id.add_vehicle_plateNum);
         etC6=parentView.findViewById(R.id.add_vehicle_plateNum);
        progressBarVerify=parentView.findViewById(R.id.progressBarVerify);
         verifyOtpBtn=parentView.findViewById(R.id.btnVerify);
         verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressBarVerify.setVisibility(View.VISIBLE);
                 verifyOtpBtn.setVisibility(View.INVISIBLE);
                 if (etC1.getText().toString().trim().isEmpty() ||
                         etC2.getText().toString().trim().isEmpty() ||
                         etC3.getText().toString().trim().isEmpty() ||
                         etC4.getText().toString().trim().isEmpty() ||
                         etC5.getText().toString().trim().isEmpty() ||
                         etC6.getText().toString().trim().isEmpty()) {
                     Toast.makeText(getContext(), "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                 } else {
                     if (verificationId != null) {
                         String code = etC1.getText().toString().trim() +
                                 etC2.getText().toString().trim() +
                                 etC3.getText().toString().trim() +
                                 etC4.getText().toString().trim() +
                                 etC5.getText().toString().trim() +
                                 etC6.getText().toString().trim();

//                         PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//                         FirebaseAuth
//                                 .getInstance()
//                                 .signInWithCredential(credential)
//                                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                     @Override
//                                     public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//                                         if (task.isSuccessful()) {
//                                             progressBarVerify.setVisibility(View.VISIBLE);
//                                             verifyOtpBtn.setVisibility(View.INVISIBLE);
//                                             Toast.makeText(getContext(), "Welcome...", Toast.LENGTH_SHORT).show();
//                                             Intent intent = new Intent(getContext(), MainActivity.class);
//                                             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                             startActivity(intent);
//                                         } else {
//                                             progressBarVerify.setVisibility(View.GONE);
//                                             verifyOtpBtn.setVisibility(View.VISIBLE);
//                                             Toast.makeText(getContext(), "OTP is not Valid!", Toast.LENGTH_SHORT).show();
//                                         }
//                                     }
//                                 });
                     }
                 }
             }
         });



        Button addVehicleBtn=parentView.findViewById(R.id.add_vehicle_btn);
        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();

    }
    private void editTextInput() {
        etC1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
//    private void sendOtp(){
//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            @Override
//            public void onVerificationCompleted(PhoneAuthCredential credential) {
//
//            }
//
//            @Override
//            public void onVerificationFailed(FirebaseException e) {
//                progressBar.setVisibility(View.GONE);
//                btnSend.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String verificationId,
//                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                progressBar.setVisibility(View.GONE);
//                btnSend.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(), "OTP is successfully send.", Toast.LENGTH_SHORT).show();
//               // Intent intent = new Intent(getContext(), OtpVerifyActivity.class);
//                //intent.putExtra("phone", binding.etPhone.getText().toString().trim());
//                //intent.putExtra("verificationId", verificationId);
//                //startActivity(intent);
//            }
//        };
//
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+91" + etPhone.getText().toString().trim())
//                        .setTimeout(60L, TimeUnit.SECONDS)
//                        .setActivity(this)
//                        .setCallbacks(mCallbacks)
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }

}