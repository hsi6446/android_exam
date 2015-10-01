
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
import com.suwonsmartapp.abl.AsyncBitmapLoader;

/**
 * Created by student on 2015-09-25.
 */
public class LoadPictureAdapter extends CursorAdapter implements AsyncBitmapLoader.BitmapLoadListener {

    private final LayoutInflater mLayoutInflater;

    // 다이나믹 비트맵 로더
    private AsyncBitmapLoader mAsyncBitmapLoader;

    public LoadPictureAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        mLayoutInflater = LayoutInflater.from(context);
        mAsyncBitmapLoader = new AsyncBitmapLoader(context);

        // setBitmapLoadListener 를 구현
        mAsyncBitmapLoader = new AsyncBitmapLoader(context);
        mAsyncBitmapLoader.setBitmapLoadListener(this);

    }

    // 맨 처음에 레이아웃을 만드는 부분
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 레이아웃 가져오기
        View view = mLayoutInflater.inflate(R.layout.item_picture, parent, false);

        // 홀더에 저장
        ViewHolder holder = new ViewHolder();
        holder.imageView = (ImageView) view.findViewById(R.id.imageview);
        view.setTag(holder);

        return view;
    }


    // 실제로 데이타를 셋팅하는 부분, 헷갈리면 아래에다 getview 메소드 또 생성해도 됨.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        // 이미지 파일
        mAsyncBitmapLoader.loadBitmap(cursor.getPosition(), holder.imageView);
    }

    @Override
    public Bitmap getBitmap(int position) {
        // id 갖고 오기
        long id = getItemId(position);

        // 비트맵 샘플링
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 2의 배수만 됨. 커질수록 작아짐(용량+크기)
        options.inSampleSize = 2;

        // id 로부터 비트맵 생성
        return MediaStore.Images.Thumbnails.getThumbnail(mContext.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
