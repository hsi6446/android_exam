
package com.example.android.android_exam.Calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-10-05.
 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mConsole;
    private int mResult = 0;
    private int mSave1 = 0;         // 이전 연산자
    private int mSave2 = 0;         // 다음 연산자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculater);

        mConsole = (TextView) findViewById(R.id.tv_console);

        findViewById(R.id.btn_times).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);

        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);

        findViewById(R.id.btn_equals).setOnClickListener(this);
        findViewById(R.id.btn_point).setOnClickListener(this);
        findViewById(R.id.btn_all_cancle).setOnClickListener(this);
        findViewById(R.id.btn_erase).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_equals:
                break;
            case R.id.btn_point:
                break;
            case R.id.btn_erase:
                break;
            case R.id.btn_all_cancle:
                mConsole.setText(0);
                mResult = 0;
                mSave1 = 0;
                break;

            case R.id.btn_times:
                onTimes();
                break;
            case R.id.btn_divide:
                onDivide();
                break;
            case R.id.btn_plus:
                onPlus();
                break;
            case R.id.btn_minus:
                onMinus();
                break;

            case R.id.btn_one:
                mSave1 += 1;
                break;
            case R.id.btn_two:
                mSave1 += 2;
                break;
            case R.id.btn_three:
                mSave1 += 3;
                break;
            case R.id.btn_four:
                mSave1 += 4;
                break;
            case R.id.btn_five:
                mSave1 += 5;
                break;
            case R.id.btn_six:
                mSave1 += 6;
                break;
            case R.id.btn_seven:
                mSave1 += 7;
                break;
            case R.id.btn_eight:
                mSave1 += 8;
                break;
            case R.id.btn_nine:
                mSave1 += 9;
                break;
            case R.id.btn_zero:
                mSave1 += 0;
                break;

        }

    }

    private int onTimes() {

        return 0;
    }

    private int onDivide() {

        return 0;
    }

    private int onPlus() {

        return 0;
    }

    private int onMinus() {

        return 0;
    }


}
