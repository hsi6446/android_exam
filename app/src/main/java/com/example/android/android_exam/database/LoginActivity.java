package com.example.android.android_exam.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.database.helper.UserDbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;
    private TextView mSignUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mSignUpTextView = (TextView)findViewById(R.id.tv_sign_up);
        mSignUpTextView.setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                // TODO 로그인 처리
//                long insertedId = mUserDbHelper.insert("test", "test", "test");


//                int count = mUserDbHelper.update("test", "test");

                if (mUserDbHelper.delete("test")) {
                    Toast.makeText(LoginActivity.this, "delete 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "delete 실패", Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }
}
