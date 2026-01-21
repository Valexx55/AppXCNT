package edu.cas.appxcnt.profe.productos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.util.RedUtil
import kotlinx.coroutines.launch

class ListaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (RedUtil.hayInternet(this)) {
            Log.d(Constantes.ETIQUETA_LOG, "Sí hay conexión a internet")

            //ESTA SECCIÓN REPRESENTA EL ÁREA DE LA CORRUTINA
            //DENTRO DE ELLA, PUEDE HABER LLAMADAS BLOQUEANTES
            //COMO POR EJEMPLO, SERÁ LA CONEXIÓN A INTERNET CON RETROFIT
            //TODO: explicar el sentido de lifecycleScope
            lifecycleScope.launch {

            }

        } else {
            val toast = Toast.makeText(this, "Sin  conexión a internet", Toast.LENGTH_LONG)
            toast.show()

        }


    }

    //
}