
package com.example.android.android_exam.musicPlayer;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();

    public static final String ACTION_PLAY = "com.example.android.android_exam.musicPlayer.ACTION_PLAY";
    public static final String ACTION_PAUSE = "com.example.android.android_exam.musicPlayer.ACTION_PAUSE";
    public static final String ACTION_RESET = "com.example.android.android_exam.musicPlayer.ACTION_RESET";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    private MediaPlayer mMediaPlayer;

    private MediaSessionCompat mSession;
    private MediaController mController;
    private MediaSessionManager mManager;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "뮤직 서비스 시작");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "뮤직 서비스 종료 ㅇㅇ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mSession == null) {

            mSession = new MediaSessionCompat(this, "tag", null, null);
        }

        String action = intent.getAction();

        switch (action) {
            case ACTION_RESET:
                if (mMediaPlayer != null) {
                    mMediaPlayer.reset();
                }
                break;
            case ACTION_PLAY:
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();}
                else {
                    mMediaPlayer.pause();
                }

                break;
            case ACTION_PAUSE:
                mMediaPlayer.pause();

                break;
                }


        return START_NOT_STICKY;        // 강제종료되었을 때 재시작하지 않는다.
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.d(TAG, "뮤직 서비스 : unBind");
    }

    public class LocalBinder extends Binder {
        MusicService getService() {
            // Return this instance of LocalService so clients can call public
            // methods
            return MusicService.this;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }
}
