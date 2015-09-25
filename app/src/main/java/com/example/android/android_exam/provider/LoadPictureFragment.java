package com.example.android.android_exam.provider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.android_exam.R;

/**
 * Created by student on 2015-09-25.
 */
public class LoadPictureFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private LoadPictureAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_load_picture, container, false);

        mListView = (ListView)view.findViewById(R.id.frag_listView);

        return view;
    }

    // 뷰가 생성된 직후에 작동하는 메소드
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new LoadPictureAdapter(getActivity(), null, true);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
    }

    // cursor loader 생성하는 부분
    // Background thread 에서 동작하는 부분
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    // onCreateLoader 에서 작업이 끝난 후에 호출 되는 부분
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // loader 에서 가져온 데이터 어뎁터에 저장
        mAdapter.swapCursor(data);
    }

    // 데이터를 리셋처리하는 부분
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
