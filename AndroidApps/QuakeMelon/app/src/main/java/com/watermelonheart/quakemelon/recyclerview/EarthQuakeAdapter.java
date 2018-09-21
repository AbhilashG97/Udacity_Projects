package com.watermelonheart.quakemelon.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watermelonheart.quakemelon.R;
import com.watermelonheart.quakemelon.model.Feature;
import com.watermelonheart.quakemelon.model.Geometry;
import com.watermelonheart.quakemelon.model.Properties;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeViewHolder> {

    private ArrayList<Feature> featureList;
    private Context context;

    public EarthQuakeAdapter(ArrayList<Feature> featureList, Context context) {
        this.featureList = featureList;
        this.context = context;
    }

    @NonNull
    @Override
    public EarthQuakeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EarthQuakeViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.earthquake_info_item, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EarthQuakeViewHolder earthQuakeViewHolder, int position) {
            Properties properties = featureList.get(position).getProperties();

            earthQuakeViewHolder.place.setText(properties.getPlace());
            earthQuakeViewHolder.magnitude.setText(String.valueOf(properties.getMag()));
            earthQuakeViewHolder.alert.setText(properties.getAlert());

            Geometry geometry = featureList.get(position).getGeometry();

            earthQuakeViewHolder.longitude.setText(String.valueOf(geometry.getCoordinates().get(0)));
            earthQuakeViewHolder.latitude.setText(String.valueOf(geometry.getCoordinates().get(1)));
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    public static class EarthQuakeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_alert)
        TextView alert;

        @BindView(R.id.tv_latitude)
        TextView latitude;

        @BindView(R.id.tv_longitude)
        TextView longitude;

        @BindView(R.id.tv_magnitude)
        TextView magnitude;

        @BindView(R.id.tv_place)
        TextView place;

        public EarthQuakeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
