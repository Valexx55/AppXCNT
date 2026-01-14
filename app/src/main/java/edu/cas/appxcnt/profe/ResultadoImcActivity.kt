package edu.cas.appxcnt.profe

import android.os.Bundle
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
        val imcString = traducirResultadoImc(imcFloat)

        val textoLeyenda = findViewById<TextView>(R.id.leyenda)
        textoLeyenda.text = imcString
    }


    fun traducirResultadoImc (resultado:Float):String
    {
        var imcResultado:String = ""

        val resultadoInt = resultado.toInt()

        imcResultado = when (resultadoInt) {
            in 1 until 16  -> "DESNUTRIDO"
            in 16 until  18 -> "DELGADO"
            in 18 until 25-> "IDEAL"
            in 25 until 31 -> "SOBREPESO"
            else -> "OBESO" // mayor o igual q 31
        }

        return imcResultado
    }
}