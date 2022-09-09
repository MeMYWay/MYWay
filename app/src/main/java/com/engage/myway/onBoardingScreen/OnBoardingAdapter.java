package com.engage.myway.onBoardingScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engage.myway.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>{
    private List<OnBoardingItem> onBoardingItemList;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItemList) {
        this.onBoardingItemList = onBoardingItemList;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItemList.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder {
        private TextView textTile;
        private TextView textDescription;
        private ImageView imageOnBoarding;


        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTile=itemView.findViewById(R.id.tectTitle);
            textDescription=itemView.findViewById(R.id.textDescription);
            imageOnBoarding=itemView.findViewById(R.id.imageOnBoarding);

        }
        void setOnBoardingData(OnBoardingItem onBoardingItem){
            textTile.setText(onBoardingItem.getTitle());
            textDescription.setText(onBoardingItem.getDescription());
            imageOnBoarding.setImageResource(onBoardingItem.getImage());

        }
    }
}
