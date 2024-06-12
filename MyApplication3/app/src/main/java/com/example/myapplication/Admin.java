package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {
    private static final String TAG = "Admin";

    private static final String SHARED_PREF_NAME = "com.example.myapplication";
    private static final String USER_ID = "userId";


    Button userManagementButton;

    Button viewOrdersButton;
    Button viewInventoryButton;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //wire up the rest of the stff
        userManagementButton = findViewById(R.id.btn_user_management);

        userManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToShowUsers = new Intent(Admin.this, ShowUsers.class);
                startActivity(goToShowUsers);
            }

        });


        viewInventoryButton = findViewById(R.id.btn_view_inventory);

        viewInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToInventory = new Intent(Admin.this, ShowShells.class);
                startActivity(goToInventory);
            }

        });


        // TODO: 12/10/2022  Onclick lisnter usermangement button


//  go to view orders
//        viewOrdersButton = findViewById(R.id.btn_view_orders);
//
//        viewOrdersButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToViewOrders = new Intent(Admin.this, ViewOrders.class);
//                startActivity(goToViewOrders);
//            }
//        });

//
    }

}
