package com.engage.myway;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Location location;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private SupportMapFragment mapFragment;
    private RecyclerView vehicleListRV;
    private List<VehicleInfoModel> vehicleInfoModelList;
    private Switch switchMode;
    private LinearLayout driverBottomLayout,riderBottomLayout;
    SharedPreferences sharedPreferences;
    private Button addVehicleBtn;
    private String email;



    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        ////
        vehicleListRV=view.findViewById(R.id.vehicle_list_rv);
        switchMode=view.findViewById(R.id.switch_mode);
        riderBottomLayout=view.findViewById(R.id.rider_bottom_layout);
        driverBottomLayout=view.findViewById(R.id.driver_bottom_layout);
        driverBottomLayout.setVisibility(View.GONE);
        addVehicleBtn=view.findViewById(R.id.add_vehicle_btn);
        ////

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vehicleListRV.setLayoutManager(linearLayoutManager);
        vehicleInfoModelList=new ArrayList<>();
//        vehicleInfoModelList.add(new VehicleInfoModel("BMW","Car",4,384729839));
//        vehicleInfoModelList.add(new VehicleInfoModel("BMW","Car",4,384729839));
//        vehicleInfoModelList.add(new VehicleInfoModel("BMW","Car",4,384729839));
//        vehicleInfoModelList.add(new VehicleInfoModel("BMW","Car",4,384729839));
        ///
        sharedPreferences=this.getActivity().getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        email=sharedPreferences.getString("email",null);
        Toast.makeText(getContext(),email,Toast.LENGTH_SHORT).show();
        ///

        //////
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getContext(),"Inside Swithch Changed",Toast.LENGTH_SHORT).show();
                if(b){
                    driverBottomLayout.setVisibility(View.VISIBLE);
                    riderBottomLayout.setVisibility(View.GONE);
                    if(DBQueries.getVehicleResponseList.size()==0){
                        String email=sharedPreferences.getString("email",null);
                        VehicleListAdapter vehicleListAdapter=new VehicleListAdapter(DBQueries.getVehicleResponseList);
                        DBQueries.loadVehicle(email,getContext(),vehicleListAdapter);
                        int size=DBQueries.getVehicleResponseList.size();
                        Toast.makeText(getContext(),Integer.toString(size),Toast.LENGTH_LONG).show();
                        for(int i=0;i<size;i++){
                           vehicleInfoModelList.add(DBQueries.getVehicleResponseList.get(i));
                        }
                        Toast.makeText(getContext(),Integer.toString(vehicleInfoModelList.size()),Toast.LENGTH_SHORT).show();

                        vehicleListRV.setAdapter(vehicleListAdapter);
                    }else{
                        VehicleListAdapter vehicleListAdapter=new VehicleListAdapter(DBQueries.getVehicleResponseList);
                        vehicleListRV.setAdapter(vehicleListAdapter);
                    }
                    alert("Driver");
                }else{
                    alert("Rider");
                    riderBottomLayout.setVisibility(View.VISIBLE);
                    driverBottomLayout.setVisibility(View.GONE);
                }
            }
        });

        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicle();
            }
        });
        /////
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                if(location != null){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latlng).title("Current Location");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));

                            googleMap.addMarker(markerOptions);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
    private void alert(String mode){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setMessage("You are in the "+mode+" mode").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        alertDialog.show();
    }
    void addVehicle(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_add_vehicle, null);

        //parentView.findViewById().setOnClickListener();
        EditText vehicleName =parentView.findViewById(R.id.add_vehicle_name);
        EditText plateNum=parentView.findViewById(R.id.add_vehicle_plateNum);
        Button addVehicleBtn=parentView.findViewById(R.id.add_vehicle_btn);
        String name=vehicleName.getText().toString();
        String plateNumber=plateNum.getText().toString();

        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddVehicleRequest addVehicleRequest=new AddVehicleRequest();
                addVehicleRequest.setUser_email(email);
                addVehicleRequest.setVehicle_Name(name);
                addVehicleRequest.setVehicle_Number(plateNumber);
                DBQueries.addVehicle(addVehicleRequest);
            }
        });
        bottomSheetDialog.setContentView(parentView);

        bottomSheetDialog.show();
    }
}