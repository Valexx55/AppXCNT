package edu.cas.appxcnt.practicaskotlin

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    val time1 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            printForecast()
            printTemperature()
        }
    }
    //ejecución más rápida: paralela
    val time2 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
        }
    }

    println("T1 = $time1 T2 = $time2")
}

suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}