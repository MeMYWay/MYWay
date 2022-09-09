package com.engage.myway;

import java.util.List;

public class GetVehicleResponse {
    private Boolean success;
    private String error;
    private List<VehicleInfoModel > vehicle;

    public GetVehicleResponse(Boolean success, String error, List<VehicleInfoModel> vehicle) {
        this.success = success;
        this.error = error;
        this.vehicle = vehicle;
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

    public List<VehicleInfoModel> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<VehicleInfoModel> vehicle) {
        this.vehicle = vehicle;
    }
}
