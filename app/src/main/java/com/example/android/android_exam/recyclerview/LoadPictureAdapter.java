package com.example.android.android_exam.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_exam.R;
import com.suwonsmartapp.abl.AsyncBitmapLoader;

import java.util.List;

/**
 * Created by student on 2015-10-13.
 */
public class LoadPictureAdapter extends RecyclerView.Adapter<LoadPictureAdapter.ViewHolder>
        implements AsyncBitmapLoader.BitmapLoadListener {
    private Context mContext;
    private List mDataList;

    // 다이나믹 비트맵 로더
    private AsyncBitmapLoader mAsyncBitmapLoader;

    public LoadPictureAdapter(Context context, List data) {
        mDataList = data;
        mContext = context;

        // setBitmapLoadListener 를 구현
        mAsyncBitmapLoader = new AsyncBitmapLoader(context);
        mAsyncBitmapLoader.setBitmapLoadListener(this);

    }

    // 어뎁터에서  getView 부분을 두 개로 나누어 놓은 것.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 레이아웃 가져오기 - card view 재활용
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);

        // 홀더에 저장
        ImageView iv = (ImageView) view.findViewById(R.id.imageview);
        ViewHolder holder = new ViewHolder(iv);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 이미지 파일
        mAsyncBitmapLoader.loadBitmap(position, holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public Bitmap getBitmap(int position) {
        // id 갖고 오기
        long id = (long) mDataList.get(position);

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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
