package com.watermelonheart.quakemelon.retrofit;

import com.watermelonheart.quakemelon.model.EarthquakeData;
import com.watermelonheart.quakemelon.model.Feature;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("query")
    Call<EarthquakeData> getFeatures(@Query("format") String format,
                                     @Query("starttime") String starttime,
                                     @Query("endtime") String endtime,
                                     @Query("limit") int limit);

    @GET("query")
    Call<EarthquakeData> getFeatures(@Query("format") String format,
                                         @Query("starttime") String starttime,
                                         @Query("endtime") String endtime,
                                         @Query("minmagnitude") int magnitude,
                                         @Query("limit") int limit);
}
