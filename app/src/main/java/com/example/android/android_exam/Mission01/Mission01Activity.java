
package com.example.android.android_exam.Mission01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.android_exam.R;

public class Mission01Activity extends AppCompatActivity {

    private Button mChangeButton;
    private ImageView mImageView;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        mChangeButton = (Button) findViewById(R.id.change_button);
        mImageView = (ImageView) findViewById(R.id.leva1);
        mImageView2 = (ImageView) findViewById(R.id.leva2);

        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();

            }
        });


    }

    public void changeImage() {
        if(mImageView.getVisibility() == View.VISIBLE) {

            mImageView.setVisibility(View.INVISIBLE);
            mImageView2.setVisibility(View.VISIBLE);
        }else {

            mImageView.setVisibility(View.VISIBLE);
            mImageView2.setVisibility(View.INVISIBLE);
        }



    }

}
