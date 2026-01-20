package edu.cas.appxcnt.profe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.databinding.ActivityBusquedaBinding

class BusquedaActivity : AppCompatActivity() {

    lateinit var binding: ActivityBusquedaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun buscarEnGoogle(view: View) {
        val busqueda: String = binding.textoBusqueda.text.toString()

        Log.d(Constantes.ETIQUETA_LOG, "El usuario quiere buscar $busqueda")

        val url:String = "https://www.google.com/search?q=$busqueda"
        //uri
        val uri = url.toUri()//la búsqueda normalilzada
        //https://www.google.com/search?q=asfb
        Log.d(Constantes.ETIQUETA_LOG, "URI =  $uri")
        //intent Implícito
        val intentBusqueda = Intent(Intent.ACTION_VIEW, uri)//"QUIERO VER; UNA WEB"
        if (intentBusqueda.resolveActivity(packageManager)!=null)
        {
            Log.d(Constantes.ETIQUETA_LOG, "Este dispositivo sí cuenta con al menos una app que puede ver sitios web")
            //CHOOSER
            //startActivity(intentBusqueda)
            startActivity(Intent.createChooser(intentBusqueda, "Elige navegador"))
        } else {
            //Emoticono windows + . punto
            Toast.makeText(this, "No se ha detectado un navegador 😥  ", Toast.LENGTH_LONG ).show()
        }

    }
}