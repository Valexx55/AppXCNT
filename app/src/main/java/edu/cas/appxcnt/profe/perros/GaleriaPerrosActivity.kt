package edu.cas.appxcnt.profe.perros

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

class GaleriaPerrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_galeria_perros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val razaPerro = intent!!.getStringExtra("RAZA") ?: ""
        consultarDogApi(razaPerro)
    }

    fun consultarDogApi(razaPerro:String)
    {
        if (RedUtil.hayInternet(this)) {

            lifecycleScope.launch {

                Log.d(
                    Constantes.ETIQUETA_LOG,
                    "Se realiza la petición para obtener las URL de las imágenes"
                )

                val dogService: PerroService = PerroServiceHelper.getDogServiceInstance()

                try {

                    val dogImages: PerroRespuesta = dogService.getDogImages(razaPerro)

                    Log.d(
                        Constantes.ETIQUETA_LOG,
                        "Las imágenes para la raza '$razaPerro' son..."
                    )
                    showImages(dogImages.message);
                } catch (e: Exception) {

                    Log.e(Constantes.ETIQUETA_LOG, e.stackTraceToString())

                    Toast.makeText(
                        this@GaleriaPerrosActivity,
                        "No se pudieron recuperar las imágenes para la raza '$razaPerro'",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {

            Toast.makeText(this, "No hay internet disponible", Toast.LENGTH_LONG).show()
        }
    }


    fun showImages(images: List<String>) {

        images.forEach {

            Log.d(Constantes.ETIQUETA_LOG, "$it")
        }
        //TODO mostrar las fotos en un carrusel
    }
}