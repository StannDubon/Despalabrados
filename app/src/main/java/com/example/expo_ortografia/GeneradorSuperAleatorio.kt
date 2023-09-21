package com.example.expo_ortografia

import kotlin.random.Random

class GeneradorSuperAleatorio(private val desde: Int, private val hasta: Int) {
    private val rango = (desde until hasta).toList()
    private val numerosGenerados = mutableListOf<Int>()

    init {
        if (desde >= hasta) {
            throw IllegalArgumentException("El primer número debe ser menor que el último número.")
        }
    }

    fun obtenerNumeroUnico(): Int {
        if (numerosGenerados.size >= rango.size) {
            // Si se han generado todos los números posibles, reinicia la lista
            numerosGenerados.clear()
        }

        val numerosDisponibles = rango.filterNot { numerosGenerados.contains(it) }

        if (numerosDisponibles.isEmpty()) {
            throw IllegalStateException("Todos los números posibles en el rango se han generado.")
        }

        val numeroAleatorio = numerosDisponibles[Random.nextInt(numerosDisponibles.size)]
        numerosGenerados.add(numeroAleatorio)
        return numeroAleatorio
    }
}