package com.example.android.android_exam.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.database.helper.UserDbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mNickname;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordVerify;
    private UserDbHelper mUserDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNickname = (EditText) findViewById(R.id.edit_nickname);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mPasswordVerify = (EditText) findViewById(R.id.edit_password_verify);

        findViewById(R.id.btn_sign_up).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);

    }

    @Override
    public void onClick(View v) {
        // 가입 처리
        if (mPassword.getText().toString().equals(mPasswordVerify.getText().toString()) == false) {
            Toast.makeText(SignUpActivity.this, "패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
            return;

        }

        long inserted = mUserDbHelper.insert(mNickname.getText().toString(), mEmail.getText().toString(), mPassword.getText().toString(), mPasswordVerify.getText().toString());

        if (inserted != -1) {
            Toast.makeText(SignUpActivity.this, "가입되었습니다. 로그인해주세요", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SignUpActivity.this, "가일실패", Toast.LENGTH_SHORT).show();
        }

    }



}

