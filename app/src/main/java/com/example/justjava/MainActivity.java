/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int Quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void Increment(View view) {
        if(Quantity==100)
        {
            Toast.makeText(this,"you can't Have Less Den 100 coffee",Toast.LENGTH_SHORT).show();
            return;
        }

        Quantity = Quantity + 1;
        displayQuantity(Quantity);

    }

    /**
     * This method is called when the - button is clicked.
     */
    public void Decrement(View view) {
        if(Quantity==1)
        {
            Toast.makeText(this,"you can't Have Less Den 1 Coffee",Toast.LENGTH_SHORT).show();
            return;
        }

        Quantity = Quantity - 1;
        displayQuantity(Quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

      EditText nameField=(EditText) findViewById(R.id.Name_field);
        String name=nameField.getText().toString();
        Log.v("MainActivity","Name:"+name);

        /**
         * Here's some more detailed info for each step:
         *
         * When the button is clicked, find the CheckBox view, get checked state from the CheckBox, and store the checked state value in a new boolean variable. Feel free to add a log message to verify that step 1 is completed correctly.
         *
         * Pass the checked state boolean into the createOrderSummary() method, so it takes in 2 input parameters. The new input parameter is a boolean called hasWhippedCream. Remember to modify the method signature of the method. Here's a link to the video discussion adding and removing parameters.
         *
         * Modify the createOrderSummary() method so it displays this text on screen using the boolean input parameter.
         * Remove log messages that were added for debugging purposes
         */
        CheckBox WhipperCreameCheckBox= (CheckBox) findViewById(R.id.Whipped_creame_checkBox);
        boolean hasWhipperCreame=WhipperCreameCheckBox.isChecked();
        Log.v("MainActivity","HasWhipperCreame?"+hasWhipperCreame);

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        //display(NumberOfCoffes);
        int Price = CalculatePrice(hasChocolate,hasWhipperCreame) ;
        String Pricmsg=CreateOrdreSummery(name,Price,hasWhipperCreame,hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);//ACTION_SEND==constant
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, " Coffee for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,Pricmsg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        // When U Call displayMessage 157 line u used this Function DisplaydisplayMessage(Pricmsg);



    }
    //Cal culate Price od the Oreder
    //Return

    private int CalculatePrice(boolean addwippedcreame,boolean addchocklate){
       int BasePrice=8;
       //add $1 User want coffe
       if(addwippedcreame){
           BasePrice+=1;
       }

       if (addchocklate){
           BasePrice+=2;
       }
       return Quantity*BasePrice;

    }

    /**create oreder summery
     *
     * @param Price fds
     * @return text summery
     */
    private String CreateOrdreSummery( String name,int Price,boolean addwhipperCream, boolean addChocolate)
    {

       String Pricmsg="Name:"+name+"\nAdd WhippedCreame?"+addwhipperCream;
        Pricmsg += "\nAdd chocolate? " + addChocolate;
        Pricmsg+="\nQuantity:"+Quantity;
        Pricmsg+= "\nTotal: $" + Price;
        Pricmsg+=   "\nThank You!";
        return Pricmsg;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberberOfCoffes) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);//id return a view and we cast into a txt view
        quantityTextView.setText("" + numberberOfCoffes);
    }



    /**This is a Display Method
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView OrderSummeryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummeryTextView.setText(message);
    }

    use xml File When use It upr function

      <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="14dp"
            android:layout_marginBottom="16dp"
            android:text="oreder summery"
            android:textAllCaps="true" />

        <TextView
            android:id="@+id/order_summary_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="$0"
            android:textColor="#111111"
            android:textSize="16sp" />

     */
}
