package com.example.roomdb_practice.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        binding.btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog();
            }});
        return binding.getRoot();
    }

    private void showImageSelectionDialog() {
        final String[] imageOptions = {"Bacon", "Chicken", "Ranch", "Beef", "Berry"};
        final int[] imageResources = {
                R.drawable.bacon_wrapped,
                R.drawable.bbq_chicken,
                R.drawable.bbq_ranch,
                R.drawable.beef_stir_fry,
                R.drawable.berry_blast
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select Product Image")
                .setItems(imageOptions, (dialog, which) -> {
                    int selectedImage = imageResources[which];
                    viewModel.setSelectedImageResource(selectedImage);
                    ImageView productImageView = getView().findViewById(R.id.imv_image);
                    productImageView.setImageResource(selectedImage);
                });
        builder.show();
    }
}