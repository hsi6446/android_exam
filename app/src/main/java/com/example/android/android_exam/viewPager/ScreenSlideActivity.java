package com.example.android.android_exam.viewPager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_exam.R;
import com.example.android.android_exam.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-16.
 */
public class ScreenSlideActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);

        mTabLayout = (TabLayout)findViewById(R.id.tap_layout);

        // 짝퉁 데이터
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mFragmentList.add(new ColorFragment());
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab" + (i +1)));
        }

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), mFragmentList);

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    // FragmentStatePagerAdapter 는 메모리 관리가 필요할 때 사용.
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);

            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
