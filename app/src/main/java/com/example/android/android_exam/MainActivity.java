
package com.example.android.android_exam;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.android.android_exam.Activity.ActivityExamActivity;
import com.example.android.android_exam.Activity.WebActivity;
import com.example.android.android_exam.Animation.AnimationActivity;
import com.example.android.android_exam.Calendar.CalendarActivity;
import com.example.android.android_exam.Extra.ExtraActivity;
import com.example.android.android_exam.Mission02.Mission02MainActivity;
import com.example.android.android_exam.Mission03.Mission03MainActivity;
import com.example.android.android_exam.Mission05.DatePickerDialogActivity;
import com.example.android.android_exam.fragment.FragmentActivity;
import com.example.android.android_exam.layout.FrameLayoutActivity;
import com.example.android.android_exam.parsing.jason.WeatherActivity;
import com.example.android.android_exam.receiver.BroadcastActivity;
import com.example.android.android_exam.thread.ThreadActivity;
import com.example.android.android_exam.viewPager.ScreenSlideActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.android.android_exam.animation.TransitionDrawableExamActivity;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                        "title"
                },
                new int[] {
                        android.R.id.text1
                }));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
        addItem(myData, "FrameLayoutActivity", FrameLayoutActivity.class);
        addItem(myData, "Mission02MainActivity", Mission02MainActivity.class);
        addItem(myData, "Mission03", Mission03MainActivity.class);
        addItem(myData, "화면이동예제", ActivityExamActivity.class);
        addItem(myData, "WebView 연습", WebActivity.class);
        addItem(myData, "Datepicker Dialog 연습", DatePickerDialogActivity.class);
        addItem(myData, "Animation 연습", AnimationActivity.class);
        addItem(myData, "Calendar 연습", CalendarActivity.class);
        addItem(myData, "잉잉이 리스트뷰 연습", ExtraActivity.class);
        addItem(myData, "Thread 연습", ThreadActivity.class);
        addItem(myData, "날씨 정보 불러오기 연습", WeatherActivity.class);
        addItem(myData, "Fragment 연습", FragmentActivity.class);
        addItem(myData, "스크린 슬라이드", ScreenSlideActivity.class);
        addItem(myData, "브로드캐스트 리시버", BroadcastActivity.class);

//        addItem(myData, "Mission03MainActivity". Mission03MainActivity.class);
        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class cls) {
        this.addItem(data, name, new Intent(this, cls));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
