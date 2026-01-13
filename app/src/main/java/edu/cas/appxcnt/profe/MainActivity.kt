package edu.cas.appxcnt.profe

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /***
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //prepara lo que se va a ver
        setContentView(R.layout.activity_main)
        val strVersion : String = "VERSIÓN " +Build.VERSION.RELEASE + " API " + Build.VERSION.SDK_INT
        infoVersionLog(strVersion)
        //TODO mostrar la versión en la pantalla
        infoVersionPantalla(strVersion)
    }

    fun infoVersionLog (strVersion:String): Unit//como el VOID de Java no devuelve nada
    {

        Log.d(Constantes.ETIQUETA_LOG ,"Versión movil = ${strVersion}")

    }

    fun infoVersionPantalla (strVersion:String): Unit//como el VOID de Java no devuelve nada
    {

        //Log.d(Constantes.ETIQUETA_LOG ,"Versión movil = ${strVersion}")
        val cajaVersion : TextView = findViewById<TextView>(R.id.cajaVersion)
        //cajaVersion.setText(strVersion)
        cajaVersion.text = strVersion

    }

}