
package com.example.android.android_exam.thread;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.android_exam.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThreadActivity.class.getSimpleName();
    private Button mbtnThread1;
    private Button mbtnThread2;

    private TextView mNumber1;
    private TextView mNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mbtnThread1 = (Button)findViewById(R.id.btn_thread1);
        mbtnThread2 = (Button)findViewById(R.id.btn_thread2);

        mNumber1 = (TextView)findViewById(R.id.tv_number1);
        mNumber2 = (TextView)findViewById(R.id.tv_number2);

        mbtnThread1.setOnClickListener(this);
        mbtnThread2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1 :
                progressDialogExam();

                break;
            case R.id.btn_thread2 :
                break;
        }

    }

    private void progressDialogExam() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드 중");

        //다운로드 중에 취소되는 것 막기
        progressDialog.setCancelable(false);

        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 2초 동안 다운로드
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //다운로드 끝나면 progressDialog 를 닫는다.
                progressDialog.dismiss();

            }
        }).start();
    }


    // 백그라운드 처리는 바로바로 보이지만, UI 변경은 스레드 종요 시 마지막 결과만 보여진다.
    private void runOnUIThreadExam() {
        // UI Thread 로 동작하게 해줌. Activity 가 제공함(액티비티 안에서만 사용 가능)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); //스레드가 잠시 쉰다. 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }

                    Log.d(TAG, "" + i);      //background
                    mNumber1.setText("" +i); //foreground

                }
            }
        });
    }


    // UI 수정하는 곳에 사용
    // UI로 많이 사용되면 성능이 떨어짐=느려짐
    private void threadAndHandler() {
        //Handler class 상속을 생략한 것
        // 보이는 부분에서 동작하는 Thread
        // UI Thread
        // Main Thread
        // Foreground Thread

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumber1.setText("" + msg.arg1);
            }
        };

        // 안 보이는 곳에서 동작하는 Thread
        // Background Thread
        // Worker Thread
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "" + i);
                    try {
                        Thread.sleep(1000); //스레드가 잠시 쉰다. 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }

                    Message msg = new Message();
                    msg.arg1 = i;
                    handler.sendMessage(msg);

                }
            }
        });
        thread.start();
    }


    //스레드 사용방법1
    //background에서 동작.
    private void backgroundThread() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "" + i);
                    try {
                        Thread.sleep(1000); //스레드가 잠시 쉰다. 1000 = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                }
            }
        });
        thread.start();
    }
}
