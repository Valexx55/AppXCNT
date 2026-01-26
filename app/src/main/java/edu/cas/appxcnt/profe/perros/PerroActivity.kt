package edu.cas.appxcnt.profe.perros

import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityPerroBinding
import edu.cas.appxcnt.profe.util.RedUtil
import kotlinx.coroutines.launch

class PerroActivity : AppCompatActivity(),  AdapterView.OnItemSelectedListener {
    val arrayBreeds =
        arrayOf("chihuahua", "collie", "german", "papillon", "pug", "retriever")

    var razaPerroSeleccionada: String = ""
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

        razaPerroSeleccionada = textViewSelected.text.toString()

        Log.d(Constantes.ETIQUETA_LOG, "Selecciondada la raza '$razaPerroSeleccionada'")

        mostrarSnack()


    }

    fun mostrarSnack()
    {
       val snackbar = Snackbar.make(binding.spinnerPerros, "RAZA SELECCIONADA $razaPerroSeleccionada", Snackbar.LENGTH_LONG)
        //snackbar.setTextColor(getResources().getColor(R.color.mirojo, theme))
        /**
         * snackbar.setTextColor(
         *     ContextCompat.getColor(this, R.color.mirojo)
         * );
         *
         */
        snackbar.setActionTextColor(getResources().getColor(R.color.mirojo, theme))
        snackbar.setAction("OK"){snackbar-> Log.d(Constantes.ETIQUETA_LOG, "Ok tocado") }
        snackbar.show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

        // Nothing to do.
    }



    fun buscarFotos(view: View) {

        Log.d(Constantes.ETIQUETA_LOG, "A buscar fotos de $razaPerroSeleccionada")
        val intentGaleria = Intent(this, GaleriaPerrosActivity::class.java)
        intentGaleria.putExtra("RAZA", razaPerroSeleccionada)
        startActivity(intentGaleria)
    }
}