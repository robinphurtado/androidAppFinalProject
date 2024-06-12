package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        Button mainLoginButton = (Button) findViewById(R.id.activity_main_btn_login);
        Button mainCreateAccountButton = (Button) findViewById(R.id.activity_main_btn_create_account);

        mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked mainLoginButton");

                Intent goToLogin = new Intent(MainActivity.this, Login.class);
                startActivity(goToLogin);
            }
        });

        mainCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked mainCreateAccountButton");

                Intent goToCreateAccount= new Intent(MainActivity.this, CreateAccount.class);
                startActivity(goToCreateAccount);
            }
        });
    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
