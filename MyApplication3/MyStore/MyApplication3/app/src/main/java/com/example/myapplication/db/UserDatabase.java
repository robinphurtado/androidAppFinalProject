package com.example.myapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.User;
import com.example.myapplication.typeconverter.BooleanTypeConverter;

@Database(entities = {User.class}, version = 2)
//changes in version 2, added isAdmin as a member of User Entity and added BooleanTypeConverter
@TypeConverters(BooleanTypeConverter.class)
public abstract class UserDatabase extends RoomDatabase {
    //public static final String dbName = "db-user";

    public abstract UserDAO getUserDAO();


}
