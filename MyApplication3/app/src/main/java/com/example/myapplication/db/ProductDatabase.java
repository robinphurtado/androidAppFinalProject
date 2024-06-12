package com.example.myapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Product;

@Database(entities = Product.class, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDAO getProductDAO();

}
