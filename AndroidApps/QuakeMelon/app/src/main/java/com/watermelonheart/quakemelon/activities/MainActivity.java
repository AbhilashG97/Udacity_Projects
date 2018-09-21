package com.watermelonheart.quakemelon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.watermelonheart.quakemelon.R;
import com.watermelonheart.quakemelon.utilities.CustomEditTextDatePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_start_date)
    EditText startDate;

    @BindView(R.id.et_end_date)
    EditText endDate;

    @BindView(R.id.btn_search)
    Button search;

    @BindView(R.id.et_magnitude)
    EditText magnitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Start Date Picker
        new CustomEditTextDatePicker(this, R.id.et_start_date);

        //End Date Picker
        new CustomEditTextDatePicker(this, R.id.et_end_date);

        onSearchButtonPressed();
    }

    public void onSearchButtonPressed() {
        search.setOnClickListener((view) -> {
            Intent intent = new Intent(getBaseContext(), ViewEarthquake.class);
            intent.putExtra("startDate", startDate.getText().toString());
            intent.putExtra("endDate", endDate.getText().toString());
            intent.putExtra("magnitude", magnitude.getText().toString());
            startActivity(intent);
        });
    }

}
