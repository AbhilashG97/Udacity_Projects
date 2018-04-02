package com.example.abhilashg.coffeeanyone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static int quantity = 0;

    @BindView(R.id.tv_price_Text)
    TextView priceView;

    @BindView(R.id.et_name)
    EditText userName;  

    @BindView(R.id.tv_quantity_num)
    TextView quantityText;

    @BindView (R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            quantity = 0;
            quantityText.setText("" + quantity);
            priceView.setText("Rs 0");
            userName.setText(null);
        }

        if(id==R.id.action_settings) {
            // About page will appear here
        }
        return super.onOptionsItemSelected(item);
    }

    //  This method is used to increase the quantity of the cups of coffee ordered.
    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    // This method is used to decrease the quantity of the cups of coffee ordered.
    public void decrement(View view) {
        quantity -= 1;
        if (quantity < 0) {
            quantity = 0;
            Toast.makeText(getApplicationContext(), "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    // This method displays the price of a coffee
    public void calculatePrice(int quantity) {
        int price = quantity * 5;
        priceView.setText("Rs " + Integer.toString(price));
    }

    // This method sends the order through an implicit intent
    public void order(View view) {
        userName.getText().toString();
        calculatePrice(quantity);

        if(quantity==0) {
            Snackbar.make(coordinatorLayout,"Quantity cannot be zero.",Snackbar.LENGTH_SHORT).show();
        } else {
            Intent orderSummary = new Intent(Intent.ACTION_SENDTO);
            orderSummary.setType("text/plain");
            orderSummary.setData(Uri.parse("mailto:"));
            orderSummary.putExtra(orderSummary.EXTRA_TEXT, "");

            if (orderSummary.resolveActivity(getPackageManager()) != null) {
                startActivity(orderSummary);
            }
        }
    }

    // This method displays the quantity of the coffee cups ordered.
    private void displayQuantity(int quantity) {
        quantityText.setText("" + quantity);
    }

}
