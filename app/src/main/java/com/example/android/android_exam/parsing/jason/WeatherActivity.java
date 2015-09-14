package com.example.android.android_exam.parsing.jason;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.android_exam.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherActivity extends Activity {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?q=suwon&units=metric";
    private EditText mCityEditText;
    private ListView mWeatherListView;
    private WeatherAdapter mAdapter;
    private ProgressBar mProgressbar;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mWeatherListView.setAdapter(mAdapter);
            mProgressbar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityEditText = (EditText)findViewById(R.id.et_weather);
        mWeatherListView = (ListView)findViewById(R.id.lv_weather);

        mProgressbar = (ProgressBar)findViewById(R.id.weather_progressbar);

        showWeatherInfo();

    }

    // 안드로이드 4.0 부터 네트워킹 제약이 생김.
    // 네트워킹 처리는 반드시 Thread 에서 해야 함!!
    public void showWeatherInfo() {
        mProgressbar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // http 에서 내용을 string 으로 받아온다.
                    String jsonString = getReturnString(getUrlConnection());

                    // 받아온 jason string 을 오브젝트로 변환
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    // 날씨 정보 저장할 리스트
                    List<Weather> weatherList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        long time = object.getLong("dt");
                        String temp = object.getJSONObject("main").getString("temp");
                        String description = object.getJSONArray("weather").getJSONObject(0).getString("description");

                        weatherList.add(new Weather(time, temp, description));
                        Log.d(TAG, new Weather(time, temp, description).toString());
                    }

                    mAdapter = new WeatherAdapter(WeatherActivity.this, weatherList);

                    // UI 갱신을 핸들러에 요청.
                    mHandler.sendEmptyMessage(0);

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

            }
        }).start();
    }

    /**
     * useUrl
     * @Note : URL커넥션 사용
     * @throws IOException
     * @throws Exception
     *
     *
     */
    public void useUrl() throws IOException, Exception {

        String testVal = getReturnString( getUrlConnection() );

    }


    /**
     * getUrlConnection
     * @Note : url 커넥션
     * @return
     * @throws Exception
     *
     *
     */
    public static URLConnection getUrlConnection(  )
            throws Exception {

        // URL 조합
        String urlString = URL_FORECAST;

        URL url = new URL( urlString ); // 넘어오는 URL밎정보
        URLConnection connection = url.openConnection(); // 커넥션
        connection.setDoOutput( true );
        return connection;
    }


    /**
     * getReturnString
     * @Note : 커넥션된 결과값
     * @param connection
     * @return
     * @throws IOException
     *
     *
     */
    public static String getReturnString( URLConnection connection )
            throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(
                connection.getInputStream(), "UTF-8" ) ); // 반환되는 값이 UTF-8 경우
        StringBuffer buffer = new StringBuffer();
        String decodedString;

        while( ( decodedString = in.readLine() ) != null ) {
            buffer.append( decodedString );

        }

        in.close();

        return buffer.toString();
    }
}
