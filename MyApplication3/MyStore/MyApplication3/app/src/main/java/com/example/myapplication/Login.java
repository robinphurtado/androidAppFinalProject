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
import java.util.function.ToDoubleBiFunction;

public class Login extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    UserDAO userDAO;
    Button backButton;
    Button loginButton;
    List<User>  users;

    private static final String TAG = "Login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started");

        usernameInput = findViewById(R.id.login_ti_username);
        passwordInput = findViewById(R.id.login_ti_password);
        loginButton = findViewById(R.id.login_btn_login);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

 //fix this so it logs in instead of registers
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked Login button");

                //if empty, display toast
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    toast("Empty username or password not allowed");
                } else {
                    User user = userDAO.getQuestionWithIdPassword(username, password);
                    if(user == null){
                        toast("Invalid username or password");
                    } else {
                        Intent goToLanding = new Intent(Login.this, Landing.class).putExtra("username", username);
                        startActivity(goToLanding);
                    }
                }

            }

        });

        backButton = (Button)findViewById(R.id.login_btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked Login backButton");

                Intent backIntent = new Intent(Login.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

        ImageButton backArrowButton = (ImageButton) findViewById(R.id.login_ibtn_back_arrow);

        backArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked Login backArrowButton");

                Intent backIntent = new Intent(Login.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
