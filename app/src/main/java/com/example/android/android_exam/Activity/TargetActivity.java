package com.example.android.android_exam.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.android_exam.R;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        Toast.makeText(TargetActivity.this, "name : " + name + "phone : " + phone, Toast.LENGTH_SHORT).show();

        findViewById(R.id.finish_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        //Activity 종료
        finish();
    }
}
