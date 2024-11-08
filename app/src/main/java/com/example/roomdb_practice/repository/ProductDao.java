package com.example.roomdb_practice.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb_practice.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * FROM product_table")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product_table WHERE isFavorite = 'yes'")
    List<Product> getFavoriteProducts();
}
