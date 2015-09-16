
package com.example.android.android_exam.receiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by student on 2015-09-16.
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 코드로 짠 레이아웃 ----xml, java 파일을 따로 줄 필요 없음.
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button.setText("브로드캐스트 발송");
        button.setOnClickListener(this);

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        linearLayout.addView(button);
        setContentView(linearLayout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        sendBroadcast(intent);

        // 순서대로 받기.
//        sendOrderedBroadcast();
    }

    MyReceiver mReceiver =  new MyReceiver();

    @Override
    protected void onResume() {
        super.onResume();

        // 마이리시버가 받을 인텐트 정의
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        // 우선 순위 높게하기
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        // 로컬 브로드캐스트 리시버 등록
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
