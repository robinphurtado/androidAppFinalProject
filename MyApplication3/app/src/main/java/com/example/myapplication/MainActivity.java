package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.db.ProductDAO;
import com.example.myapplication.db.ProductDatabase;
import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    UserDAO userDAO;
    ProductDAO productDAO;
 //   SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started Main");

        Button mainLoginButton = (Button) findViewById(R.id.activity_main_btn_login);
        Button mainCreateAccountButton = (Button) findViewById(R.id.activity_main_btn_create_account);

        //populate product database on first run
        productDAO = Room.databaseBuilder(this, ProductDatabase.class, "db-product")
                .allowMainThreadQueries()
                .build()
                .getProductDAO();

        List<Product> products = productDAO.getAllProducts();
        if(products.size()<=0){
            Product sandDollar = new Product("Sand Dollar", 2.00, "Sand Dollar collected from Southern California beaches", 10);
            Product conch = new Product("Conch", 15.00, "Conch shell collected from Florida beaches", 2);
            Product scallop = new Product("Scallop", 2.00, "Scallop shells collected from various beaches", 20);
            Product seaStar = new Product("Sea Star", 5.00, "Sea star collected from Southern California beaches", 5);
            productDAO.insert(sandDollar, conch, scallop, seaStar);

            Log.d(TAG, "onCreate: product database created");
        }


        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        //do we have any users at all?
        List<User> users = userDAO.getAllUsers();
        if(users.size()<=0){
            User defaultUser = new User("testuser1", "testuser1");
            Log.d(TAG, "onCreate: testUser " + defaultUser.getUserId() + " username " + defaultUser.getUserName() + " isAmdin? " + defaultUser.isAdmin());
            User defaultAdmin = new User("admin2", "admin2");
            defaultAdmin.setAdmin(true);
            Log.d(TAG, "onCreate: testUser " + defaultAdmin.getUserId() + " username " + defaultAdmin.getUserName() + " isAmdin? " + defaultAdmin.isAdmin());
            userDAO.insert(defaultUser, defaultAdmin);
            Log.d(TAG, "onCreate: new users created");
        }

 //

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

    public void saveData(){
        
    }

    private void addUserToPreference(){

    }
}
