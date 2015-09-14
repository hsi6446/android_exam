
package com.example.android.android_exam.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.android_exam.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThreadActivity.class.getSimpleName();
    private Button mbtnThread1;
    private Button mbtnThread2;

    private TextView mNumber1;
    private TextView mNumber2;

    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mbtnThread1 = (Button)findViewById(R.id.btn_thread1);
        mbtnThread2 = (Button)findViewById(R.id.btn_thread2);

        mNumber1 = (TextView)findViewById(R.id.tv_number1);
        mNumber2 = (TextView)findViewById(R.id.tv_number2);

        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        mbtnThread1.setOnClickListener(this);
        mbtnThread2.setOnClickListener(this);

    }

    private DownloadTask mDownloadTask;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1 :
                progressDialogExam();

                //굉장히 오래 걸리는 처리(10초)
                // for(...)


                //완료되었습니다.

                break;
            case R.id.btn_thread2 :

                if (mDownloadTask == null
                        || mDownloadTask.getStatus() == AsyncTask.Status.FINISHED) {
                    // 실행 할 때마다 인스턴스 생성
                    mDownloadTask = new DownloadTask();
                    mDownloadTask.execute();

                }

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


    // 백그라운드 처리는 바로바로 보이지만, UI 변경은 스레드 종료 시 마지막 결과만 보여진다.
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

    private class DownloadTask extends AsyncTask<Void, Integer, Void> {
        private AlertDialog.Builder mmBuilder;


        // UI Thread
        // doInBackground 전에 호출 됨.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mmBuilder = new AlertDialog.Builder(ThreadActivity.this);
            mmBuilder.setMessage("다운로드 완료 되었습니다.");
            mmBuilder.setNegativeButton("닫기", null);

            mProgressBar.setProgress(0);
        }

        @Override
        // Background Thread 처리하는 부분.
        protected Void doInBackground(Void... params) {
            // 다운로드 처리
            for (int i = 0; i < 100; i++) {
                // 0.2초 쉬고
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }

                // onProgressUpdate 를 호출
                publishProgress(i + 1);
            }
            return null;
        }

        // UI Thread
        // doInBackground 에서 publishProgress 로 호출하면 호출 됨.
        // 직접 호출하면 프로그램이 죽으므로, 간접적으로 호출 됨.
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
            mNumber2.setText(values[0] + "%");
        }

        // UI Thread
        // doInBackground 가 수행된 후에 호출 됨.
        // doInBackground 에서 리턴 된 값이 파라미터로 넘어옴.
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mmBuilder.show();
        }
    }
}
