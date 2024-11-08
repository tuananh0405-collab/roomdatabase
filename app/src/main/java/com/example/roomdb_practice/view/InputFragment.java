package com.example.roomdb_practice.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.databinding.FragmentInputBinding;
import com.example.roomdb_practice.viewmodel.ProductViewModel;

public class InputFragment extends Fragment {
    private FragmentInputBinding binding;
    private ProductViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ProductViewModel.class);
        binding.setViewModel(viewModel);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addProduct();
                // set current item to 0
                ViewPagerFragment.viewPager.setCurrentItem(0);
            }
        });
        return binding.getRoot();
    }
}