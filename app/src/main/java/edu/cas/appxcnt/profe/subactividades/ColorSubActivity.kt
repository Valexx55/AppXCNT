package edu.cas.appxcnt.profe.subactividades

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R

class ColorSubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_color_sub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun colorSeleccionado(view: View) {

        val fondo = view.background//extraigo el color de la view tocada
        val colorfondo = fondo as ColorDrawable//casting
        val color = colorfondo.color
        Log.d(Constantes.ETIQUETA_LOG, "COLOR SELECCIONADO = $color")

        //guardar el color seleccioando como la resultado de la actividad
        //y finalizar la actividad
        val intent_resutaldo = Intent() //este intent, representa el valor devuelto por la actividad  memoria temporal
        intent_resutaldo.putExtra("COLOR_ELEGIDO", color)

        setResult(RESULT_OK, intent_resutaldo)
        finish()

    }
}