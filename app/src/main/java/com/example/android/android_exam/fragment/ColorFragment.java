package com.example.android.android_exam.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_exam.R;

import java.util.Random;

/**
 * Created by student on 2015-09-15.
 */
public class ColorFragment extends Fragment {

    private ImageView mImageview;


    // View 를 만드는 곳
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_color, container, false);
        mImageview = (ImageView) view.findViewById(R.id.iv_image);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImageview.setBackgroundColor(getRandomColor());


    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void setColor(int color) {
        mImageview.setBackgroundColor(color);
    }
}