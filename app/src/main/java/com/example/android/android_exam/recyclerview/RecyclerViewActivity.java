package com.example.android.android_exam.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_exam.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // 코드로 fragment 를 바꾸는 법.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new RecyclerViewFragment())
                .commit();
    }
}
