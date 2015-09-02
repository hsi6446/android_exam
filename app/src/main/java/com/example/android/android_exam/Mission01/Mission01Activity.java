
package com.example.android.android_exam.Mission01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.android_exam.R;

public class Mission01Activity extends AppCompatActivity {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private Button mUpButton;
    private Button mDownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        mImageView1 = (ImageView) findViewById(R.id.leva1);
        mImageView2 = (ImageView) findViewById(R.id.leva2);

        mUpButton = (Button) findViewById(R.id.up_button);
        mDownButton = (Button) findViewById(R.id.down_button);

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();

            }
        });

        mDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage2();
            }
        });

    }

    public void changeImage() {

        mImageView1.setImageResource(R.drawable.leva2);
        mImageView2.setImageResource(R.drawable.leva1);
    }

    private void changeImage2() {

       if (mImageView2 == mImageView2) {
           mImageView1.setImageResource(R.drawable.leva1);
           mImageView2.setImageResource(R.drawable.leva2);

       } else {
           mImageView1.setImageResource(R.drawable.leva2);
           mImageView2.setImageResource(R.drawable.leva1);
       }

    }

}
