package com.watermelonheart.petmelon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.watermelonheart.petmelon.data.PetDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPet extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_breed)
    EditText etBreed;

    @BindView(R.id.sp_gender)
    Spinner spGender;

    @BindView(R.id.et_weight)
    EditText etWeight;

    private PetDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);

        database = PetDatabase.getInstance(this);
    }

    public void insertData() {
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_pet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_delete:
                // delete the entry
                return true;
            case R.id.item_check:
                // add pet to DB
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
