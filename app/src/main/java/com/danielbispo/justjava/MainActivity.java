package com.danielbispo.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees;
    Button btn1;
    Button btnPlus;
    Button btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.orderBtn);
        btnPlus = (Button) findViewById(R.id.plusBtn);
        btnMinus = (Button) findViewById(R.id.minusBtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder(getCurrentFocus(), numberOfCoffees);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lessCount(getCurrentFocus());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusCount(getCurrentFocus());
            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view, int numberOfCoffees) {
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 2);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.itemCount);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void plusCount(View view) {
        numberOfCoffees = ++numberOfCoffees;
        display(numberOfCoffees);
    }
    private void lessCount(View view) {
        numberOfCoffees = --numberOfCoffees;
        display(numberOfCoffees);
    }
}