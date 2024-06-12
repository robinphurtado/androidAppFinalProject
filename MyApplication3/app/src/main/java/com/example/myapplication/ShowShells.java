package com.example.myapplication;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.db.OrderDAO;
import com.example.myapplication.db.OrderDatabase;
import com.example.myapplication.db.ProductDAO;
import com.example.myapplication.db.ProductDatabase;
import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShowShells extends AppCompatActivity {
    private static final String TAG = "ShowShells";

    ProductDAO productDAO;
    OrderDAO orderDAO;
    private List<Product> products;
    private ArrayList<String> productNames = new ArrayList<>();
    TextInputEditText shellNameInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shells);
        Log.d(TAG, "onCreate: ");

        Button orderShellButton = (Button)findViewById(R.id.showshells_btn_order);
        Button addShellButton = (Button)findViewById(R.id.showshells_btn_add);
        Button editShellButton = (Button)findViewById(R.id.showshells_btn_edit);
        Button deleteShellButton = (Button)findViewById(R.id.showshells_btn_delete);
        shellNameInput = (TextInputEditText)findViewById(R.id.showshells_ti_shell);

        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "db-product")
                .allowMainThreadQueries()
                .build()
                .getProductDAO();

        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "db-order")
                .allowMainThreadQueries()
                .build()
                .getOrderDAO();

        initRecyclerView();



        //when the add button is clicked
        addShellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: addShellbutton clicked");
                Intent goToAddShellIntent = new Intent(ShowShells.this, AddShell.class);
                startActivity(goToAddShellIntent);
            }
        });

        editShellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: editShell");
                Intent goToAddShellIntent = new Intent(ShowShells.this, AddShell.class);
                startActivity(goToAddShellIntent);
            }
        });

        orderShellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: orderShell");

                String shellName = shellNameInput.getText().toString();

                if (shellName.isEmpty()) {
                    toast("Shell name cannot be empty");
                } else {
                    Product shell = productDAO.getQuestionWithProductName(shellName);
                    //if user exists toast user exists
                    if (shell == null) {
                        toast("That shell is not available ");
                    } else {
                        orderProduct();
                    }
                }




                Intent goToLandingIntent = new Intent(ShowShells.this, Landing.class);
                startActivity(goToLandingIntent);
            }//end onClick View
        });//end onClickListener

    }



    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        products = productDAO.getAllProducts();
        int numProducts = products.size();

        for (int i=0; i<numProducts; i++){
            Product currentProduct = productDAO.getQuestionWithProductId(i+1);
            productNames.add(currentProduct.getProductName());
        }

        RecyclerView recyclerView = findViewById(R.id.showshells_recyclerv);

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(this, productNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void orderProduct(){

        String productName = shellNameInput.getText().toString();
        Date today = Calendar.getInstance().getTime();

        Product currentProduct = productDAO.getQuestionWithProductName(productName);
        int currentProductId = currentProduct.getProductId();
        //need to use shared preferences to get the userId for current user

        Order newOrder= new Order(1, currentProductId, 1 , today);


        orderDAO.insert(newOrder);

        toast("1 " + productName + " Ordered");

    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
