package com.example.android.android_exam.Mission03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.android_exam.R;

public class Mission03MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    private Button mLoginButton;
    private EditText mIdBtn;
    private EditText mPassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_main);
        mIdBtn = (EditText) findViewById(R.id.id_edit_text);
        mPassBtn = (EditText) findViewById(R.id.password_edit_text);
        mLoginButton = (Button)findViewById(R.id.login_button);

       mLoginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (mIdBtn.getTextSize() <= 0 || mPassBtn.getTextSize() <= 0) {

                   Toast.makeText(Mission03MainActivity.this, "입력해주세요", Toast.LENGTH_SHORT).show();
               } else {
                   Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                   startActivityForResult(intent, REQUEST_CODE);

               }
           }
       });




    }


}
