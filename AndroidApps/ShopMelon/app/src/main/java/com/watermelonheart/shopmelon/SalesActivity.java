package com.watermelonheart.shopmelon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.watermelonheart.shopmelon.model.Transaction;
import com.watermelonheart.shopmelon.retrofit.ApiClient;
import com.watermelonheart.shopmelon.retrofit.ApiInterface;
import com.watermelonheart.shopmelon.transactionrecyclerview.TransactionAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {

    @BindView(R.id.rv_transactions)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);

        fetchTransaction();
    }

    public void fetchTransaction() {
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ArrayList<Transaction>> call = service.getAllTransaction();

        call.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                initializeRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
                Log.e(SalesActivity.class.toString(), t.getMessage());
            }
        });
    }

    public void initializeRecyclerView(ArrayList<Transaction> transactions) {

        recyclerView.setAdapter(new TransactionAdapter(transactions, this));
    }
}
