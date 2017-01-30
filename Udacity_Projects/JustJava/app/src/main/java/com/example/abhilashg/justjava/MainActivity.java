/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package com.example.abhilashg.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;
import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method increases the value of the quantity
     */
    public void increment(View view) {
        quantity += 1;
        if (quantity >= 100) {
            quantity = 100;
            Toast.makeText(getApplicationContext(), "You cannot have more than 100 coffees at a time.", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method decreases the value of the quantity
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity <= 0) {
            quantity = 1;
            Toast.makeText(getApplicationContext(), "You cannot have less than one coffee", Toast.LENGTH_SHORT).show();

        }
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText name = (EditText) findViewById(R.id.name_field);
        String inputName = name.getText().toString();

        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = hasChocolate.isChecked();

        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addWhippedCream = hasWhippedCream.isChecked();

        int price = calculatePrice(addChocolate, addWhippedCream);

        createOrderSummary(price, addWhippedCream, addChocolate, inputName);

    }


    private void createOrderSummary(int price, boolean clicked, boolean hasChocolate, String name) {

        String summary = name + "\nQuantity :" + quantity + "\n" + "Total: $ " + price;
        summary += "\nAdded Whipped cream ? " + clicked;
        summary += "\nAdded Chocolate ? " + hasChocolate;
        summary += "\nThank you.";
        displayMessage(summary);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "My coffee order");
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     *
     * @param addChocolate    is the boolean value for the chocolate topping
     * @param addWhippedCream is the boolean value for the whipped cream topping
     */
    private int calculatePrice(boolean addChocolate, boolean addWhippedCream) {
        int price = quantity * 5;
        if (addChocolate) {
            price += 2;
        }

        if (addWhippedCream) {
            price += 1;
        }
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}