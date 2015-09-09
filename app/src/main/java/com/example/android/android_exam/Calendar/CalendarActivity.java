
package com.example.android.android_exam.Calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private List<Calendar> mList;
    private CalendarAdapter mCalendarAdapter;
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
        mCalendarAdapter = new CalendarAdapter(this);

        // view 에 어뎁터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                mCalendarAdapter.prevMonth();
                updateTitle();
                break;
            case R.id.next_btn:
                mCalendarAdapter.nextMonth();
                updateTitle();
                break;

            default:
                break;
        }

    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년 " + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 뭔가 합니다.
            }
        });

        View view2 = LayoutInflater.from(this).inflate(R.layout.dialog_schedule, parent,
                false);

        // 액티비티에서만 사용 가능함.
        // View view2 = getLayoutInflater().inflate(R.layout.dialog_schedule, null);

        builder.setView(view2);
        builder.show();
    }
}
