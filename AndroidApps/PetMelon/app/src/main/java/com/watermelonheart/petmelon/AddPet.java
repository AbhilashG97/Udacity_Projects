package com.watermelonheart.petmelon;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.watermelonheart.petmelon.data.Pet;
import com.watermelonheart.petmelon.data.PetDatabase;
import com.watermelonheart.petmelon.utilities.Constant;

import java.util.concurrent.Executors;

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
        initializeSpinner();
    }

    private void initializeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constant.genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(adapter);
    }

    public void insertData() {
        if (isFieldEmpty()) {
            Snackbar.make(AddPet.this.findViewById(android.R.id.content),
                    "Please enter the details",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            new DatabaseAsyncTask().execute();
        }

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
                Executors.newSingleThreadExecutor().execute(() -> database.getPetDao()
                        .deletePetById(2));
                return true;
            case R.id.item_check:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                insertData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isFieldEmpty() {
        if (etName.getText().toString().equals("") ||
                etBreed.getText().toString().equals("") ||
                etWeight.getText().toString().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    private class DatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            Pet pet = new Pet();
            pet.setBreed(etBreed.getText().toString());
            pet.setName(etName.getText().toString());
            pet.setWeight(Integer.parseInt(etWeight.getText().toString()));

            pet.setGender(Constant.map.get(spGender.getSelectedItem().toString()));

            database.getPetDao().insertPet(pet);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AddPet.this.finish();
        }
    }
}
