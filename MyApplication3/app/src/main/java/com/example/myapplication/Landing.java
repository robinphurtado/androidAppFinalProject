package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;

public class Landing extends AppCompatActivity {

    TextView textUserName;
    Button adminButton;
    Button backButton;
    UserDAO userDAO;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "com.example.myapplication";
    private static final String USER_ID = "userId";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Log.d(TAG, "onCreate: Started");

    String username = getIntent().getStringExtra("username");


        adminButton = findViewById(R.id.btn_admin);
        textUserName = findViewById(R.id.landing_tv_username);
        textUserName.setText(username);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        int currentUserId = sharedPreferences.getInt(USER_ID, -1);
        Log.d(TAG, "onCreate: check userId " + currentUserId);

        User currentUser = userDAO.getQuestionWithId(currentUserId);

        String name = currentUser.getUserName();
        Log.d(TAG, "onCreate: username " + name);

        boolean currentIsAdmin = currentUser.isAdmin();
        Log.d(TAG, "onCreate: is Admin? " + currentIsAdmin);

        if(currentIsAdmin) {
            adminButton.setVisibility(View.VISIBLE);
        } else {
            adminButton.setVisibility(View.GONE);
        }

        backButton = (Button)findViewById(R.id.landing_button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked Login backButton");

                Intent backIntent = new Intent(Landing.this, Login.class);
                startActivity(backIntent);
            }
        });

        Button searchShellsButton = (Button)findViewById(R.id.btn_search_shells);
        searchShellsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(Landing.this, Search.class);
                startActivity(searchIntent);
            }
        });

        Button logoutButton = (Button)findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked Login backButton");

                Intent backIntent = new Intent(Landing.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

        ImageButton backArrowButton = (ImageButton) findViewById(R.id.landing_ibtn_back_arrow);

        backArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked Landing backArrowButton");

                Intent backIntent = new Intent(Landing.this, Login.class);
                startActivity(backIntent);
            }
        });

        adminButton = (Button)findViewById(R.id.btn_admin);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked Landing admin button");

                Intent adminIntent = new Intent(Landing.this, Admin.class);
                startActivity(adminIntent);
            }
        });

    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
