package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    User getQuestionWithId(int userId);

    @Query("SELECT * from user_table WHERE userName = (:username) and password = (:password) ")
    User getQuestionWithIdPassword(String username, String password);

    @Query("SELECT * FROM user_table WHERE userName = :userName")
    User getQuestionWithUserName(String userName);

}
