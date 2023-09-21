package com.example.expo_ortografia

import android.content.Context
import android.media.MediaPlayer

class MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun play(context: Context, resourceId: Int) {
        stop() // Detener la reproducción actual antes de cambiar de canción

        mediaPlayer = MediaPlayer.create(context, resourceId)
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.release()
            }
        }
        mediaPlayer = null
    }

    fun changeSong(context: Context, resourceId: Int) {
        stop() // Detener la reproducción actual antes de cambiar de canción

        mediaPlayer = MediaPlayer.create(context, resourceId)
        mediaPlayer?.start()
    }
}