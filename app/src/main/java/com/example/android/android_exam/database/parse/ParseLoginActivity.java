package com.example.android.android_exam.database.parse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.database.SignUpActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by student on 2015-09-18.
 */
public class ParseLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mSignUpTextView;
    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mSignUpTextView = (TextView) findViewById(R.id.tv_sign_up);
        mSignUpTextView.setOnClickListener(this);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mCheckBox = (CheckBox)findViewById(R.id.check_email);

        findViewById(R.id.btn_login).setOnClickListener(this);

        //http://developer.android.com/intl/ko/training/basics/data-storage/shared-preferences.html

        mEmail.setText(loadEmail());
    }

    private String loadEmail() {

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        String email = sharedPref.getString("pref_email", "");

        return email;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                ParseUser.logInInBackground(mEmail.getText().toString(),
                        mPassword.getText().toString(),
                        new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // 성공
                            Toast.makeText(ParseLoginActivity.this, "성공 ㅇㅋ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ParseLoginActivity.this, "실패dddd", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

        }

    }

    private void saveEmail(String email) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("pref_email", email);
//        editor.commit();      // 동기식 sync
        editor.apply();        // 비동기식 sync
    }
}
