package edu.cas.appxcnt.profe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InflarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inflar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.padre)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mostrarLayout(findViewById<View>(R.id.padre))
    }

    fun mostrarMensajeSalida(view: View) {

        val editext = findViewById<EditText>(R.id.editText)
        val nombre = editext.text.toString()

        Log.d(Constantes.ETIQUETA_LOG, "Nombre introducido = $nombre")

        val mensaje = mensajeSalida(nombre)

        val caja_resultado = findViewById<LinearLayout>(R.id.resultado)

        if (caja_resultado.childCount > 0) {
            //el layout de mensaje de salida ya ha sido inflado
            Log.d(Constantes.ETIQUETA_LOG, "Ya ha sido inflado el textview donde va el resultado")

            val textoResultado = findViewById<TextView>(R.id.mensaje_salida)
            textoResultado.text = mensaje
        } else {
            //hay que inflar
            Log.d(Constantes.ETIQUETA_LOG, "NO ha sido inflado el textview donde va el resultado, hay que inflar")

            val vista_inflada = layoutInflater.inflate(R.layout.mensaje_salida, findViewById<LinearLayout>(R.id.resultado))
            val textoResultado = vista_inflada.findViewById<TextView>(R.id.mensaje_salida)
            textoResultado.text = mensaje
        }



        mostrarLayout(findViewById<View>(R.id.padre))
    }

    fun mensajeSalida (nombre:String) : String
    {
        var mensaje:String = ""

            mensaje =  "El nombre tiene ${nombre.length} letras"

        return mensaje
    }

    /**
     * Recorro el XML
     */
    private fun mostrarLayout(vista: View) {
        Log.d(Constantes.ETIQUETA_LOG, "Recorriendo el XML ${vista.javaClass.canonicalName}")

        if (vista is ViewGroup) {
            val viewGroup = vista

            for (i in 0..<viewGroup.childCount) {
                val vistahija = viewGroup.getChildAt(i)
                mostrarLayout(vistahija)
            }
        }
    }
}