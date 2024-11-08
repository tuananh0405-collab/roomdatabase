package com.example.roomdb_practice.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.model.Product;
import com.example.roomdb_practice.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private static final String TAG = "TAGTAGTAG";
    private ProductRepository repository;
    private MutableLiveData<List<Product>> listProducts;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<String> isFavorite = new MutableLiveData<>("no");
    private MutableLiveData<Product> selectedProduct = new MutableLiveData<>(new Product("", "", "", "no"));
    private Application application;

    public void setApplication(Application application) {
        this.application = application;
    }

    public LiveData<List<Product>> getListProducts() {
        if (listProducts == null) {
            repository = new ProductRepository(this.application);
            listProducts = repository.getAllProducts();
        }

        return listProducts;
    }

    public LiveData<Product> getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        selectedProduct.setValue(product);
    }

    public void addProduct() {
        String productName = name.getValue();
        String productPrice = price.getValue();
        String productDescription = description.getValue();
        String favorite = isFavorite.getValue();

        if (productName != null && !productName.isEmpty() && productDescription != null && !productDescription.isEmpty() &&
                productPrice != null && !productPrice.isEmpty()) {

            Product newProduct = new Product(productName, productDescription, productPrice, favorite);

            List<Product> currentList = listProducts.getValue();
            if (currentList != null) {
                currentList.add(newProduct);
                listProducts.setValue(currentList);
                repository.insert(newProduct);
            }
            name.setValue("");
            price.setValue("");
            description.setValue("");
        }
    }

    public void updateSelectedProduct() {
        Product product = selectedProduct.getValue();
        Log.d(TAG, "updateSelectedProduct: " + product.toString());
        if (product != null) {
            repository.update(product);
            List<Product> currentList = listProducts.getValue();
            if (currentList != null) {
                for (int i = 0; i < currentList.size(); i++) {
                    if (currentList.get(i).equals(product)) {
                        currentList.set(i, product);
                        listProducts.setValue(currentList);
                        break;
                    }
                }
            }
        }
    }

    public void toggleFavoriteStatus(Product product) {
        String newFavoriteStatus = product.isFavorite().equals("yes") ? "no" : "yes";
        product.setFavorite(newFavoriteStatus);
        repository.update(product);
    }
}
