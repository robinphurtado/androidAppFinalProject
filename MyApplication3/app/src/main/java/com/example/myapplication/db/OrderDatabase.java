package com.example.myapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.Order;
import com.example.myapplication.typeconverter.DateTypeConverter;

@Database(entities = Order.class, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class OrderDatabase extends RoomDatabase {

    public abstract OrderDAO getOrderDAO();

}
