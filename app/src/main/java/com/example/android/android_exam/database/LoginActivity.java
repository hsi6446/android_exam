package com.example.android.android_exam.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.database.contract.UserContract;
import com.example.android.android_exam.database.helper.UserDbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;
    private TextView mSignUpTextView;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mSignUpTextView = (TextView) findViewById(R.id.tv_sign_up);
        mSignUpTextView.setOnClickListener(this);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);

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
                // 로그인 처리

                UserDbHelper helper = new UserDbHelper(this);
                Cursor cursor = helper.query();

                if (cursor != null) {
                    while (cursor.moveToFirst()) {
                        String email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL));

                        String password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD));

                        if ((email.equals(mEmail.getText().toString())) && password.equals(mPassword.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                break;

        }

    }
}
