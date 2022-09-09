package com.engage.myway;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.ViewHolder>{
    List<VehicleInfoModel> vehicleInfoModelList;

    public VehicleListAdapter(List<VehicleInfoModel> vehicleInfoModelList) {
        this.vehicleInfoModelList = vehicleInfoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_vehiclelist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(vehicleInfoModelList.get(position).getVehicle_Name());
    }

    @Override
    public int getItemCount() {
        return vehicleInfoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleName;
        ImageView vehicleTypeImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleName=itemView.findViewById(R.id.vehicle_name);
            vehicleTypeImg=itemView.findViewById(R.id.vehicle_type_image);
        }
        void setData(String name){
            vehicleName.setText(name);
//            if(vehicleType=="Car"){
//                vehicleTypeImg.setImageResource(R.drawable.sedan);
//            }else{
//                vehicleTypeImg.setImageResource(R.drawable.bike);
//            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(),DriverPathInfoActivity.class));
                }
            });
        }

    }
}
