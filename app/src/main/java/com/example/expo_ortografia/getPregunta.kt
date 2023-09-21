package com.example.expo_ortografia

import android.content.res.Resources
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader
import kotlin.random.Random

data class setPregunta(
    val pregunta: String,
    val respuestaCorrecta: String,
    val respuestaIncorrecta1: String,
    val respuestaIncorrecta2: String,
    val respuestaIncorrecta3: String,
    val tiempo: String,
    val razon: String
)

class getPregunta(private val resources: Resources) {

    fun getPreguntas(): setPregunta? {
        val inputStream = resources.openRawResource(R.raw.preguntas)
        val reader = CSVReaderBuilder(InputStreamReader(inputStream))
            .withSkipLines(0)
            .build()

        val filas = reader.readAll()
        reader.close()

        if (filas.isEmpty()) {
            return null
        }

        val filaAleatoria = filas[GeneradorSuperAleatorio(0, 36).obtenerNumeroUnico()]

        return setPregunta(
            filaAleatoria[0],
            filaAleatoria[1],
            filaAleatoria[2],
            filaAleatoria[3],
            filaAleatoria[4],
            filaAleatoria[5],
            filaAleatoria[6]
        )
    }
}