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

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_extra, parent,
                    false);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.leva_image);
            TextView name = (TextView) convertView.findViewById((R.id.leva_text1));
            TextView phoneNumber = (TextView) convertView.findViewById(R.id.leva_text2);

            viewHolder = new ViewHolder();
            viewHolder.image = imageView;
            viewHolder.name = name;
            viewHolder.phone = phoneNumber;


            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        Leva leva = (Leva) getItem(position);


        viewHolder.image.setImageResource(leva.getImageResourceId());
        viewHolder.name.setText(leva.getName());
        viewHolder.phone.setText(leva.getPhoneNumber());

        convertView.setBackgroundColor(getRandomColor());


        return convertView;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView phone;

    }
}
