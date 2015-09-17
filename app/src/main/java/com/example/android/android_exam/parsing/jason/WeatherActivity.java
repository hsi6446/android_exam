
package com.example.android.android_exam.parsing.jason;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.android_exam.R;
import com.example.android.android_exam.utils.network.network.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-09-14.
 */
public class WeatherActivity extends Activity implements View.OnKeyListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?units=metric&q=";
    private EditText mCityEditText;
    private ListView mWeatherListView;
    private WeatherAdapter mAdapter;
    private ProgressBar mProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityEditText = (EditText) findViewById(R.id.et_weather);
        mWeatherListView = (ListView) findViewById(R.id.lv_weather);

        mProgressbar = (ProgressBar) findViewById(R.id.weather_progressbar);

        mCityEditText.setOnKeyListener(this);

        new WeatherInfoLoadTask().execute("suwon");

    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            new WeatherInfoLoadTask().execute(mCityEditText.getText().toString());
            return true;
        }
        return false;
    }

    // onPreExecute 에서 받는 인자 타입은 doInBackground 에서 받는 인자와 매칭시켜야함.

    // [인자타입] 처음에 인자를 받는지 - doInBackground 에서 받음 / onProgressUpdate 의 타입. 오버라이드 할 거면 타입 정해줌 / onPostExecute 의 타입. doInBackground 의 클래스 타입이 됨.

    class WeatherInfoLoadTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() { // doInBackground 시작하기 전에 해 놓을 것들.
            super.onPreExecute();

            // 프로그래스바 설정.
            mProgressbar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(String... params) { // 첫 번째 인자
            String query = params[0];

            publishProgress();

            try {
                // http 에서 내용을 string 으로 받아온다.
                String jsonString = NetworkUtil.getReturnString(URL_FORECAST + query);

                // 받아온 jason string 을 오브젝트로 변환
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("list");

                // 날씨 정보 저장할 리스트
                List<Weather> weatherList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String time = object.getString("dt_txt");
                    time = time.split(" ")[1].substring(0, 5);

                    String temp = object.getJSONObject("main").getString("temp");
                    String description = object.getJSONArray("weather").getJSONObject(0)
                            .getString("description");

                    weatherList.add(new Weather(time, temp, description));
                    Log.d(TAG, new Weather(time, temp, description).toString());
                }

                mAdapter = new WeatherAdapter(WeatherActivity.this, weatherList);

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            return null;
        }

        // doInBackground 에서 publishUpdate 로만 호출.
       @Override
          protected void onProgressUpdate(Void... values) {    // 두번째 인자
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {      // 세번째 인자
            // 어텝터 만들기 and 셋팅 ---선생님 Git Source 와 비교하기. List 이용하는 부분.
            super.onPostExecute(aVoid);

            mWeatherListView.setAdapter(mAdapter);
            mProgressbar.setVisibility(View.GONE);

        }
    }
}
