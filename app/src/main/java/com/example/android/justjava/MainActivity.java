package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int current_number = 1;
    private int minOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked. Displays the total price.
     */
    public void submitOrder(View view) {

        priceDisplay(current_number);
    }

    /**
     * This method is setting the displayed quantity to the given number
     *
     * @param number to which the number of coffees will be set.
     */
    private void setNumber(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Displays the total price of the coffees.
     *
     * @param numberOfCoffees is the number of coffees, it will be multiplied with the price of one coffee to get the total price.
     */
    private void priceDisplay(int numberOfCoffees) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String message = ("Total: " + NumberFormat.getCurrencyInstance().format(current_number * 5) + "\n" + "Thank you!");
        priceTextView.setText(message);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * increases the number of coffees with one.
     *
     * @param view
     */
    public void increaseQty(View view) {
        current_number += 1;
        setNumber(current_number);
    }

    /**
     * decreases the number of coffees with one, to a minimum of @minOrder.
     *
     * @param view
     */
    public void decreaseQty(View view) {
        if (current_number <= minOrder) {
            current_number = minOrder;
        } else {
            current_number -= 1;
        }
        setNumber(current_number);
    }
}