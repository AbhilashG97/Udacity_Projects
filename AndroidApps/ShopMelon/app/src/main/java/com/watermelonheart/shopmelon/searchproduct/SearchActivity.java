package com.watermelonheart.shopmelon.searchproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.watermelonheart.shopmelon.R;
import com.watermelonheart.shopmelon.homescreen.MainActivity;
import com.watermelonheart.shopmelon.utility.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.et_manufacturer)
    EditText manufacturer;

    @BindView(R.id.et_model)
    EditText model;

    @BindView(R.id.et_minimum_price)
    EditText minimumPrice;

    @BindView(R.id.et_maximum_price)
    EditText maximumPrice;

    @BindView(R.id.btn_search)
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        onSearchButtonPressed();
    }

    public void onSearchButtonPressed() {
        search.setOnClickListener((view) -> {
            Log.v(SearchActivity.class.toString(), "search button pressed");
            Bundle bundle = new Bundle();

            bundle.putString(Constant.MANUFACTURER, manufacturer.getText().toString());
            bundle.putString(Constant.MODEL, model.getText().toString());
            bundle.putString(Constant.MAXIMUM_PRICE, maximumPrice.getText().toString());
            bundle.putString(Constant.MINIMUM_PRICE, minimumPrice.getText().toString());

            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
