package com.example.android.android_exam.Extra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.android_exam.R;

import java.util.ArrayList;

public class ExtraActivity extends AppCompatActivity {

    private ExtraAdapter mExtraAdapter;
    private ListView mListView;
    private ArrayList<Leva> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levamain);

        initData();

        initAdapter();

        initListView();


    }

    private void initListView() {
        ListView mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mExtraAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // item이 클릭 되었을 때 동작하는 부분
                Toast.makeText(ExtraActivity.this, "잉잉", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initAdapter() {
        mExtraAdapter = new ExtraAdapter(getApplicationContext(), mData);
    }

    private void initData() {

        mData = new ArrayList<>();


        for (int i = 0; i <= 100; i++) {

            mData.add(new Leva(R.drawable.leva2, "Someone Like Leva", "By Leva"));


        }

    }


}
