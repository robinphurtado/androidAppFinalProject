package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;


public class ViewOrders extends AppCompatActivity {

    private static final String TAG = "ViewOrders";
    ProductDAO productDAO;
    UserDAO userDAO;
    OrderDAO orderDAO;
    private List<Order> orders;
    private ArrayList<String> orderNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: Started");
        setContentView(R.layout.activity_show_orders);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "db-product")
                .allowMainThreadQueries()
                .build()
                .getProductDAO();

        orderDAO = Room.databaseBuilder(this, OrderDatabase.class, "db-order")
                .allowMainThreadQueries()
                .build()
                .getOrderDAO();

        initRecyclerView();


    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        orders = orderDAO.getAllOrders();
        int numOrders = orders.size();

        for (int i=0; i<numOrders; i++){
            Order currentOrder = orderDAO.getQuestionWithProductId(i+1);
            int orderedProduct = currentOrder.getProductId();
            int quantyOrdered = currentOrder.getOrderQuantity();
            String strqtyOrdered = Integer.toString(quantyOrdered);
            Product productOrdered = productDAO.getQuestionWithProductId(orderedProduct);
            String orderedProductName = productOrdered.getProductName();
            orderNames.add(orderedProductName + " " + strqtyOrdered);
        }

        RecyclerView recyclerView = findViewById(R.id.view_order_recyclerv);

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(this, orderNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
