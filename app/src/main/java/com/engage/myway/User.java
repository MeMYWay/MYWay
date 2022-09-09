package com.engage.myway;

public class User {

    private String _id;
    private String user_Name;
    private String user_Email;
    private long user_Phone_Number;

    public User(String _id, String user_Name, String user_Email, long user_Phone_Number) {
        this._id = _id;
        this.user_Name = user_Name;
        this.user_Email = user_Email;
        this.user_Phone_Number = user_Phone_Number;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public long getUser_Phone_Number() {
        return user_Phone_Number;
    }

    public void setUser_Phone_Number(long user_Phone_Number) {
        this.user_Phone_Number = user_Phone_Number;
    }
}
