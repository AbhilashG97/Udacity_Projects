package com.watermelonheart.petmelon;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.watermelonheart.petmelon.data.Pet;
import com.watermelonheart.petmelon.data.PetDatabase;
import com.watermelonheart.petmelon.provider.PetProvider;
import com.watermelonheart.petmelon.utilities.Constant;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_pets)
    RecyclerView recyclerView;

    private PetDatabase database;
    private PetAdapter petAdapter;

    private final static int LOADER_PET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        onFabPressed();
        database = PetDatabase.getInstance(this);

        petAdapter = new PetAdapter();
        recyclerView.setAdapter(petAdapter);

        getSupportLoaderManager().initLoader(LOADER_PET, null, loaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
            switch (id) {
                case LOADER_PET:
                    return new CursorLoader(getApplicationContext(),
                            PetProvider.uri,
                            new String[] {"name"},
                            null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()) {
                case LOADER_PET:
                    petAdapter.setCursor(cursor);
                    break;
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_PET:
                    petAdapter.setCursor(null);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //getPetData();
    }


//    public void getPetData() {





    
//        Executors.newSingleThreadExecutor().execute(() -> {
//            numberOfPets = database.getPetDao().getTotalPets();
//            pets = database.getPetDao().getAllPets();
//
//            //runOnUiThread(() -> updateUi(numberOfPets, pets));
//        });
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(data != null) {
                if (data.getStringExtra("SHOW_TOAST").equals("1")) {
                    Snackbar.make(this.findViewById(android.R.id.content),
                            "Pet added to DB", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    private void onFabPressed() {
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AddPet.class);
            startActivityForResult(intent, 1);
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

                    runOnUiThread( () -> {
                        //updateUi(numberOfPets, pets);
                    });
                });

                return true;
            case R.id.action_delete_all_pets:
                // delete all pets
                Executors.newSingleThreadExecutor().execute(() -> {
                    database.getPetDao().removeAllPets();

                    //runOnUiThread(() -> updateUi(numberOfPets, pets));
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
