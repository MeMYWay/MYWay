package com.engage.myway;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("user_Email")
    private String user_Email;
    @SerializedName("user_Password")
    private String user_Password;
    @SerializedName("user_Phone_Number")
    private long user_Phone_Number;
    @SerializedName("user_Name")
    private String user_Name;

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public long getUser_Phone_Number() {
        return user_Phone_Number;
    }

    public void setUser_Phone_Number(long user_Phone_Number) {
        this.user_Phone_Number = user_Phone_Number;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }
}
