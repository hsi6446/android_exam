package com.example.android.android_exam.Stopwatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-10-07.
 */
public class StopwatchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartbtn, mPausebtn;
    private TextView mResultConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);

        mResultConsole = (TextView)findViewById(R.id.tv_result_console);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                break;
            case R.id.btn_pause:
                break;
        }
    }
}
