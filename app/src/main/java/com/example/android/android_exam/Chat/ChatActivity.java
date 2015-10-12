package com.example.android.android_exam.Chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.android_exam.R;

public class ChatActivity extends AppCompatActivity implements ChatClient.ClientCallback {

    private EditText mEditText;
    private TextView mTextView;

    private ChatClient mChatClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mEditText = (EditText)findViewById(R.id.input_massage);
        mTextView = (TextView)findViewById(R.id.tv_all_massage);

        mChatClient = new ChatClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 네트워크 접속은 background thread 에서 해야 함.
                mChatClient.setClientCallback(ChatActivity.this);
                mChatClient.connect();
            }
        }).start();

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_UP){
                    // 메세지를 서버에 전송
                    mChatClient.sendMessage(mEditText.getText().toString());

                    // 하단 텍스트뷰에 메세지를 표시

                    mTextView.append(mEditText.getText().toString());

                    //EditText 를 공백으로 설정

                    mEditText.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mChatClient.disconnect();
    }

    @Override
    public void onReceiveMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.append(message);
            }
            });
        }


    @Override
    public String getNickName() {
        return "허수인";
    }
}
