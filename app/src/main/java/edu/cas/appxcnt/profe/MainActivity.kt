package edu.cas.appxcnt.profe

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /***
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //prepara lo que se va a ver
        setContentView(R.layout.activity_main)
        infoVersion()
    }

    fun infoVersion (): Unit//como el VOID de Java no devuelve nada
    {
          val strVersion : String = "VERSIÓN " +Build.VERSION.RELEASE + " API " + Build.VERSION.SDK_INT
          Log.d("MIAPP", "Versión movil = ${strVersion}")

    }

}