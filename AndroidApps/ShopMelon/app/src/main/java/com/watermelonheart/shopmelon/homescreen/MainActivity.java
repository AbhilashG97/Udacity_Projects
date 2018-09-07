package com.watermelonheart.shopmelon.homescreen;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.watermelonheart.shopmelon.R;
import com.watermelonheart.shopmelon.model.Phone;
import com.watermelonheart.shopmelon.recyclerview.CartRecyclerViewAdapter;
import com.watermelonheart.shopmelon.retrofit.ApiClient;
import com.watermelonheart.shopmelon.retrofit.ApiInterface;
import com.watermelonheart.shopmelon.searchproduct.SearchActivity;
import com.watermelonheart.shopmelon.utility.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_cart_items)
    RecyclerView recyclerView;

    @BindView(R.id.pb_loadPhones)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fetchData();
    }

    public void fetchData() {
        ApiInterface loadPhone = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ArrayList<Phone>> call;

        if(!getDataFromBundle()) {
            call = loadPhone.getPhones();
        } else {
            call = loadPhone.getPhones(getIntent().getExtras().getString(Constant.MANUFACTURER),
                    getIntent().getExtras().getString(Constant.MODEL),
                    getIntent().getExtras().getString(Constant.MINIMUM_PRICE),
                    getIntent().getExtras().getString(Constant.MAXIMUM_PRICE));
        }

        call.enqueue(new Callback<ArrayList<Phone>>() {
            @Override
            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.v("SERVER RESPONSE", response.body()+"");
                initializeRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                Log.e(MainActivity.class.toString(), getString(R.string.data_fetch_failed));
                Toast.makeText(getApplicationContext(), getString(R.string.data_fetch_failed),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void initializeRecyclerView(ArrayList<Phone> phoneList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),
                2,
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(gridLayoutManager);
        CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(phoneList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.app_bar_search:
                Log.d("Search Pressed", "Item pressed SEARCH");
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.quit_confirm)
                .setCancelable(false)
                .setPositiveButton("Yes", (dialogInterface, id) -> MainActivity.this.finish())
                .setNegativeButton("No", null)
                .show();
    }

    public boolean getDataFromBundle() {
        Log.v("BUNDLE FROM SEARCH", "EXECUTED!!");
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            Log.v("manufacturer", intent.getExtras().getString(Constant.MANUFACTURER));
            Log.v("model", intent.getExtras().getString(Constant.MODEL));
            Log.v("maximum_price", intent.getExtras().getString(Constant.MAXIMUM_PRICE));
            Log.v("minimum_price", intent.getExtras().getString(Constant.MINIMUM_PRICE));
            return true;
        } else {
            return false;
        }
    }
}
