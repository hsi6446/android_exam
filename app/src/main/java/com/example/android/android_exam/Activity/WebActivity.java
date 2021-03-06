
package com.example.android.android_exam.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.android.android_exam.R;

public class WebActivity extends AppCompatActivity implements View.OnKeyListener {

    private WebView mWebview;
    private EditText mAdress;
    private Animation mDown;
    private Animation mUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebview = (WebView) findViewById(R.id.webView);
        mAdress = (EditText) findViewById(R.id.WebAdress_view);

        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);

        mAdress.setOnKeyListener(this);

        mUp = AnimationUtils.loadAnimation(this, R.anim.up_anim);
        mDown = AnimationUtils.loadAnimation(this, R.anim.down_anim);


    }

    private void lordUrl(String url) {
        mWebview.loadUrl(url);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            lordUrl("http://" + mAdress.getText().toString());
            return true;

        }

        return false;
    }

}
