package com.example.roomdb_practice.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomdb_practice.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ProductRepository {
    private ProductDao productDao;
    private MutableLiveData<List<Product>> allProducts = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<Product>> favoriteProducts = new MutableLiveData<>(new ArrayList<>());

    public ProductRepository(Application application) {
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.productDao();
        Executors.newSingleThreadExecutor().execute(() -> {
            allProducts.postValue(productDao.getAllProducts());
            favoriteProducts.postValue(productDao.getFavoriteProducts());
        });
    }

    public void insert(Product product) {
        new InsertProductAsyncTask(productDao).execute(product);
    }

    public void update(Product product) {
        new UpdateProductAsyncTask(productDao).execute(product);
    }

    public MutableLiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<List<Product>> getFavoriteProducts() {
        return favoriteProducts;
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private UpdateProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.update(products[0]);
            return null;
        }
    }
}
