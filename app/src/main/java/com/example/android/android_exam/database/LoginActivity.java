package com.example.android.android_exam.database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.example.android.android_exam.database.contract.UserContract;
import com.example.android.android_exam.database.helper.UserDbHelper;
import com.example.android.android_exam.database.provider.UserProvider;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;
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

        mUserDbHelper = new UserDbHelper(this);

        // TODO Shared preference 에 저장된 값이 있으면 가져와서 email 에 셋팅
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
                // 로그인 처리

//                UserDbHelper helper = new UserDbHelper(this);
//                Cursor cursor = helper.query();

                Cursor cursor = getContentResolver().query(UserProvider.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL));

                        String password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD));

                        if ((email.equals(mEmail.getText().toString())) && password.equals(mPassword.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();

                            //TODO Shared preference 에 email 값을 저장하는 부분
                            // http://developer.android.com/intl/ko/training/basics/data-storage/shared-preferences.html
                           if (mCheckBox.isChecked()) {
                               saveEmail(mEmail.getText().toString());
                           }else {
                               saveEmail("");
                           }

                            return;
                        } else {
                            Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

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
