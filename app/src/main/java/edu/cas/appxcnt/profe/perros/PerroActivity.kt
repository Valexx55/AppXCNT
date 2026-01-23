package edu.cas.appxcnt.profe.perros

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityPerroBinding
import edu.cas.appxcnt.profe.util.RedUtil
import kotlinx.coroutines.launch

class PerroActivity : AppCompatActivity(),  AdapterView.OnItemSelectedListener {
    val arrayBreeds =
        arrayOf("chihuahua", "collie", "german", "papillon", "pug", "retriever")

    lateinit var binding: ActivityPerroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        this.binding = ActivityPerroBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayBreeds)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        this.binding.spinnerPerros.adapter = spinnerAdapter

        this.binding.spinnerPerros.onItemSelectedListener = this
    }

    override fun onItemSelected(
        parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {

        val textViewSelected: TextView = (view as TextView);

        val selectedBreed = textViewSelected.text.toString()

        Log.d(Constantes.ETIQUETA_LOG, "Selecciondada la raza '$selectedBreed'")

        if (RedUtil.hayInternet(this)) {

            lifecycleScope.launch {

                Log.d(
                    Constantes.ETIQUETA_LOG,
                    "Se realiza la petición para obtener las URL de las imágenes"
                )

                val dogService: PerroService = PerroServiceHelper.getDogServiceInstance()

                try {

                    val dogImages: PerroRespuesta = dogService.getDogImages(selectedBreed)

                    Log.d(
                        Constantes.ETIQUETA_LOG,
                        "Las imágenes para la raza '$selectedBreed' son..."
                    )
                    showImages(dogImages.message);
                } catch (e: Exception) {

                    Log.e(Constantes.ETIQUETA_LOG, e.stackTraceToString())

                    Toast.makeText(
                        this@PerroActivity,
                        "No se pudieron recuperar las imágenes para la raza '$selectedBreed'",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {

            Toast.makeText(this, "No hay internet disponible", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

        // Nothing to do.
    }

    fun showImages(images: List<String>) {

        images.forEach {

            Log.d(Constantes.ETIQUETA_LOG, "$it")
        }
        //TODO mostrar las fotos en un carrusel
    }
}