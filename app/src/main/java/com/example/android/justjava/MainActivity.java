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
     * This method is called when the order button is clicked. Displays a custom message.
     */
    public void submitOrder(View view) {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(createOrderSummary(priceDisplay()));
    }

    /**
     * It creates and prints the summary string for the order
     *
     * @param totalcost is the cost of the purchase
     * @return returns the complete, formatted string with the custom message
     */
    private String createOrderSummary(int totalcost) {
        return ("Name: Director David" + "\n" + "Quantity: " + current_number + "\n" + "Total: " + NumberFormat.getCurrencyInstance().format(totalcost) + "\n" + "Thank you!");

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
     */
    private int priceDisplay() {
        return current_number * 5;

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