package com.example.android.android_exam.Mission02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;

public class Mission02MainActivity extends AppCompatActivity {

    private final String TAG = Mission02MainActivity.class.getSimpleName();

    private EditText mNameEditText;
    private TextView mBytes;
    private Button mSendButton;
    private Button mFinishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02_main);


        mNameEditText = (EditText) findViewById(R.id.edit_text);
        mBytes = (TextView) findViewById(R.id.byte_text_view);
        mSendButton = (Button) findViewById(R.id.send_button);
        mFinishButton = (Button) findViewById(R.id.finish_button);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), mNameEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        String context = " " + mNameEditText.getText();
//        int cc = context.getBytes().length;
//        mByte.setText(cc);


        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.w(TAG, "beforeTextChange" + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.w(TAG, "onTextChanged" + s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.w(TAG, "afterTextChanged" + s.toString());

                int length = s.toString().getBytes().length;
                mBytes.setText("" +
                        length);


            }

        });


    }



}