
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
import com.example.android.android_exam.Mission01.Mission01Activity;
import com.example.android.android_exam.Mission02.Mission02MainActivity;
import com.example.android.android_exam.Mission03.Mission03MainActivity;
import com.example.android.android_exam.Mission05.DatePickerDialogActivity;
import com.example.android.android_exam.layout.FrameLayoutActivity;

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
        addItem(myData, "Mission01Activity", Mission01Activity.class);
        addItem(myData, "Mission02MainActivity", Mission02MainActivity.class);
        addItem(myData, "Mission03", Mission03MainActivity.class);
        addItem(myData, "화면이동예제", ActivityExamActivity.class);
        addItem(myData, "WebView 연습", WebActivity.class);
        addItem(myData, "Datepicker Dialog 연습", DatePickerDialogActivity.class);
        addItem(myData, "Animation 연습", AnimationActivity.class);
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
