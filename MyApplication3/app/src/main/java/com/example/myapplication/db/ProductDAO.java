package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);

    @Query("SELECT * FROM product_table")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product_table WHERE productId = :productId" )
    Product getQuestionWithProductId(int productId);

    @Query("SELECT * FROM product_table WHERE productName = :productName" )
    Product getQuestionWithProductName(String productName);

    @Query("SELECT * FROM product_table WHERE quantityAvailable = :quantityAvailable" )
    Product getQuestionWithQuantityAvailable(int quantityAvailable);

}
