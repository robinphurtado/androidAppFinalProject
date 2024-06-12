package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;

import java.util.List;

public class CreateAccount extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    UserDAO userDAO;
    Button createAccountButton;
    List<User> users;

    private static final String TAG = "CreateAccount";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Log.d(TAG, "onCreate: Started");

        usernameInput = findViewById(R.id.ca_ti_username);
        passwordInput = findViewById(R.id.ca_ti_password);
        createAccountButton = findViewById(R.id.ca_btn_create_account);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 12/5/2022 check for blank,set a max pwd length, display toasts if they are bad
                submitUser();
            }

        });

        Button backButton = (Button)findViewById(R.id.ca_btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked CreateAccount backButton");

                Intent backIntent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

        ImageButton backArrowButton = (ImageButton) findViewById(R.id.ca_ibtn_back_arrow);

        backArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked CreateAccount backArrowButton");

                Intent backIntent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
// TODO: 12/5/2022 pull similar code from login to check for empty and set a max length for each. toast if violate
//    private void validateUser(){
//
//        String username = usernameInput.getText().toString();      
//        String password = passwordInput.getText().toString();
//
//        userDAO.insert(new User(username, password));
//    }

    private void submitUser(){

        String username = usernameInput.getText().toString();
//        if

        String password = passwordInput.getText().toString();

        userDAO.insert(new User(username, password));
        toast("Successfully Registered");

        Intent loginIntent = new Intent(CreateAccount.this, Login.class);
        startActivity(loginIntent);

    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
