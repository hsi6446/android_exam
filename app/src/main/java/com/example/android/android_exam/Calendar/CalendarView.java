package com.example.android.android_exam.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by student on 2015-09-08.
 */
public class CalendarView extends GridView {

    //코드상에서 생성될 때 호출하는 생성자
    public CalendarView(Context context) {
        this(context, null);
    }

    // xml에 정의 되었을 때 호출되는 생성자
    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 위젯에서 호출 했을 때
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setNumColumns(7);   // 7열로 설정
        setBackgroundResource(android.R.color.darker_gray);     //배경을 회색으로\
        setHorizontalSpacing(1);
        setVerticalSpacing(1);

    }
}
