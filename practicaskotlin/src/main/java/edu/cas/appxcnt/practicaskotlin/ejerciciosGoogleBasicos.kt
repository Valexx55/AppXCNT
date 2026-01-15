package edu.cas.appxcnt.practicaskotlin

/*fun main() {

    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)

}*/
/*
fun main() {
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is ${ticketPriceTulio(child, isMonday)}.")
    println("The movie ticket price for a person aged $child is ${ticketPriceWhenSubject(child, isMonday)}.")
    println("The movie ticket price for a person aged $child is ${ticketPriceWhenSinSubject(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is ${ticketPriceTulio(adult, isMonday)}.")
    println("The movie ticket price for a person aged $adult is ${ticketPriceWhenSubject(adult, isMonday)}.")
    println("The movie ticket price for a person aged $adult is ${ticketPriceWhenSinSubject(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is ${ticketPriceTulio(senior, isMonday)}.")
    println("The movie ticket price for a person aged $senior is ${ticketPriceWhenSubject(senior, isMonday)}.")
    println("The movie ticket price for a person aged $senior is ${ticketPriceWhenSinSubject(senior, isMonday)}.")
}*/

fun main() {
    //DE Celsius a Faranheit
    printFinalTemperature(
        27.0,
        "Celsius",
        "Fahrenheit",
        {temperaturaInicial -> (temperaturaInicial*9)/5+32} //
    )
 //XAVIER
    printFinalTemperature(
        350.0,
        "Kelvin",
        "Celsius",
        {temperaturaInicial -> (temperaturaInicial-273.15)}
    )

    printFinalTemperature(
        10.0,
        "Fahrenheit",
        "Kelvin",
        {temperaturaInicial -> (temperaturaInicial - 32)*5/9+273.15}
    )
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

/**
 * 27.0 degrees Celsius is 80.60 degrees Fahrenheit./De grados Celsius a Fahrenheit: °F = 9/5 (°C) + 32
 * 350.0 degrees Kelvin is 76.85 degrees Celsius.
 * 10.0 degrees Fahrenheit is 260.93 degrees Kelvin.
 */

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    // Fill in the code.
    return 0
}


fun ticketPriceTulio(age: Int, isMonday: Boolean): Int {
    if (age <= 12)
        return 15
    else if (age > 12 && age <= 60) {
        if (isMonday)
            return  25
        else
            return 30
    } else if (age > 60 && age <= 100)
        return 20
    return -1
}

fun ticketPriceWhenSubject(age: Int, isMonday: Boolean): Int {
    return when (age) {
        in 0..12 -> 15
        in 13..60 -> if (isMonday) 25 else 30
        in 61..100 -> 20
        else -> -1
    }
}

fun ticketPriceWhenSinSubject(age: Int, isMonday: Boolean): Int {
    //si el when, es usado como expresión, retorna un valor, es impresncindible indicar el else (caso por defecto)
    return when {
        age in 0..12 -> 15
        age in 13..60 && isMonday -> 25
        age in 13..60  -> 30
        age in 61..100 -> 20
        //age !in 0..100 -> -1
        else -> -1
    }
}

fun printNotificationSummary(numberOfMessages: Int) {

    if (numberOfMessages < 100) {

        println("You have $numberOfMessages notifications.")
    } else {

        println("Your phone is blowing up! You have 99+ notifications.")
    }
}