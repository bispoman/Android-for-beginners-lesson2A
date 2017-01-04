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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees;
    private Button btn1, btnPlus, btnMinus;
    private EditText cName;
    private CheckBox mBox;
    private String mCream = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.orderBtn);
        btnPlus = (Button) findViewById(R.id.plusBtn);
        btnMinus = (Button) findViewById(R.id.minusBtn);
        cName = (EditText) findViewById(R.id.customerName);
        mBox = (CheckBox) findViewById(R.id.checkBox);


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

        mBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mBox.isChecked()) { mCream = "Yes";} else { mCream = "No";};
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
        priceTextView.setText("Name: "+customerName+"\nAdd WhipCream? "+mCream+"\nQuantity: "+numberOfCoffees+"\nTotal: "+NumberFormat.getCurrencyInstance().format(calculatePrice())+"\nThank you!");
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

    private int calculatePrice() {
        int toppings = calcTopping();
                int finalprice = (numberOfCoffees *5)+toppings;
        return finalprice;
    }

    private int calcTopping() {
        int price=0;
        if (mBox.isChecked()) {price=+2;}
        return price;
    }

    private void plusCount(View view) {
        numberOfCoffees = ++numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }
    private void lessCount(View view) {
        numberOfCoffees = --numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }


}