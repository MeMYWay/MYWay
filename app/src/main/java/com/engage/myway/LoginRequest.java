package com.engage.myway;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_Email")
    private String user_Email;
    @SerializedName("user_Password")
    private String user_Password;

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
}