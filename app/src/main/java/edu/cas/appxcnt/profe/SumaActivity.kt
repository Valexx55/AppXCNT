package edu.cas.appxcnt.profe

import android.R.attr.duration
import android.R.attr.text
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SumaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suma)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun sumar(view: View)
    {
        Log.d(Constantes.ETIQUETA_LOG, "El usuario ha tocado sumar")
        //TODO acceder al valor de la caja 1
        val cajaNum1 = findViewById<EditText>(R.id.etn1)
        val numero1 = cajaNum1.text.toString().toInt()
        //TODO acceder al valor de la caja 2
        val cajaNum2 = findViewById<EditText>(R.id.etn2)
        val numero2 = cajaNum2.text.toString().toInt()
        //TODO Sumarlos e informalos
        val resultado = "LA SUMA ES ${numero1 + numero2}"

        val notificacionToast = Toast.makeText(this, resultado, Toast.LENGTH_LONG)
        notificacionToast.show()

    }
}