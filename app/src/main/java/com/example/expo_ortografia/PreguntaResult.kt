package com.example.expo_ortografia

import android.annotation.SuppressLint
import android.app.Activity
import com.caverock.androidsvg.SVG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

private var resultSet:Int = 0

private lateinit var constraint: ConstraintLayout
private lateinit var valor: TextView
private lateinit var razon: TextView
private lateinit var correcta: TextView
private lateinit var home: ImageView

private lateinit var fotito:ImageView

class PreguntaResult : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_result)
        constraint=findViewById(R.id.preguntaResultClMain)
        valor=findViewById(R.id.preguntaResultLblValor)
        razon=findViewById(R.id.preguntaResultLblRazon)
        correcta=findViewById(R.id.preguntaResultLblRespuestaCorrecta)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        home = findViewById(R.id.imageViewHomeTiempo2)
        home.setOnClickListener {
            finish()
        }

        fotito=findViewById(R.id.preguntaresultfotito)
        fotito.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down))

        val razon1 = intent.getStringExtra("razon")
        resultSet = intent.getIntExtra("Tipo", 0)

        razon.setText(razon1)

        if(resultSet==1)
        {
            val Right = intent.getStringExtra("RightAnswer")
            correcta.setText("La respuesta correcta es: $Right")
            valor.setText("Respuesta Correcta")
            constraint.setBackgroundResource(R.drawable.bg_green)
        }
        if(resultSet==2)
        {
            val Right = intent.getStringExtra("RightAnswer")
            correcta.setText("La respuesta correcta es: $Right")
            valor.setText("Respuesta Incorrecta")
            constraint.setBackgroundResource(R.drawable.bg_red)
        }
        if(resultSet==3)
        {
            val Right = intent.getStringExtra("RightAnswer")
            correcta.setText("La respuesta correcta es: $Right")
            valor.setText("Se acabó el tiempo")
            constraint.setBackgroundResource(R.drawable.bluebackground)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        setResult(Activity.RESULT_CANCELED)
        super.onDestroy()
        finish()
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