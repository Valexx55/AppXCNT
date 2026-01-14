package edu.cas.appxcnt.profe

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //leemos el dato del imc
        val imcFloat = intent.getFloatExtra("resultado_imc",0f)
        val parResultado  = traducirResultadoImc(imcFloat)

        val textoLeyenda = findViewById<TextView>(R.id.leyenda)
        textoLeyenda.text = parResultado.first//el string

        val cuadroImagen = findViewById<ImageView>(R.id.imagenImc)
        cuadroImagen.setImageResource(parResultado.second)
    }


    fun traducirResultadoImc (resultado:Float): Pair<String, Int>
    {

        val resultadoInt = resultado.toInt()

        val parResultado = when (resultadoInt) {
            in 1 until 16  -> Pair ("DESNUTRIDO", R.drawable.desnutrido)
            in 16 until  18 -> Pair ("DELGADO", R.drawable.delgado)
            in 18 until 25-> Pair ("IDEAL", R.drawable.ideal)
            in 25 until 31 -> Pair ("SOBREPRESO", R.drawable.sobrepeso)
            else -> Pair ("OBESO", R.drawable.obeso)
        }

        return parResultado
    }
}