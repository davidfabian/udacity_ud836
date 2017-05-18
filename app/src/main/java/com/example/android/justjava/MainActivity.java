package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int current_number = 1;
    int price = 5;
    int whippedCreamPrice = 1;
    int chocoPrice = 2;
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
        return ("Name: Director David" +
                "\n" + getResources().getString(R.string.quantity) + ": " + current_number +
                "\n" + getResources().getString(R.string.whipped_cream) + ": " + whippedCream() +
                "\n" + getResources().getString(R.string.chocolate) + ": " + chocolateAdd() +
                "\n" + getResources().getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(totalcost) +
                "\n" + getResources().getString(R.string.thankyou));

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
        int currentPrice = price;
        if (whippedCream()) {
            currentPrice += whippedCreamPrice;
        }
        if (chocolateAdd()) {
            currentPrice += chocoPrice;
        }

        return current_number * currentPrice;

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

    public boolean whippedCream() {
        CheckBox whippedCB = (CheckBox) (findViewById(R.id.whipped_cream_cb));
        return whippedCB.isChecked();
    }

    public boolean chocolateAdd() {
        CheckBox chocoCB = (CheckBox) (findViewById(R.id.chocolate_cb));
        return chocoCB.isChecked();
    }
}