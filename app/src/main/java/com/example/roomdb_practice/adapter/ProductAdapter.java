package com.example.roomdb_practice.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.databinding.ItemProductBinding;
import com.example.roomdb_practice.model.Product;
import com.example.roomdb_practice.viewmodel.ProductViewModel;

import java.util.List;
import java.util.function.Consumer;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Consumer<Product> onProductClick;
    private ProductViewModel viewModel;

    public void setViewModel(ProductViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ProductAdapter(List<Product> productList, Consumer<Product> onProductClick) {
        this.productList = productList;
        this.onProductClick = onProductClick;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using data binding
        ItemProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
        holder.binding.setViewModel(viewModel);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            binding.setProduct(product);
            binding.executePendingBindings();
            itemView.setOnClickListener(v -> onProductClick.accept(product));
            // Set up the click listener for favoriteTextView
            binding.favoriteTextView.setOnClickListener(v -> {
                // Toggle favorite status
                String currentStatus = product.isFavorite();
                String newStatus = currentStatus.equals("no") ? "no" : "yes";

                // Update the product object
                product.setFavorite(newStatus);
                binding.setProduct(product);
                // Update the TextView
                binding.favoriteTextView.setText(newStatus);

                // Notify ViewModel or Repository about the change
                // Assuming viewModel is accessible in this context
                viewModel.toggleFavoriteStatus(product);
            });
        }

    }
}
