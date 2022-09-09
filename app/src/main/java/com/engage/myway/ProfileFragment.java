package com.engage.myway;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    private TextView userName;
    SharedPreferences sharedPreferences;
    public ProfileFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences=this.getActivity().getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("name","");
        userName=view.findViewById(R.id.user_name);
        userName.setText(name);
        return (View) view;
    }
}