
package com.example.android.android_exam.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List<Calendar> mList;
    private CalendarAdapter mCalendarAdaptor;
    private CalendarView mCalendarView;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 버튼 이벤트 연결
        findViewById(R.id.prev_btn).setOnClickListener(this);
        findViewById(R.id.next_btn).setOnClickListener(this);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);

        // 어뎁터 준비
        mCalendarAdaptor = new CalendarAdapter(this);

        // view 에 어뎁터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdaptor);

        // 이벤트 리스너 연결
        mCalendarView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                mCalendarAdaptor.prevMonth();
                updateTitle();
                break;
            case R.id.next_btn:
                mCalendarAdaptor.nextMonth();
                updateTitle();
                break;

            default:
                break;
        }

    }

    private void updateTitle() {
        int year = mCalendarAdaptor.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdaptor.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년 " + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCalendarAdaptor.setSelectedPosition(position);

        //다시 그려 주세요.
        mCalendarAdaptor.notifyDataSetChanged();
    }
}
