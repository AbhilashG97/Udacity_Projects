package com.watermelonheart.petmelon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.watermelonheart.petmelon.data.Pet;
import com.watermelonheart.petmelon.data.PetDatabase;
import com.watermelonheart.petmelon.utilities.Constant;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_log)
    TextView logView;

    private PetDatabase database;
    private int numberOfPets;
    private List pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        onFabPressed();
        initializePetDatabase();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getPetData();
    }

    public void initializePetDatabase() {
        database = PetDatabase.getInstance(this);
        getPetData();
    }

    private void updateUi(int numberOfPets, List pets) {
        logView.setText("Total number of pets are " + numberOfPets + "\n\n" + pets);
    }

    public void getPetData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            numberOfPets = database.getPetDao().getTotalPets();
            pets = database.getPetDao().getAllPets();

            runOnUiThread(() -> updateUi(numberOfPets, pets));
        });

    }

    private void onFabPressed() {
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AddPet.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_insert_dummy_data:
                //insert dummy data

                Executors.newSingleThreadExecutor().execute(() -> {

                    Pet pet = new Pet();
                    pet.setName("Test pet");
                    pet.setBreed("Test breed");
                    pet.setGender(Constant.FEMALE);
                    pet.setWeight(10);

                    database.getPetDao().insertPet(pet);

                    numberOfPets = database.getPetDao().getTotalPets();
                    pets = database.getPetDao().getAllPets();

                    runOnUiThread( () -> {
                        updateUi(numberOfPets, pets);
                    });
                });

                return true;
            case R.id.action_delete_all_pets:
                // delete all pets
                Executors.newSingleThreadExecutor().execute(() -> {
                    database.getPetDao().removeAllPets();

                    numberOfPets = database.getPetDao().getTotalPets();
                    pets = database.getPetDao().getAllPets();

                    runOnUiThread(() -> updateUi(numberOfPets, pets));
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
