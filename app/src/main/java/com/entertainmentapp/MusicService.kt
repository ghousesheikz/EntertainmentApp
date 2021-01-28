package com.entertainmentapp

import android.R
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast





class MusicService : Service() , MediaPlayer.OnErrorListener{
    private val mBinder: IBinder = ServiceBinder()
    var mPlayer: MediaPlayer? = null
    private var length = 0

    fun MusicService() {}

    class ServiceBinder : Binder() {
     fun  getService() : MusicService{
           return MusicService()
       }
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
       // mPlayer = MediaPlayer.create(this)
        mPlayer!!.setOnErrorListener(this)
        if (mPlayer != null) {
            mPlayer!!.isLooping = true
            mPlayer!!.setVolume(100f, 100f)
        }
        mPlayer!!.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                onError(mPlayer, what, extra)
                return true
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mPlayer!!.start()
        return START_STICKY
    }

    fun pauseMusic() {
        if (mPlayer!!.isPlaying) {
            mPlayer!!.pause()
            length = mPlayer!!.currentPosition
        }
    }

    fun resumeMusic() {
        if (mPlayer!!.isPlaying == false) {
            mPlayer!!.seekTo(length)
            mPlayer!!.start()
        }
    }

    fun stopMusic() {
        mPlayer!!.stop()
        mPlayer!!.release()
        mPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
        return false
    }
}