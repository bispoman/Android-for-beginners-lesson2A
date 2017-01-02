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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
    EditText cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.orderBtn);
        btnPlus = (Button) findViewById(R.id.plusBtn);
        btnMinus = (Button) findViewById(R.id.minusBtn);
        cName = (EditText) findViewById(R.id.customerName);

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
        displayQuantity(numberOfCoffees);
        createOrderSummary();
    }

    public void createOrderSummary() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        String customerName = cName.getText().toString();
        priceTextView.setText("Name: "+customerName+"\nQuantity: "+numberOfCoffees+"\nTotal: "+NumberFormat.getCurrencyInstance().format(calculatePrice())+"\nThank you!");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.itemCount);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText("Foda-se " + NumberFormat.getCurrencyInstance().format(number));
    }

    private int calculatePrice() { return numberOfCoffees *5; }

    private void plusCount(View view) {
        numberOfCoffees = ++numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }
    private void lessCount(View view) {
        numberOfCoffees = --numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }
}