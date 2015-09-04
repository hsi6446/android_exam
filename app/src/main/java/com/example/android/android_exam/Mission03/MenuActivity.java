
package com.example.android.android_exam.Mission03;

import android.content.Intent;
import android.os.Bundle;
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
                customerClick();
                break;

            case R.id.sales_menu:
                salesClick();
                break;
            case R.id.product_menu:
                productClick();
                break;
        }
    }

    private void productClick() {
        Intent intent4 = new Intent(getApplication(), ProductActivity.class);
         startActivityForResult(intent4, INTENT4);
    }

    private void salesClick() {
        Intent intent2 = new Intent(getApplicationContext(), customerActivity.class);
        startActivityForResult(intent2, INTENT2);
    }

    private void customerClick() {
        Intent intent2 = new Intent(getApplicationContext(), customerActivity.class);
        startActivityForResult(intent2, INTENT2);
    }
}
