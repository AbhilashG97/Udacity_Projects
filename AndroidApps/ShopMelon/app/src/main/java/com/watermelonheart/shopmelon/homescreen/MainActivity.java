package com.watermelonheart.shopmelon.homescreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.watermelonheart.shopmelon.R;
import com.watermelonheart.shopmelon.SalesActivity;
import com.watermelonheart.shopmelon.model.Phone;
import com.watermelonheart.shopmelon.model.Transaction;
import com.watermelonheart.shopmelon.cartrecyclerview.CartRecyclerViewAdapter;
import com.watermelonheart.shopmelon.cartrecyclerview.ClickListener;
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

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fetchPhoneData();
    }

    public void fetchPhoneData() {
        ApiInterface loadPhone = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ArrayList<Phone>> call;

        if (!getDataFromBundle()) {
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
                Log.v("SERVER RESPONSE", response.body() + "");
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

    public void getPhoneTransaction(String model, String username, String quantity) {
        ApiInterface loadTransaction = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<Transaction> call = loadTransaction.getTransaction(model, username, quantity);
        call.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                Log.v("ITEM BOUGHT", response.body().toString());
                Toast.makeText(MainActivity.this, response.body().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.e("TRANSACTION", "Failed to fetch transaction :(");
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

        adapter.setOnItemClickListener(new ClickListener() {

//            @BindView(R.id.et_username)
//            EditText username;
//
//            @BindView(R.id.et_quantity)
//            EditText quantity;

            @Override
            public void onItemClick(int position, View view) {
                Log.d("Item Clicked :","Position clicked :"+position);

                //final View v = getLayoutInflater().inflate(R.layout.buy_custom_dialog, null);
                //ButterKnife.bind(this, v);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();

                View dialogView = layoutInflater.inflate(R.layout.buy_custom_dialog, null);

                builder.setView(dialogView);

                EditText username = dialogView.findViewById(R.id.et_username);
                EditText quantity = dialogView.findViewById(R.id.et_quantity);

                builder.setTitle("Buy a product");
                builder.setCancelable(false);
                builder.setMessage("Please enter these details")
                        .setPositiveButton("Buy", (dialog, id) -> {
                            // handle the transaction
                            Log.v("DIALOG VALUE : USERNAME", username.getText().toString());
                            Log.v("DIALOG VALUE : QUANTITY", quantity.getText().toString());

                            getPhoneTransaction(phoneList.get(position).getModel(),
                                    username.getText().toString(),
                                    quantity.getText().toString());

                        })
                        .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onItemLongClick(int position, View view) {
                Log.d("Long Item Clicked :","Position clicked :"+position);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Log.d("Search Pressed", "Item pressed SEARCH");
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.app_bar_transaction:
                Log.d("Transaction Pressed", "Item pressed TRANSACTION");
                Intent transactionIntent = new Intent(this, SalesActivity.class);
                startActivity(transactionIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setMessage(R.string.quit_confirm)
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialogInterface, id) -> MainActivity.this.finish())
                    .setNegativeButton("No", null)
                    .show();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);

    }

    public boolean getDataFromBundle() {
        Log.v("BUNDLE FROM SEARCH", "EXECUTED!!");
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
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
