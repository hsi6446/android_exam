
package com.example.android.android_exam.Mission03;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_exam.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private String title;

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
        title = ((Button) v).getText().toString();

        switch (v.getId()) {
            case R.id.customer_menu:
                OpenDialog();
                break;

            case R.id.sales_menu:
                OpenDialog();
                break;
            case R.id.product_menu:
                OpenDialog();
                break;
        }

    }

    private void OpenDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle(title);

        builder.setNegativeButton("닫기", null);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

}
