package com.example.roomdb_practice.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomdb_practice.R;
import com.example.roomdb_practice.adapter.CustomPagerAdapter;
import com.example.roomdb_practice.databinding.FragmentViewPagerBinding;
import com.google.android.material.tabs.TabLayout;


public class ViewPagerFragment extends Fragment implements DetailFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener {
    private CustomPagerAdapter adapter;
    public static ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentViewPagerBinding binding = FragmentViewPagerBinding.inflate(inflater, container, false);
        viewPager = binding.viewPager;
        tabLayout = binding.tabLayout;

        adapter = new CustomPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ListFragment(), "List ");
        adapter.addFragment(new InputFragment(), "Input ");
        adapter.addFragment(new FavoriteFragment(), "Farite ");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return binding.getRoot();
    }

    @Override
    public void onReplaceFragmentToInputFragment() {
        Fragment inputFragment = new InputFragment();
        adapter.replaceFragment(inputFragment, "Input ", 1);
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onReplaceFragmentToDetailFragment() {
        Fragment detailFragment = new DetailFragment();
        adapter.replaceFragment(detailFragment, "Detail ", 1);
        viewPager.setCurrentItem(1);

    }
}