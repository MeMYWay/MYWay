package com.engage.myway;

import android.content.Context;
import android.widget.Toast;

import com.engage.myway.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBQueries {
    public static List<VehicleInfoModel> getVehicleResponseList=new ArrayList<>();


    public static void loadVehicle(String userEmailId, Context context,final VehicleListAdapter vehicleAdapter){

        getVehicleResponseList.clear();
        if(userEmailId!=null){
            GetVehicleRequest getVehicleRequest=new GetVehicleRequest();
            getVehicleRequest.setUser_Email(userEmailId);
            Call<GetVehicleResponse> call= ApiClient.getUserService().getVehicle(getVehicleRequest);
            call.enqueue(new Callback<GetVehicleResponse>() {
                @Override
                public void onResponse(Call<GetVehicleResponse> call, Response<GetVehicleResponse> response) {
                    if(response.isSuccessful()){
                        GetVehicleResponse getVehicleResponse =response.body();
                        if(getVehicleResponse.getSuccess()){
                            int size=getVehicleResponse.getVehicle().size();
                           for(int i=0;i<size;i++){
                               getVehicleResponseList.add(getVehicleResponse.getVehicle().get(i));
                           }
                           vehicleAdapter.notifyDataSetChanged();

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetVehicleResponse> call, Throwable t) {

                }
            });
        }else{
            ///Handle Error
        }
    }

    public static void addVehicle(AddVehicleRequest addVehicleRequest){
        Call<GetVehicleResponse> call=ApiClient.getUserService().addVehicle(addVehicleRequest);
        call.enqueue(new Callback<GetVehicleResponse>() {
            @Override
            public void onResponse(Call<GetVehicleResponse> call, Response<GetVehicleResponse> response) {
                if(response.isSuccessful()){
                    GetVehicleResponse getVehicleResponse =response.body();
                    if(getVehicleResponse.getSuccess()){
                        int size=getVehicleResponse.getVehicle().size();
                        for(int i=0;i<size;i++){
                            getVehicleResponseList.add(getVehicleResponse.getVehicle().get(i));
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetVehicleResponse> call, Throwable t) {

            }
        });
    }
}
