package com.example.android.android_exam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.android_exam.R;

public class ActivityExamActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_STRING = 1000;
    private EditText mNameEditText;
    private EditText mPhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acivity_exam);

        mNameEditText = (EditText)findViewById(R.id.name_edit_text);
        mPhoneEditText = (EditText)findViewById(R.id.phoneNumber_edit_text);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1 :
                btn1Click();

                break;
            
            case R.id.btn2 :

                btn2Click();

                break;
        }

    }

    private void btn1Click() {
        //Target Activity로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // 데이터 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhoneEditText.getText().toString());

        startActivity(intent);
    }

    private void btn2Click() {
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);


        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhoneEditText.getText().toString());

        //=============================주는 곳
        //데이터를 돌려 받기 위해서는 startActivityResult 를 호출해야됨
        //리퀘스트 코드는 원하는 값을 받기위한 약속같은 것.
        startActivityForResult(intent, REQUEST_CODE_STRING);

    }


    //=======================받는 곳
    //startActivityForResult로 호출 후에 데이터를 리턴 받는 곳.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //결과가 OK인지 확인
        if(resultCode == RESULT_OK) {
            //리퀘스트 코드에 따라 처리를 나눈다.
            if (requestCode == REQUEST_CODE_STRING) {
                if(data != null) {
                    String result = data.getStringExtra("result");
                    Toast.makeText(ActivityExamActivity.this, "result : " + result, Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            Toast.makeText(ActivityExamActivity.this, "에러", Toast.LENGTH_SHORT).show();
        }
    }
}
