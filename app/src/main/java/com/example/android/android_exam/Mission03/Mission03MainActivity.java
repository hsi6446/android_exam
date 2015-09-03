package com.example.android.android_exam.Mission03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_exam.R;

public class Mission03MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_main);

        mLoginButton = (Button)findViewById(R.id.login_button);

       mLoginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
               startActivityForResult(intent, REQUEST_CODE);

           }
       });

    }


}
