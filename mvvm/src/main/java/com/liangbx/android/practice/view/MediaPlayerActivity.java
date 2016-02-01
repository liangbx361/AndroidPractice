package com.liangbx.android.practice.view;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.liangbx.android.practice.R;
import com.liangbx.android.practice.log.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private String path = "http://v8.huayu.nd/b/p/1625/0bb37db056cf4235a82bb8878728acce/0bb37db056cf4235a82bb8878728acce.a.mp4";

    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private MediaPlayerEvent mMediaplayerEvent;
    private boolean pause;
    private long position;

    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnStop;
    private Button mBtnReset;

    private Subscription playingSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        initView();
        initMediaPlayer();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.sv);
        mBtnPlay = (Button) findViewById(R.id.btn_play);
        mBtnPlay.setOnClickListener(this);
        mBtnPause = (Button) findViewById(R.id.btn_pause);
        mBtnPause.setOnClickListener(this);
        mBtnStop = (Button) findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(this);
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnReset.setOnClickListener(this);
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceView.getHolder().setFixedSize(640, 480);
        mSurfaceView.getHolder().setKeepScreenOn(true);
        mSurfaceView.getHolder().addCallback(new SurfaceCallback());

        mMediaplayerEvent = new MediaPlayerEvent();
        mMediaPlayer.setOnBufferingUpdateListener(mMediaplayerEvent);
        mMediaPlayer.setOnCompletionListener(mMediaplayerEvent);
        mMediaPlayer.setOnErrorListener(mMediaplayerEvent);
        mMediaPlayer.setOnInfoListener(mMediaplayerEvent);
        mMediaPlayer.setOnSeekCompleteListener(mMediaplayerEvent);
        mMediaPlayer.setOnVideoSizeChangedListener(mMediaplayerEvent);
    }

    private void play(MediaPlayer mediaPlayer, String path) {
        play(0);
    }

    private void pause(MediaPlayer mediaPlayer) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            pause = true;
        } else {
            if (pause) {
                mediaPlayer.start();
                pause = false;
            }
        }

        showBtnSate(pause);
    }

    private void showBtnSate(boolean pause) {
        if(pause) {
            mBtnPause.setText("恢复");
        } else {
            mBtnPause.setText("暂停");
        }
    }

    private void stop(MediaPlayer mediaPlayer) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    private void reset(MediaPlayer mediaPlayer) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        } else {
            play(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                play(mMediaPlayer, path);
                break;

            case R.id.btn_pause:
                pause(mMediaPlayer);
                break;

            case R.id.btn_stop:
                stop(mMediaPlayer);
                break;

            case R.id.btn_reset:
                reset(mMediaPlayer);
                break;
        }
    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (position > 0 && path != null) {
                play(position);
                Logger.debug(this, "surfaceCreated --> position " + position);
                position = 0;
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mMediaPlayer.isPlaying()) {
                position = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.stop();

                com.liangbx.android.practice.log.Logger.debug(this, "surfaceDestroyed --> position " + position);
            } else if(pause) {
                //暂停状态
                position = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.stop();
                Logger.debug(this, "surfaceDestroyed --> pause " + position);
            }
        }
    }

    private void play(long position) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new PrepareListener(position));

            startPlayingListener(mMediaPlayer, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final class PrepareListener implements MediaPlayer.OnPreparedListener {
        private long position;

        public PrepareListener(long position) {
            this.position = position;
        }

        public void onPrepared(MediaPlayer mp) {
            mMediaPlayer.start();
            if (position > 0) {
                mMediaPlayer.seekTo((int) position);
            }
            if(pause) {
                pause(mMediaPlayer);
            }
        }
    }

    private final class MediaPlayerEvent implements
            MediaPlayer.OnBufferingUpdateListener,
            MediaPlayer.OnCompletionListener,
            MediaPlayer.OnErrorListener,
            MediaPlayer.OnInfoListener,
            MediaPlayer.OnSeekCompleteListener,
            MediaPlayer.OnVideoSizeChangedListener {

        private String TAG = this.getClass().getSimpleName();

        /**
         * 整个视频缓冲百分比
         *
         * @param mp
         * @param percent
         */
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {

            if(playingSubscription.isUnsubscribed()) {
                startPlayingListener(mp, 0);
            }

            Log.d(TAG, "onBufferingUpdate=" + percent);
        }

        /**
         * 播放结束
         *
         * @param mp
         */
        @Override
        public void onCompletion(MediaPlayer mp) {

            Log.d(TAG, "onCompletion=");
        }

        /**
         * 视频播放发生错误，FIXME 出错类型需要列出
         *
         * @param mp
         * @param what
         * @param extra
         * @return
         */
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {

            Log.d(TAG, "onError --> what " + what + " --> extra " + extra);
            return false;
        }

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {

            Log.d(TAG, "onInfo --> what " + what + " --> extra " + extra);
            return false;
        }

        @Override
        public void onSeekComplete(MediaPlayer mp) {

            Log.d(TAG, "onSeekComplete=");
        }


        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

            Log.d(TAG, "onInfo --> width " + width + " --> height " + height);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        int position = 0;

        if(mMediaPlayer.isPlaying()) {
            position = mMediaPlayer.getCurrentPosition();
        }

        Logger.debug(this, "onPause --> position " + position);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.debug(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.debug(this, "onDestroy");

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    private void startPlayingListener(final MediaPlayer mediaPlayer, long startPosition) {
        if(playingSubscription != null && playingSubscription.isUnsubscribed()) {
            playingSubscription.unsubscribe();
        }

        final long[] lastPosition = {startPosition};
        playingSubscription = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long time) {
                        boolean isPlaying = false;

                        Logger.debug(this, "interval time --> " + time);
                        if (mediaPlayer != null) {
                            if(mMediaPlayer.isPlaying()) {
                                long currentPosition = mediaPlayer.getCurrentPosition();
                                if (currentPosition > lastPosition[0]) {
                                    isPlaying = true;
                                }
                                lastPosition[0] = currentPosition;
                            } else {
//                                throw new NullPointerException();
                            }
                            return isPlaying;
                        } else {
                            throw new NullPointerException();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isPlaying -> {
                    if (isPlaying) {
                        Logger.debug(this, "视频播放中...");
                    } else {
                        Logger.debug(this, "视频缓冲中...");
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    Logger.debug(this, "视频播放监听失败...");
                });
    }

    private void startPlayingListenerThrowable(final MediaPlayer mediaPlayer, long startPosition) {
        if(playingSubscription != null && playingSubscription.isUnsubscribed()) {
            playingSubscription.unsubscribe();
        }

        final long[] lastPosition = {startPosition};
        playingSubscription = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long time) {
                        boolean isPlaying = false;

                        Logger.debug(this, "interval time --> " + time);
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            if(mMediaPlayer.isPlaying()) {
                                long currentPosition = mediaPlayer.getCurrentPosition();
                                if (currentPosition > lastPosition[0]) {
                                    isPlaying = true;
                                }
                                lastPosition[0] = currentPosition;
                                throw new NullPointerException();
                            } else {
                                throw new NullPointerException();
                            }
//                            return isPlaying;
                        } else {
                            throw new NullPointerException();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isPlaying -> {
                    if (isPlaying) {
                        Logger.debug(this, "视频播放中...");
                    } else {
                        Logger.debug(this, "视频加载中...");
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    Logger.debug(this, "视频播放检测失败...");
                });
    }
}
