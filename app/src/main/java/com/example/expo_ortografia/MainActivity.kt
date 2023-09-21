package com.example.expo_ortografia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout

private lateinit var btnPregunta: LinearLayout
private lateinit var btnReto: LinearLayout

private lateinit var fotito1:ImageView
private lateinit var fotito2:ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val musicPlayer = MusicPlayer()
        musicPlayer.play(this, R.raw.music_main_screen)

        fotito1=findViewById(R.id.mainfotito1)
        fotito2=findViewById(R.id.mainfotito2)

        fotito1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down))
        fotito2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down))

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        btnPregunta=findViewById(R.id.btnGenerarPregunta)
        btnPregunta.setOnClickListener {
            musicPlayer.stop()
            val intent = Intent(this, GenerarPregunta::class.java)
            startActivity(intent)
        }

        btnReto=findViewById(R.id.btnGenerarReto)
        btnReto.setOnClickListener {
            musicPlayer.stop()
            val intent = Intent(this, GenerarReto::class.java)
            startActivity(intent)
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )
    }

}