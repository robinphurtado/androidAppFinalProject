package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Order;
import com.example.myapplication.Product;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    void insert(Order... orders);

    @Update
    void update(Order... orders);

    @Delete
    void delete(Order... orders);

    @Query("SELECT * FROM order_table")
    List<Order> getAllOrders();

//    @Query("DELETE FROM order_table")
//    List<Order> deleteAllOrders();

    @Query("SELECT * FROM order_table WHERE orderId = :orderId")
    Order getQuestionWithOrderId(int orderId);

    @Query("SELECT * FROM order_table WHERE userId = :userId")
    Order getQuestionWithUserId(int userId);

    @Query("SELECT * FROM order_table WHERE productId = :productId")
    Order getQuestionWithProductId(int productId);

    @Query("SELECT * FROM order_table WHERE orderDate = :orderDate")
    Order getQuestionWithOrderDate(int orderDate);

}
