package edu.cas.appxcnt.profe.perros

import retrofit2.http.GET
import retrofit2.http.Path

interface PerroService {

    @GET("/api/breed/{raza}/images")
    suspend fun getDogImages(@Path("raza") raza: String) : PerroRespuesta
}