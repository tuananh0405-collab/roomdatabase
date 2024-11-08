package com.example.roomdb_practice.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.adapter.ProductFavoriteAdapter;
import com.example.roomdb_practice.databinding.FragmentFavoriteBinding;
import com.example.roomdb_practice.model.Product;
import com.example.roomdb_practice.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    private ProductFavoriteAdapter adapter;
    private ProductViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        binding.rcvFavorite.setHasFixedSize(true);
        binding.rcvFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProductFavoriteAdapter(new ArrayList<>());
        binding.rcvFavorite.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        viewModel.getListProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Product> products) {
                List<Product> favoriteProducts = products.stream()
                        .filter(product -> "yes".equals(product.isFavorite()))
                        .collect(Collectors.toList());
                adapter.setProductList(favoriteProducts);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setViewModel(viewModel);

        return binding.getRoot();
    }
}