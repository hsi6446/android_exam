
package com.example.android.android_exam.Calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-10-05.
 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mConsole;
    private TextView mMiddle;

    private int mResult = 0;        // 결과창에 표시될 데이터
    private int mSave1 = 0;         // 이전 연산자
    private int mSave2 = 0;         // 다음 연산자

    private int timesResult = 0;
    private int divideResult = 0;
    private int plusResult = 0;
    private int minusResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculater);

        mConsole = (TextView) findViewById(R.id.tv_console);
        mMiddle = (TextView) findViewById(R.id.tv_middle);

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
                mConsole.setText(Integer.toString(mResult));
                break;
            case R.id.btn_point:
                Toast.makeText(CalculatorActivity.this, "" + mSave1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_erase:
                if (!mMiddle.getText().toString().equals("")) {
                    String e = mMiddle.getText().toString().substring(0, mMiddle.getText().toString().length() - 1);
                    mMiddle.setText(e);
                } else {
                    mMiddle.setText("");
                }
                break;
            case R.id.btn_all_cancle:
                mConsole.setText("");
                mMiddle.setText("");
                mResult = 0;
                mSave1 = 0;
                break;

            case R.id.btn_times:
                mSave2 = Integer.valueOf(mMiddle.getText().toString().trim());
                onTimes(mSave2);
                mMiddle.setText("");
                break;
            case R.id.btn_divide:
                mSave2 = Integer.valueOf(mMiddle.getText().toString().trim());
                onDivide(mSave2);
                mMiddle.setText("");
                break;
            case R.id.btn_plus:
                mSave2 = Integer.valueOf(mMiddle.getText().toString().trim());
                onPlus(mSave2);
                mMiddle.setText("");
                break;
            case R.id.btn_minus:
                mSave2 = Integer.valueOf(mMiddle.getText().toString().trim());
                onMinus(mSave2);
                mMiddle.setText("");
                break;

            case R.id.btn_one:
                mMiddle.setText(mMiddle.getText().toString() + 1);
                break;
            case R.id.btn_two:
                mMiddle.setText(mMiddle.getText().toString() + 2);
                break;
            case R.id.btn_three:
                mMiddle.setText(mMiddle.getText().toString() + 3);
                break;
            case R.id.btn_four:
                mMiddle.setText(mMiddle.getText().toString() + 4);
                break;
            case R.id.btn_five:
                mMiddle.setText(mMiddle.getText().toString() + 5);
                break;
            case R.id.btn_six:
                mMiddle.setText(mMiddle.getText().toString() + 6);
                break;
            case R.id.btn_seven:
                mMiddle.setText(mMiddle.getText().toString() + 7);
                break;
            case R.id.btn_eight:
                mMiddle.setText(mMiddle.getText().toString() + 8);
                break;
            case R.id.btn_nine:
                mMiddle.setText(mMiddle.getText().toString() + 9);
                break;
            case R.id.btn_zero:
                mMiddle.setText(mMiddle.getText().toString() + 0);
                break;

        }

    }

    private int onTimes(int mSave2) {

        mResult = (mSave1 = mSave1 * mSave2);

        return mResult;
    }

    private int onDivide(int mSave2) {

        mResult = (mSave1 = mSave2 / Integer.valueOf(mMiddle.getText().toString().trim()));

        return mResult;
    }

    private int onPlus(int mSave2) {

        mResult = (mSave1 = mSave2 + Integer.valueOf(mMiddle.getText().toString().trim()));

        return mResult;
    }

    private int onMinus(int mSave2) {

        mResult = (mSave1 = mSave1 - mSave2);

        return mResult;
    }


}
