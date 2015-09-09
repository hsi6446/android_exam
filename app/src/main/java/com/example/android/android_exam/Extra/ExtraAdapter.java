package com.example.android.android_exam.Extra;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.util.List;
import java.util.Random;

/**
 * Created by student on 2015-09-09.
 */
public class ExtraAdapter extends BaseAdapter {

    private List<Leva> mData;
    private Context mContext;

    public ExtraAdapter(Context context, List<Leva> data) {
        mData = data;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;



        // 1. 레이아웃 구성

        // convertView 처음 로딩될 때 초기화 과정을 거쳐서 viewHolder 에 저장
        if (convertView == null) {

            // 레이아웃을 가져오기
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_extra, parent,
                    false);

            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            convertView.setBackgroundColor(color);

            // 각 View를 소스로 연결
            ImageView imageView = (ImageView) convertView.findViewById(R.id.leva_image);
            TextView name = (TextView) convertView.findViewById((R.id.leva_text1));
            TextView phoneNumber = (TextView) convertView.findViewById(R.id.leva_text2);

            // viewHolder 를 생성 후, 가져온 view 를 연결
            viewHolder = new ViewHolder();
            viewHolder.image = imageView;
            viewHolder.name = name;
            viewHolder.phone = phoneNumber;

            // viewHolder 를 convertView 의 태그로 저장.
            convertView.setTag(viewHolder);

        } else {
            // convertView 가 다시 로딩 될 때에는 viewHolder 에서 꺼내와서 재사용
            // 이점 : 속도가 빠르다.

            viewHolder = (ViewHolder) convertView.getTag();
        }



        // 2. 레이아웃에 데이터를 바인딩

        Leva leva = (Leva) getItem(position);

        // 둘 중 하나만 쓰면 됨.
        // People people1 = mData.get(position);

        viewHolder.image.setImageResource(leva.getImageResourceId());
        viewHolder.name.setText(leva.getName());
        viewHolder.phone.setText(leva.getPhoneNumber());

        // 3. 완성된 view 를 리턴

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView phone;

    }
}
