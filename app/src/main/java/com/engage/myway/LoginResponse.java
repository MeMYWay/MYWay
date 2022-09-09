package com.engage.myway;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

public class LoginResponse {
    @SerializedName("success")
    private Boolean success;
    private String error;
    @SerializedName("user")
   private User user;

    public LoginResponse(Boolean success, String error, User user) {
        this.success = success;
        this.error = error;
        this.user = user;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
