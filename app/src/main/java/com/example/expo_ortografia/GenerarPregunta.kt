package com.example.expo_ortografia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import android.content.res.Resources
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView

private lateinit var lblPregunta: TextView
private lateinit var btn1 : Button
private lateinit var btn2 : Button
private lateinit var btn3 : Button
private lateinit var btn4 : Button

private lateinit var countdownTimer: CountDownTimer
private lateinit var textViewTimer: TextView

private var idPregunta: Int = 0
private var pregunta: String = "No se encontro ninguna pregunta"
private var razon: String = "No se encontro ninguna pregunta"
private var tiempoPregunta: Int = 0

private var question:String? = null
private var rc:String? = null
private val answers: ArrayList<String?> = ArrayList()
private var time:Int = 0
private var reason:String? = null

private lateinit var fotito:ImageView

class GenerarPregunta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_pregunta)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )

        answers.clear()
        getPreguntas4form()

        fotito=findViewById(R.id.generar_pregunta_fotito)
        fotito.startAnimation(AnimationUtils.loadAnimation(this, R.anim.up_down))

        //Timer
        textViewTimer = findViewById(R.id.textViewTimer)
        val initialTimeInMillis = (time+1) * 1000
        val countDownInterval = 1000

        countdownTimer = object : CountDownTimer(initialTimeInMillis.toLong(), countDownInterval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                textViewTimer.text = seconds.toString()
            }

            override fun onFinish() {
                val intent = Intent(this@GenerarPregunta, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 3)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()
            }
        }

        countdownTimer.start()

        //Preguntas
        lblPregunta=findViewById(R.id.lblPregunta)
        lblPregunta.setText(question)


        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)

        val botones = listOf(btn1, btn2, btn3, btn4)
        val respuestasAleatorias = answers.shuffled().take(botones.size)

        for (i in 0 until botones.size) {
            val boton = botones[i]
            val respuesta = respuestasAleatorias[i]
            boton.text = respuesta
        }

        btn1.setOnClickListener {
            if (btn2.text.toString()==rc){
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 1)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}
            else{
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 2)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}}

        btn2.setOnClickListener {
            if (btn2.text.toString()==rc){
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 1)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}
            else{
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 2)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}}

        btn3.setOnClickListener {
            if (btn3.text.toString()==rc){
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 1)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}
            else{
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 2)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}}

        btn4.setOnClickListener {
            if (btn4.text.toString()==rc){
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 1)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}
            else{
                val intent = Intent(this, PreguntaResult::class.java)
                intent.putExtra("razon", reason)
                intent.putExtra("Tipo", 2)
                intent.putExtra("RightAnswer", rc)
                startActivity(intent)
                finish()}}

    }

    //Obtener la cantidad de preguntas en la base de datos
    private fun getPreguntas4form(){
        val resources: Resources = resources

        val preguntaManager = getPregunta(resources)
        val preguntaAleatoria = preguntaManager.getPreguntas()

        if (preguntaAleatoria != null) {
            question = preguntaAleatoria.pregunta
            rc = preguntaAleatoria.respuestaCorrecta
            val ri_0 = preguntaAleatoria.respuestaIncorrecta1
            val ri_1 = preguntaAleatoria.respuestaIncorrecta2
            val ri_2 = preguntaAleatoria.respuestaIncorrecta3
            time = preguntaAleatoria.tiempo.toInt()
            reason = preguntaAleatoria.razon

            answers.add(rc)
            answers.add(ri_0)
            answers.add(ri_1)
            answers.add(ri_2)
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