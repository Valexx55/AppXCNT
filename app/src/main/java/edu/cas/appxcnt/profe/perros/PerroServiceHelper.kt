package edu.cas.appxcnt.profe.perros

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object PerroServiceHelper {

    const val BASE_URL = "https://dog.ceo"

    val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getDogServiceInstance(): PerroService {

        return retrofit.create(PerroService::class.java)
    }
}