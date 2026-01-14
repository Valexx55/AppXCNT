package edu.cas.appxcnt.profe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularImc(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "El usuario ha tocado el botón de calcular")
        //TODO validar
        // OBTENERPESO
        val peso = obtenerPeso()
        // OBTENER ALTURA
        val altura = obtenerAltura()
        // HACER EL CÁLCULO
        val resultadoImcNum = calculoIMCNumerico(peso, altura)
        // MOSTRAR
        mostrarResultadoImcNum(resultadoImcNum)
    }

    fun obtenerPeso (): Float
    {
        var peso:Float = 0f

            peso = this.findViewById<EditText>(R.id.editTextPeso).text.toString().toFloat()

        return peso

    }

    fun obtenerAltura (): Float
    {
        var altura:Float = 0f

            altura = this.findViewById<EditText>(R.id.editTextAltura).text.toString().toFloat()

        return altura
    }

    fun calculoIMCNumerico (peso:Float, altura:Float): Float
    {
        var resultadoImc : Float = 0f

            resultadoImc = peso / (altura*altura)

        return resultadoImc

    }

    fun mostrarResultadoImcNum (imcNum:Float)
    {
        val notiToast = Toast.makeText(this, "Su IMC es $imcNum", Toast.LENGTH_LONG)
        notiToast.show()
    }

}