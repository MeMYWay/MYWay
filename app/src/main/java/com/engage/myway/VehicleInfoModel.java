package com.engage.myway;

public class VehicleInfoModel {
    private String _id, vehicle_Name, vehicle_Number, user_Email, __v;

    public VehicleInfoModel(String _id, String vehicle_Name, String vehicle_Number, String user_Email, String __v) {
        this._id = _id;
        this.vehicle_Name = vehicle_Name;
        this.vehicle_Number = vehicle_Number;
        this.user_Email = user_Email;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
