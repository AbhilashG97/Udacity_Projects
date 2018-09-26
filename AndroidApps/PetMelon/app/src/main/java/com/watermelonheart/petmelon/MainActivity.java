package com.watermelonheart.petmelon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.watermelonheart.petmelon.data.PetDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        onFabPressed();
        initializePetDatabase();

    }

    public void initializePetDatabase() {
        database = PetDatabase.getInstance(this);
        new DatabaseAsyncTask().execute();
    }

    public void showLogs(int numberOfPets) {
        logView.setText("Total number of pets are "+numberOfPets);
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
                return true;
            case R.id.action_delete_all_pets:
                // delete all pets
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            showLogs(database.getPetDao().getTotalPets());
            return null;
        }
    }
}
