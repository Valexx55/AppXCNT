package edu.cas.appxcnt.profe.perros

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityGaleriaPerrosBinding
import edu.cas.appxcnt.profe.util.RedUtil
import kotlinx.coroutines.launch
import kotlin.math.abs

class GaleriaPerrosActivity : AppCompatActivity() {

    lateinit var binding: ActivityGaleriaPerrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityGaleriaPerrosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(this.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val razaPerro = intent!!.getStringExtra("RAZA") ?: ""
        consultarDogApi(razaPerro)
        binding.razaPerro.text = razaPerro
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
        var adapterPerros = AdapterPerros(this)
        adapterPerros.listaPerros = images

        //asocio al viewpager (como si fuera un recycler o estructura) su adapter
        this.binding.viewpager.adapter = adapterPerros

        /**
         * animamos el deslizamiento entre fragments
         *
         */

       /* this.binding.viewpager.setPageTransformer { page, position ->
            when {
                position < -1 -> page.alpha = 0f
                position <= 0 -> {
                    page.alpha = 1f
                    page.translationX = 0f
                    page.scaleX = 1f
                    page.scaleY = 1f
                }

                position <= 1 -> {
                    page.alpha = 1 - position
                    page.translationX = page.width * -position
                    val scaleFactor = 0.75f + (1 - position) * 0.25f
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                }

                else -> page.alpha = 0f
            }

        }*/

        val zoomOutTransformer = ViewPager2.PageTransformer { page, position ->
            val scale = 1 - abs(position) * 0.2f
            page.scaleX = scale
            page.scaleY = scale
            page.alpha = 0.5f + (1 - abs(position)) * 0.5f
        }

        val fadeTransformer = ViewPager2.PageTransformer { page, position ->
            page.alpha = 1 - abs(position)
        }

        //binding.viewpager.setPageTransformer(fadeTransformer)
        binding.viewpager.setPageTransformer(zoomOutTransformer)
    }
}