
package com.example.android.android_exam.musicPlayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.android_exam.R;

import java.text.SimpleDateFormat;

/**
 * Created by student on 2015-10-01.
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    private static final int REQUEST_PICK_MUSIC = 1;
    private ImageView mImageView;
    private MediaPlayer mMediaPlayer;
    private TextView mMediaInfo;
    private TextView mCurrentTime;
    private TextView mEndTime;

    private SimpleDateFormat mFormat;
    private SeekBar mSeekBar;
    private SeekBarUpdateTask mSeekBarUpdateTask;
    private ImageButton mPlayButton;

    public static String getTime(long milliSeconds) {
        String result = "";

        int time = (int) milliSeconds / 1000;

        int hour = time / 3600;
        int minute = (time - (hour * 3600)) / 60;
        int second = time - ((hour * 3600) + (minute * 60));

        return (getTimeToString(hour).equals("") ? "" : getTimeToString(hour) + ":")
                + getTimeToString(minute) + ":" + getTimeToString(second);
    }

    public static String getTimeToString(int time) {
        String result = "";

        if (time == 0) {
            result = "";
        } else if (time < 10) {
            result = "0" + String.valueOf(time);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);

        findViewById(R.id.btn_file_pick).setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.iv_thumbnail);
        mMediaInfo = (TextView) findViewById(R.id.tv_file_info);

        mPlayButton = (ImageButton) findViewById(R.id.btn_play_pause);

        mCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        mEndTime = (TextView) findViewById(R.id.tv_end_time);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

        mPlayButton.setOnClickListener(this);
        mFormat = new SimpleDateFormat("mm:ss");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_pick:
                pickFile();
                break;
            case R.id.btn_play_pause:
                if (mMediaPlayer != null) {
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                        v.setSelected(true);

                        mSeekBarUpdateTask = new SeekBarUpdateTask();
                        mSeekBarUpdateTask.execute();

                    } else {
                        mMediaPlayer.pause();
                        v.setSelected(false);
                    }
                }

                break;

        }
    }

    private void pickFile() {
        // 음악 파일을 선택하고,
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent.createChooser(intent, "음악 파일 선택"), REQUEST_PICK_MUSIC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 정보들을 화면에 표시
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_MUSIC) {

            if (mMediaPlayer != null) {
                reset();
            }

            loadInfo(data);

        }

    }

    private void loadInfo(Intent data) {
        mMediaPlayer = MediaPlayer.create(this, data.getData());

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getApplicationContext(), data.getData());
        String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String duration = retriever
                .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        // 타이틀 변경
        getSupportActionBar().setTitle(title + "-" + artist);

        // 앨범 사진
        byte albumImage[] = retriever.getEmbeddedPicture();
        if (null != albumImage) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage, 0, albumImage.length);
            mImageView.setImageBitmap(bitmap);
        }

        // 나머지 정보 표시
        mMediaInfo.setText(album + " / " + artist);

        // 종료시간 표시
        long lDuration = Long.parseLong(duration);
        String endTime = mFormat.format(lDuration);
        mEndTime.setText(endTime);

        // Seek bar 최대값 설정
        mSeekBar.setMax(mMediaPlayer.getDuration());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mCurrentTime.setText(getTime(progress));
            mMediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class SeekBarUpdateTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (mMediaPlayer != null && mMediaPlayer.isPlaying()) {

                publishProgress();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            mCurrentTime.setText(getTime(mMediaPlayer.getCurrentPosition()));
            mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
        }
    }

    private void reset() {
        mMediaPlayer.reset();
        mCurrentTime.setText("0:00");
        mSeekBar.setProgress(0);
        mSeekBarUpdateTask = null;
        mPlayButton.setSelected(false);
    }

}
