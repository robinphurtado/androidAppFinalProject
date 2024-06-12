package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.db.ProductDAO;
import com.example.myapplication.db.ProductDatabase;
import com.example.myapplication.db.UserDatabase;

import java.util.ArrayList;
//Addshell class
public class AddShell extends AppCompatActivity {
    private static final String TAG = "AddShell";

   //needed member variables
    Button addButton;
    Button orderButton;
    Button editButton;
    Button deleteButton;
    EditText shellNameInput;
    EditText shellPriceInput;
    EditText shellDescriptionInput;
    EditText shellQtyInput;
    ProductDAO productDAO;
    ArrayList<Product> products;

//on create
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shell);
        Log.d(TAG, "onCreate: ");

//instance of productDAO
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "db-product")
                .allowMainThreadQueries()
                .build()
                .getProductDAO();



////wire up elements
        shellNameInput = findViewById(R.id.add_shell_ti_name);
        shellDescriptionInput = findViewById(R.id.add_shell_ti_description);
        shellPriceInput = findViewById(R.id.add_shell_ti_price);
        shellQtyInput = findViewById(R.id.add_shell_ti_qty);
        addButton = findViewById(R.id.add_shell_btn_add);
        editButton =findViewById(R.id.add_shell_btn_edit);

//go to order window when order is clicked?
        //find how to save the username of the item clicked, change its color
        //add that item to order database when order is clicked
            //prompt for empty selection

//onclick listener edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = shellNameInput.getText().toString();
                String description = shellDescriptionInput.getText().toString();
                String price = shellPriceInput.getText().toString();
                String qty = shellQtyInput.getText().toString();
                if (productName.isEmpty() || description.isEmpty() || price.isEmpty() || qty.isEmpty()) {
                    toast("Empty fields not allowed, please fill in all product details");
                } else {
                    Product shell = productDAO.getQuestionWithProductName(productName);
                    //if user exists toast user exists
                    if (shell == null) {
                        toast("Cannot edit, product does not exist");
                    } else {
                        editProduct();
                    }
                }
            }//end onClick View
        });//end onClickListener



// create new product object when info is entered and "Add" button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = shellNameInput.getText().toString();
                String description = shellDescriptionInput.getText().toString();
                String price = shellPriceInput.getText().toString();
                String qty = shellQtyInput.getText().toString();
                if (productName.isEmpty() || description.isEmpty() || price.isEmpty() || qty.isEmpty()) {
                    toast("Empty fields not allowed, please fill in all product details");
                } else {
                    Product shell = productDAO.getQuestionWithProductName(productName);
                    //if user exists toast user exists
                    if (shell != null) {
                        toast("That product already exists");
                    } else {
                        submitProduct();
                    }
                }
            }//end onClick View
        });//end onClickListener

    } //end oncCreate

    //

    private void submitProduct(){

        String productName = shellNameInput.getText().toString();
        String description = shellDescriptionInput.getText().toString();
        String strPrice = shellPriceInput.getText().toString();
        double price = Double.parseDouble(strPrice);
        String strQty = shellQtyInput.getText().toString();
        int qty = Integer.parseInt(strQty);


        productDAO.insert(new Product(productName, price, description,qty));
        toast(productName + " Added");


    }

    private void editProduct(){

        String productName = shellNameInput.getText().toString();
        String description = shellDescriptionInput.getText().toString();
        String strPrice = shellPriceInput.getText().toString();
        double price = Double.parseDouble(strPrice);
        String strQty = shellQtyInput.getText().toString();
        int qty = Integer.parseInt(strQty);

        Product currentProduct = productDAO.getQuestionWithProductName(productName);
        int currentProductId = currentProduct.getProductId();

        Product editedProduct = new Product(productName,price,description,qty);
        productDAO.update(editedProduct);

        toast(productName + " Edited");

    }


    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
