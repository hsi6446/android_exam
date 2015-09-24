package com.example.android.android_exam.database.parse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.android_exam.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by student on 2015-09-24.
 */
public class ParseLocalDbActivity extends Activity implements View.OnClickListener {

    ParseObject gameScore = new ParseObject("langlang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parse_localdb);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_load).setOnClickListener(this);
        findViewById(R.id.btn_sync).setOnClickListener(this);

    }

    private void sync() {
        gameScore.saveEventually();
    }

    private void save() {
        gameScore.put("username", "혜운");
        gameScore.put("score", "100");

        gameScore.pinInBackground();

        Toast.makeText(ParseLocalDbActivity.this, "저장 성공했음 ㅇㅇ", Toast.LENGTH_SHORT).show();
    }

    private void load() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("langlang");
        query.whereEqualTo("username", "혜운");

        // local 로 부터 쿼리
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    ParseObject parseObject = objects.get(0);
                    int score = parseObject.getInt("score");
                    Toast.makeText(ParseLocalDbActivity.this, "성공", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ParseLocalDbActivity.this, "실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_load:
                load();
                break;
            case R.id.btn_sync:
                sync();
                break;
        }
    }
}
