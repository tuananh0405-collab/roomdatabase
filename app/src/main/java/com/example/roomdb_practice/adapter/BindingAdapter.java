package com.example.roomdb_practice.adapter;

import android.widget.ImageView;

import com.example.roomdb_practice.R;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        if(resource != 0){
            imageView.setImageResource(resource);
        }else{
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
    @androidx.databinding.BindingAdapter("favoriteStatus")
    public static void setFavoriteIcon(ImageView imageView, String isFavorite) {
        if ("yes".equals(isFavorite)) {
            imageView.setImageResource(R.drawable.ic_star_gray);
        } else {
            imageView.setImageResource(R.drawable.ic_star_white);
        }
    }
}
