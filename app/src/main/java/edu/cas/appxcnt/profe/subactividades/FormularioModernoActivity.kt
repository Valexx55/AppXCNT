package edu.cas.appxcnt.profe.subactividades

import android.R.attr.data
import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityFormularioModernoBinding

class FormularioModernoActivity : AppCompatActivity() {



    //subactivities de la forma "nueva"
    lateinit var lanzadorColorFavorito: ActivityResultLauncher<Intent>
    lateinit var binding:ActivityFormularioModernoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormularioModernoBinding.inflate(layoutInflater)


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        lanzadorColorFavorito = registerForActivityResult (
            ActivityResultContracts.StartActivityForResult() //lo que lanzo es una actividad
        ){
            //la función que recibe el resultado
                result ->
            if (result.resultCode == Activity.RESULT_OK)
            {
                Log.d(Constantes.ETIQUETA_LOG, "La subactividad ha FINALIZADO BIEN ${result.resultCode}")
                val intent_resultado = result.data
                val color = intent_resultado?.getIntExtra("COLOR_ELEGIDO", 0) ?: 0
                binding.colorFavorito.setBackgroundColor(color)
            } else {
                Log.d(Constantes.ETIQUETA_LOG, "La subactividad ha FINALIZADO MAL ${result.resultCode}")
            }

        }

    }

    fun seleccionarColorFavorito(view: View) {


        val intentSelColor = Intent(this, ColorSubActivity::class.java)
        //startActivityForResult(intentSelColor, 85) //forma vieja deprecada

        //forma moderna
        lanzadorColorFavorito.launch(intentSelColor)

    }

    //startActivityForResult forma antigua
    override fun onActivityResult(
        requestCode: Int, //cod id la conversación
        resultCode: Int, //si bien o mal
        intentRes: Intent? //tiene el color seleccionado en la otra actividad
    ) {
        super.onActivityResult(requestCode, resultCode, intentRes)

        if (resultCode == Activity.RESULT_OK)
        {
            //accedo al color
            val colorSeleccionado = intentRes?.getIntExtra("COLOR_ELEGIDO", 0) ?: 0

            Log.d(Constantes.ETIQUETA_LOG, "Color elegido = $colorSeleccionado")
            binding.colorFavorito.setBackgroundColor(colorSeleccionado)
        }
    }

    fun mostrarInfoFormulario(view: View) {}
    fun borrarUsuarioPrefs(view: View) {}
}