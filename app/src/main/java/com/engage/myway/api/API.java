package com.engage.myway.api;

import android.app.appsearch.GetByDocumentIdRequest;

import com.engage.myway.AddVehicleRequest;
import com.engage.myway.GetVehicleRequest;
import com.engage.myway.GetVehicleResponse;
import com.engage.myway.LoginRequest;
import com.engage.myway.LoginResponse;
import com.engage.myway.SignUpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {


    @POST("auth/login")
    Call<LoginResponse> userLogin(
            @Body LoginRequest loginRequest
            );


    @POST("auth/register")
    Call<LoginResponse> createUser(
            @Body SignUpRequest signUpRequest
            );

    @POST("vehicle/getVehicle")
    Call<GetVehicleResponse> getVehicle(
            @Body GetVehicleRequest getVehicleRequest
            );

    @POST("vehicle/addVehicle")
    Call<GetVehicleResponse> addVehicle(
            @Body AddVehicleRequest addVehicleRequest
            );
}
