package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShowUsers extends AppCompatActivity {
    private static final String TAG = "ShowUsers";

    UserDAO userDAO;
    private List<User> users;
    private ArrayList<String> userNames = new ArrayList<>();

    Button addUserButton;
    Button deleteUserButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        Log.d(TAG, "onCreate: ");

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        // TODO: 12/17/2022   maybe turn this into a method initialize nameList or something
        users = userDAO.getAllUsers();
        int numUsers = users.size();

        for (int i=0; i<numUsers; i++){
            User currentUser = userDAO.getQuestionWithId(i+1);
            userNames.add(currentUser.getUserName());
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(this, userNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addUserButton = findViewById(R.id.show_users_btn_add);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked admin AddUserButton");

                Intent goToCreateAccount= new Intent(ShowUsers.this, CreateAccount.class);
                startActivity(goToCreateAccount);
            }
        });

        deleteUserButton = findViewById(R.id.show_users_btn_delete);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked admin DeleteUserButton");

                Intent goToDeleteAccount= new Intent(ShowUsers.this, DeleteAccount.class);
                startActivity(goToDeleteAccount);
            }
        });


    }


}
