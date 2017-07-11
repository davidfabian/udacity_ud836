package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int current_number = 1;
    int price = 5;
    int whippedCreamPrice = 1;
    int chocoPrice = 2;
    String name_entered;
    String orderAddress = "order@justjava.com";
    private int minOrder = 1;
    private int maxOrder = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked. Tries to create a custom email message to orderAddress with a custom message based on the order details.
     */
    public void submitOrder(View view) {
//  /**
//  * generates the order summary on the screen
//  */
//
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(createOrderSummary(priceDisplay()));
        Intent sendOrderMail = new Intent(Intent.ACTION_SENDTO);
        sendOrderMail.setData(Uri.parse("mailto:" + orderAddress));
        sendOrderMail.putExtra(Intent.EXTRA_SUBJECT, "Order for: " + getName_entered());
        sendOrderMail.putExtra(Intent.EXTRA_TEXT, createOrderSummary(priceDisplay()));

        if (sendOrderMail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendOrderMail);
        }
    }

    /**
     * It creates the summary string for the order
     *
     * @param totalcost is the cost of the purchase
     * @return returns the complete, formatted string with the custom message
     */
    private String createOrderSummary(int totalcost) {
        return ("Name: " + getName_entered() +
                "\n" + getResources().getString(R.string.quantity) + ": " + current_number +
                "\n" + getResources().getString(R.string.whipped_cream) + ": " + whippedCream() +
                "\n" + getResources().getString(R.string.chocolate) + ": " + chocolateAdd() +
                "\n" + getResources().getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(totalcost) +
                "\n" + getResources().getString(R.string.thankyou));

    }

    /**
     * extracts the name from the editText view
     *
     * @return the name entered
     */
    private String getName_entered() {
        TextView nameField = (TextView) findViewById(R.id.name_input);
        name_entered = nameField.getText().toString();
        return name_entered;
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
        if (current_number >= maxOrder) {
            current_number = maxOrder;
            Toast.makeText(this, "You can't order more than " + maxOrder + " coffees at a time.", Toast.LENGTH_SHORT).show();
        } else {
            current_number += 1;
        }
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
            Toast.makeText(this, "You can't order less than " + minOrder + " coffees at a time.", Toast.LENGTH_SHORT).show();
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