package com.watermelonheart.shopmelon.retrofit;

import java.util.ArrayList;

import com.watermelonheart.shopmelon.model.Phone;
import com.watermelonheart.shopmelon.model.Transaction;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("get-items")
    Call<ArrayList<Phone>> getPhones();

    @GET("get-items")
    Call<ArrayList<Phone>> getPhones(@Query("manufacturer") String manufacturer,
                                     @Query("model") String model,
                                     @Query("min-price") String minPrice,
                                     @Query("max-price") String maxPrice);
    @GET("buy")
    Call<Transaction> getTransaction(@Query("model") String model,
                                     @Query("username") String username,
                                     @Query("qty") String quantity);

    @GET("getSalesRecords")
    Call<ArrayList<Transaction>> getAllTransaction();
}
