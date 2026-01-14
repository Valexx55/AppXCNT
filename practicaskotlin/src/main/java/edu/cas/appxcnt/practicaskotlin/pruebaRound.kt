package edu.cas.appxcnt.practicaskotlin

import kotlin.math.roundToInt

fun main() {
    var numero = 13.35
    var numero2 = 13.56

    println(" Redondeo 1 13.35 = ${numero.roundToInt()}") //redondea hacia abajo 13
    println(" Redondeo 1 13.56 = ${numero2.roundToInt()}") //hacia arriba 14

    println(" Redondeo 1 13.35 = ${numero.toInt()}")//hacia abajo 13
    println(" Redondeo 1 13.56 = ${numero2.toInt()}")//hacia abajo 13
}