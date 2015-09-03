package com.example.android.android_exam.Mission03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_exam.R;

public class customerActivity extends AppCompatActivity {

    private Button mCustomerFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        mCustomerFinish = (Button)findViewById(R.id.customer_finish);
        mCustomerFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
