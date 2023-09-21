package com.example.expo_ortografia

import android.content.res.Resources
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader
import kotlin.random.Random

data class setReto(
    val challenge: String,
    val time: Int
)

class getReto(private val resources: Resources) {

    fun getPreguntas(): setReto? {
        val inputStream = resources.openRawResource(R.raw.retos)
        val reader = CSVReaderBuilder(InputStreamReader(inputStream))
            .withSkipLines(0)
            .build()

        val filas = reader.readAll()
        reader.close()

        if (filas.isEmpty()) {
            return null
        }

        while (true) {
            //val filaAleatoria = filas[Random.nextInt(filas.size)]
            val filaAleatoria = filas[GeneradorSuperAleatorio(0, 25).obtenerNumeroUnico()]

            try {
                val timeValue = filaAleatoria[1].toInt()

                return setReto(
                    filaAleatoria[0],
                    timeValue
                )
            } catch (e: NumberFormatException) {
                continue
            }
        }
    }
}