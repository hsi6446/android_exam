package com.example.android.android_exam.Animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-09-08.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private Animation mScaleAndRotate;
    private ImageView mImageView;
    private Button mStartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        mImageView = (ImageView)findViewById(R.id.leva3_image_view);

        mStartBtn = (Button)findViewById(R.id.start_btn);
        mStartBtn.setOnClickListener(this);

        // 애니메이션 시작, 끝, 반복할 때 처리를 구현
        mScaleAndRotate = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.anim_scale_rotate);
        mScaleAndRotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        mImageView.startAnimation(mScaleAndRotate);
    }
}
