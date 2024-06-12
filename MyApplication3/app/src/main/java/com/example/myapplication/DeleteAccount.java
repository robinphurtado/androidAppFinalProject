package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.db.UserDAO;
import com.example.myapplication.db.UserDatabase;

import java.util.List;

public class DeleteAccount extends AppCompatActivity {
    private static final String TAG = "DeleteAccount";

    EditText usernameInput;
    UserDAO userDAO;
    List<User> users;
    Button deleteAccountButton;
    //SharedPreferences preferences

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        Log.d(TAG, "onCreate: ");

        usernameInput = findViewById(R.id.da_ti_username);
        deleteAccountButton = findViewById(R.id.da_btn_delete_account);

        userDAO = Room.databaseBuilder(this, UserDatabase.class, "db-user")
                .allowMainThreadQueries()
                .build()
                .getUserDAO();


        //when delete button is clicked
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();

                //check if username is empty
                if (username.isEmpty()){
                    //display toast message if empty
                    toast("Please enter a username to delete or go back to cancel");
                } else {
                    //try to retrieve user in the dao with the user name input,
                    User user = userDAO.getQuestionWithUserName(username);
                    //if user does not exist, toast that user not found
                    if (user == null) {
                        toast("User " + username + "does not exist. Delete Failed");
                    }else if ( username.equals("admin2")){
                        //check for the default admin and toast cannot be deleted
                        toast("Cannot delete default admin");
                    }else {
                        //if user does exist, delete user and toast deleted
                        userDAO.delete(user);
                        toast("User " + username + " Deleted");



//                        Intent goToAdminIntent  = new Intent(DeleteAccount.this, Admin.class);
//                        startActivity(goToAdminIntent);
                    }
                }
            }
        });

        //try to retrieve user with usernameInput  matches a user in the dao,
        //if user is null toast
        //if user is found, delete user

        // TODO: 12/17/2022 it would be a good idea to have a dialog box confirming delete


    }

    private void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
