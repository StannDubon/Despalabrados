package com.example.expo_ortografia

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

private var reto: String = "No se encontro ninguna pregunta"
private var tiempoReto: Int = 10
private var seconds: Long = 0


private lateinit var lblReto:TextView
private lateinit var lblTimerReto:TextView
private lateinit var btnAceptarReto:ImageView
private lateinit var countdownTimer: CountDownTimer
private lateinit var lblStart:TextView

private lateinit var fotito:ImageView

class GenerarReto : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_reto)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        getRetos4form()

        lblReto=findViewById(R.id.lblReto)
        lblReto.setText(reto)
        lblTimerReto=findViewById(R.id.lblTimerReto)
        btnAceptarReto=findViewById(R.id.btnAceptarReto)
        lblStart=findViewById(R.id.lblStart)

        fotito=findViewById(R.id.fotitoreto)

        fotito.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down))

        lblTimerReto.visibility = View.GONE

        btnAceptarReto.setOnClickListener {
            lblTimerReto.visibility = View.VISIBLE
            lblStart.visibility=View.VISIBLE
            btnAceptarReto.visibility = View.VISIBLE

            btnAceptarReto.setImageResource(R.drawable.btnstyle1)
            lblStart.text = "STOP"

            val initialTimeInMillis = (tiempoReto + 1) * 1000
            val countDownInterval = 1000

            countdownTimer = object : CountDownTimer(initialTimeInMillis.toLong(), countDownInterval.toLong()) {
                override fun onTick(millisUntilFinished: Long) {
                    seconds = millisUntilFinished / 1000
                    lblTimerReto.text = seconds.toString()
                }

                override fun onFinish() {
                    val intent = Intent(this@GenerarReto, Tiempo::class.java)
                    val xd = tiempoReto - seconds
                    println(seconds.toString())
                    intent.putExtra("tiempoTardado", xd)
                    startActivity(intent)
                    finish()
                }
            }

            countdownTimer.start() // Inicia el temporizador

            btnAceptarReto.setOnClickListener {
                val intent = Intent(this@GenerarReto, Tiempo::class.java)
                val xd = tiempoReto - seconds
                println(seconds.toString())
                intent.putExtra("tiempoTardado", xd)
                startActivity(intent)
                finish()
            }
        }

        /*
        imgChek.setOnClickListener {
            val intent = Intent(this@GenerarReto, Tiempo::class.java)
            val xd = tiempoReto - seconds
            println(seconds.toString())
            intent.putExtra("tiempoTardado", xd)
            startActivity(intent)
            finish()
        }

         */
    }

    private fun getRetos4form() {
        val resources: Resources = resources

        val retoManager = getReto(resources)
        val retoAleatorio = retoManager.getPreguntas()

        if (retoAleatorio != null) {
            reto = retoAleatorio.challenge
            tiempoReto = retoAleatorio.time

        } else {
            println("No se encontraron preguntas en el archivo CSV.")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
        countdownTimer.cancel()
    }

    override fun onDestroy() {
        setResult(Activity.RESULT_CANCELED)
        super.onDestroy()
        countdownTimer.cancel()
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