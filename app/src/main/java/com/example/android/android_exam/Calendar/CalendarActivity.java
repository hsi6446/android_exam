package com.example.android.android_exam.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_exam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private List<Calendar> mList;
    private CalendarAdapter mCalendarAdaptor;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mList = new ArrayList<>();

        // 오늘 날짜
        Calendar calendar = GregorianCalendar.getInstance();

        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH) + 1;

        // 마지막 날
        int lastDay = calendar.getActualMaximum(Calendar.DATE);

        // 이 달의 첫 번째 날
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstday = calendar.get(Calendar.DAY_OF_WEEK);

        for(int i = 1; i < firstday; i++){
            mList.add(null);
        }

        // 이번 달 달력 데이터
        for(int i = 1; i<= lastDay; i++) {
            mList.add(new GregorianCalendar(year, month, i));
        }

        // 어뎁터 준비
        mCalendarAdaptor = new CalendarAdapter(CalendarActivity.this, mList);

        // view 에 어뎁터를 설정
        mCalendarView = (CalendarView)findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdaptor);
    }

}
