
package com.example.android.android_exam.Mission01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.android_exam.R;

public class Mission01Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private Button mUpButton;
    private Button mDownButton;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        mImageView1 = (ImageView) findViewById(R.id.leva1);
        mImageView2 = (ImageView) findViewById(R.id.leva2);

        findViewById(R.id.up_button).setOnClickListener(this);
        findViewById(R.id.down_button).setOnClickListener(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.leva1);
        mImageView1.setImageBitmap(mBitmap);

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.up_button:
                        mImageView1.setImageBitmap(mBitmap);
                        mImageView2.setImageBitmap(null);
                        break;
                    case R.id.down_button:
                        mImageView1.setImageBitmap(null);
                        mImageView2.setImageBitmap(mBitmap);
                }

            }
        });


}

}