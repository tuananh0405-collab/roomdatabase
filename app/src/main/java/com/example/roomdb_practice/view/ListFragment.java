package com.example.roomdb_practice.view;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.roomdb_practice.adapter.ProductAdapter;
import com.example.roomdb_practice.databinding.FragmentListBinding;
import com.example.roomdb_practice.model.Product;
import com.example.roomdb_practice.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ProductAdapter adapter;
    private ProductViewModel viewModel;
    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onReplaceFragmentToDetailFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof DetailFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) parentFragment;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        binding.rcvProduct.setHasFixedSize(true);
        binding.rcvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Default is 0, observe will ... in repo
        adapter = new ProductAdapter(new ArrayList<>(), product -> {
            viewModel.setSelectedProduct(product);
            // open fragment
            mListener.onReplaceFragmentToDetailFragment();
        });
        binding.rcvProduct.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ProductViewModel.class);
        viewModel.getListProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProductList(products);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setViewModel(viewModel);

        return binding.getRoot();
    }
}