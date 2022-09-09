package com.engage.myway;

import com.google.gson.annotations.SerializedName;

public class AddVehicleRequest {
    @SerializedName("user_email")
    String user_email;
    @SerializedName("vehicle_Name")
    String vehicle_Name;
    @SerializedName("vehicle_Number")
    String vehicle_Number;



    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getVehicle_Name() {
        return vehicle_Name;
    }

    public void setVehicle_Name(String vehicle_Name) {
        this.vehicle_Name = vehicle_Name;
    }

    public String getVehicle_Number() {
        return vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        this.vehicle_Number = vehicle_Number;
    }
}

