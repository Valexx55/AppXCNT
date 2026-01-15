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
        val cajaNum1 = findViewById<EditText>(R.id.etn1)
        val numero1 = cajaNum1.text.toString().toInt()
        val cajaNum2 = findViewById<EditText>(R.id.etn2)
        val numero2 = cajaNum2.text.toString().toInt()
        val resultado = "LA SUMA ES ${numero1 + numero2}"
        val n3 = numero1+numero2
        var resultadoi18n = resources.getString(R.string.mensaje_resultado_suma, numero1, numero2, n3 )


        //val notificacionToast = Toast.makeText(this, resultado, Toast.LENGTH_LONG)
        val notificacionToast = Toast.makeText(this, resultadoi18n, Toast.LENGTH_LONG)
        notificacionToast.show()

        /**
         *
         * NOTAS i18n parámetros variables
         *
         *
         * | Tipo                 | Placeholder | Ejemplo  |
         * | -------------------- | ----------- | -------- |
         * | String               | `%s`        | `%1$s`   |
         * | Entero (Int)         | `%d`        | `%2$d`   |
         * | Long                 | `%d`        | `%1$d`   |
         * | Float / Double       | `%f`        | `%1$.2f` |
         * | Carácter             | `%c`        | `%1$c`   |
         * | Boolean (como texto) | `%b`        | `%1$b`   |
         * | Hexadecimal          | `%x` / `%X` | `%1$x`   |
         * | Octal                | `%o`        | `%1$o`   |
         *
         */

    }
}