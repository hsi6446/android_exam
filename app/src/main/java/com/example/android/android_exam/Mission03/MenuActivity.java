package com.example.android.android_exam.Mission03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_exam.R;

public class MenuActivity extends AppCompatActivity {

    public static final int INTENT2 = 1002;
    public static final int INTENT3 = 1003;
    public static final int INTENT4 = 1004;

    private Button mCustomerMenu;
    private Button mSalesMenu;
    private Button mProductMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mCustomerMenu = (Button)findViewById(R.id.customer_menu);
        mSalesMenu = (Button)findViewById(R.id.sales_menu);
        mProductMenu = (Button)findViewById(R.id.product_menu);


        mCustomerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), customerActivity.class);
                startActivityForResult(intent2, INTENT2);
            }
        });

        mSalesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), SalesMainActivity.class);
                startActivityForResult(intent3, INTENT3);
            }
        });

        mProductMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplication(), ProductActivity.class);
                startActivityForResult(intent4, INTENT4);
            }
        });
    }





}
