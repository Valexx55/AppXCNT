package edu.cas.appxcnt.profe.productos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * En este objeto, describimos detalles de la comunicación HTTP para retrofit
 * creando la implemtanción del service
 *
 */
object ProductosRetrofitHelper {

    const val RUTA_BASE = "https://my-json-server.typicode.com"

    val retrofit = Retrofit.Builder().baseUrl(RUTA_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    fun getProductoServiceInstance(): ProductoService
    {
        val productoService = retrofit.create(ProductoService::class.java)
        return productoService
    }

}