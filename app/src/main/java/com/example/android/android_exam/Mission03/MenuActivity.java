
package com.example.android.android_exam.Mission03;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.android_exam.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int INTENT2 = 1002;
    public static final int INTENT3 = 1003;
    public static final int INTENT4 = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.customer_menu).setOnClickListener(this);
        findViewById(R.id.sales_menu).setOnClickListener(this);
        findViewById(R.id.product_menu).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customer_menu:
                CustomerOpenDialog();
                break;

            case R.id.sales_menu:
                SalesOpenDialog();
                break;
            case R.id.product_menu:
                ProductOenDialog();
                break;
        }
    }


    private void CustomerOpenDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("고객관리");

        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

    private void SalesOpenDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("매출 관리");

        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

    private void ProductOenDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("상품 관리");

        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

}
