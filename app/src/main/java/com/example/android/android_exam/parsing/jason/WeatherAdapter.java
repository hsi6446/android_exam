package com.example.android.android_exam.parsing.jason;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015-09-11.
 */
public class WeatherAdapter extends BaseAdapter {

    private static final String TAG = WeatherAdapter.class.getSimpleName();
    private List<Weather> mList;
    private Context mContext;

    private SimpleDateFormat mFormat = new SimpleDateFormat("HH:MM");

    public WeatherAdapter(Context context, List<Weather> data) {

        mList = data;
        mContext = context;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // 레이아웃을 완성하고 뷰홀더와 연결
        // findViewById를 한번만 사용하기 위해.
        if (convertView == null) {
            holder = new ViewHolder();
            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent,
                    false);

            holder.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tempTextView = (TextView) convertView.findViewById(R.id.tv_temp);
            holder.descriptionTextView = (TextView) convertView.findViewById(R.id.tv_description);

            convertView.setTag(holder);
        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();

        }

        Weather weather = mList.get(position);

        Date date = new Date();
        date.setTime(weather.getTime());
        holder.timeTextView.setText(mFormat.format(date));
        holder.tempTextView.setText(weather.getTemp() + "C");
        holder.descriptionTextView.setText(weather.getDescription());


        return convertView;
    }


    private class ViewHolder {

        TextView timeTextView;
        TextView tempTextView;
        TextView descriptionTextView;
    }
}
