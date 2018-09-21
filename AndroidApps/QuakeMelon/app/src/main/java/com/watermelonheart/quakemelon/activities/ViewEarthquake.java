package com.watermelonheart.quakemelon.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.watermelonheart.quakemelon.R;
import com.watermelonheart.quakemelon.model.EarthquakeData;
import com.watermelonheart.quakemelon.model.Feature;
import com.watermelonheart.quakemelon.recyclerview.EarthQuakeAdapter;
import com.watermelonheart.quakemelon.retrofit.ApiClient;
import com.watermelonheart.quakemelon.retrofit.ApiInterface;
import com.watermelonheart.quakemelon.utilities.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEarthquake extends AppCompatActivity {

    @BindView(R.id.rv_earthquake)
    RecyclerView earthQuakeRecyclerView;

    @BindView(R.id.pb_earthQuakeView)
    ProgressBar progressBar;

    private String startTime, endTime, magnitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_earthquake);
        ButterKnife.bind(this);

        startTime = getIntent().getStringExtra("startDate");
        endTime = getIntent().getStringExtra("endDate");
        magnitude = getIntent().getStringExtra("magnitude");

        Log.v("START DATE", startTime);
        Log.v("END DATE", endTime);
        Log.v("MAGNITUDE", magnitude);

        fectchData();

    }

    public void fectchData() {
        ApiInterface service = ApiClient.getClint().create(ApiInterface.class);
        Call<EarthquakeData> call;
        if(getIntent().getStringExtra("magnitude") == null ||
                getIntent().getStringExtra("magnitude").equals("")) {
            call = service.getFeatures(Constant.format,
                    startTime,
                    endTime,
                    50);
            Log.v("CALL", call.request().url().toString());
        } else {
            call = service.getFeatures(Constant.format,
                    startTime,
                    endTime,
                    Integer.parseInt(magnitude),
                    50);
            Log.v("CALL", call.request().url().toString());
        }

        call.enqueue(new Callback<EarthquakeData>() {

            @Override
            public void onResponse(Call<EarthquakeData> call, Response<EarthquakeData> response) {
                progressBar.setVisibility(View.GONE);
                Log.v("RESPONSE BODY", response.body().toString());
                initializeRecyclerView(response.body().getFeatures());
            }

            @Override
            public void onFailure(Call<EarthquakeData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("FETCH DATA", "Unable to fetch data :(");
                Toast.makeText(getBaseContext(), "Unable to fetch data :(", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void initializeRecyclerView(ArrayList<Feature> features) {
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(features, this);
        earthQuakeRecyclerView.setAdapter(adapter);
    }

}
