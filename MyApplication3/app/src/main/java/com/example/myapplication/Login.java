package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "com.example.myapplication";
    private static final String USER_ID = "userId";
    private static final String TAG = "Login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started");

        usernameInput = findViewById(R.id.login_ti_username);
        passwordInput = findViewById(R.id.login_ti_password);
        loginButton = findViewById(R.id.login_btn_login);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //check if shared preferences already
        int existingUserId = sharedPreferences.getInt(USER_ID, -1);
 //activate this later
//        if(existingUserId != null){
//            Intent intent = new Intent(Login.this, Landing.class);
//        }


        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

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
                        //maybe write a check for no userId
                        //retrieve userId for user whose username is entered
                        User currentUser = userDAO.getQuestionWithUserName(username);
                        int currentUserId = currentUser.getUserId();

                        //when click login button, put data on the shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(USER_ID, currentUserId);
                        editor.apply();

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
