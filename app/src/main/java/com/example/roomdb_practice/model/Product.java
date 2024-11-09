package com.example.roomdb_practice.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class Product  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String price;
    private String isFavorite = "no";
    private int imageResource;

    public Product(String name, String description, String price, String isFavorite, int imageResource) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isFavorite = isFavorite;
        this.imageResource = imageResource;
    }

    public String isFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
    public int getImageResource() {
        return imageResource;
    }
}
