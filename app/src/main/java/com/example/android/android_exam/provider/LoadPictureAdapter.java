package com.example.android.android_exam.provider;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-09-25.
 */
public class LoadPictureAdapter extends CursorAdapter {

    private final LayoutInflater mLayoutInflater;

    public LoadPictureAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        mLayoutInflater = LayoutInflater.from(context);
    }

    // 맨 처음에 레이아웃을 만드는 부분
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 레이아웃 가져오기
        View view = mLayoutInflater.inflate(R.layout.item_picture, parent, false);

        // 홀더에 저장
        ViewHolder holder = new ViewHolder();
        holder.imageView = (ImageView)view.findViewById(R.id.imageview);
        view.setTag(holder);

        return view;
    }

    // 실제로 데이타를 셋팅하는 부분, 헷갈리면 아래에다 getview 메소드 또 생성해도 됨.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();

        // id 갖고 오기
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));

        // 비트맵 샘플링
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 2의 배수만 됨. 커질수록 작아짐(용량+크기)
        options.inSampleSize = 2;

        // id 로부터 비트맵 생성
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);

        // 이미지 셋팅
        holder.imageView.setImageBitmap(bitmap);
    }

    static class ViewHolder {
        ImageView imageView;

    }
}
