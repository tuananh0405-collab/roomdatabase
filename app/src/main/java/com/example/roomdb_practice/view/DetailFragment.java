package com.example.roomdb_practice.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.databinding.FragmentDetailBinding;
import com.example.roomdb_practice.viewmodel.ProductViewModel;


public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private ProductViewModel viewModel;

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onReplaceFragmentToInputFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) parentFragment;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        binding.setViewModel(viewModel);
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateSelectedProduct();
                mListener.onReplaceFragmentToInputFragment();
                // replace to add and set current to 0
                ViewPagerFragment.viewPager.setCurrentItem(0);

            }
        });
        return binding.getRoot();
    }
}