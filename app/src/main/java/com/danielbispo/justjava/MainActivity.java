package com.danielbispo.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
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
    private CheckBox mCreamBox, mChocBox;
    private String mCream = "No", mChoc = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.orderBtn);
        btnPlus = (Button) findViewById(R.id.plusBtn);
        btnMinus = (Button) findViewById(R.id.minusBtn);
        cName = (EditText) findViewById(R.id.customerName);
        mCreamBox = (CheckBox) findViewById(R.id.checkBox);
        mChocBox = (CheckBox) findViewById(R.id.checkBoxChoc);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();
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

        mCreamBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCreamBox.isChecked()) {
                    mCream = "Yes";
                } else {
                    mCream = "No";
                }
                ;
            }
        });

        mChocBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mChocBox.isChecked()) {
                    mChoc = "Yes";
                } else {
                    mChoc = "No";
                }
                ;
            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(createOrderSummary());
    }

    /**
     * This method creates the Order summary and returns it as a string.
     * @return Order description string.
     */
    public String createOrderSummary() {
        String customerName = cName.getText().toString();
        String orderSummary = "Name: " + customerName;
        orderSummary += "\nAdd WhipCream? " + mCream;
        orderSummary += "\nAdd Chocolate? " + mChoc;
        orderSummary += "\nQuantity: " + numberOfCoffees;
        orderSummary += "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice());
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.itemCount);
        quantityTextView.setText("" + number);
    }

    /**
     * This method calculates the final price
     * @return int with the price.
     */
    private int calculatePrice() {
        int toppings = calcTopping();
        int finalprice = numberOfCoffees * (5 + toppings);
        return finalprice;
    }

    /**
     * This method calculates the price for all the toppings
     * @return int with the price of all toppings.
     */
    private int calcTopping() {
        int price = 0;
        if (mCreamBox.isChecked()) {
            price += 1;
        }
        if (mChocBox.isChecked()) {
            price += 2;
        }
        return price;
    }

    /**
     * adds one to the quantity counter
     */
    private void plusCount(View view) {
        numberOfCoffees = ++numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }

    /**
     * minus one on the quantity counter
     */
    private void lessCount(View view) {
        numberOfCoffees = --numberOfCoffees;
        displayQuantity(numberOfCoffees);
    }


}