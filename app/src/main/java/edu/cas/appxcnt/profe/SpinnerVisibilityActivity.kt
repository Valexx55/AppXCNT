package edu.cas.appxcnt.profe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import edu.cas.appxcnt.profe.databinding.ActivitySpinnerVisibilityBinding

class SpinnerVisibilityActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivitySpinnerVisibilityBinding

    val arrayVisibilidad = arrayOf("VISIBLE", "INVISIBLE", "GONE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySpinnerVisibilityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //INICIAMOS EL SPINNER - ADAPTER - "PROVEEDOR"
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayVisibilidad)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)//estilo cuando la lista esté desplegada
        //asociamos al spinner su layout
        binding.spinner.adapter = spinnerAdapter
        //programamos el listener al evento de selección
        binding.spinner.onItemSelectedListener = this //la propia clase implementa la interfaz


    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        //TODO ("Not yet implemented")
        //OJO : caso especial sin llegar a seleccionar una opción, el spinner salta (se invoca este método)


        val textViewSeleccionado = (view as TextView) //casting
        Log.d(Constantes.ETIQUETA_LOG, "onItemSelected spinner posición = $position Texto = ${textViewSeleccionado.text}")

        when (position)
        {
            0 -> binding.imagenMuestra.visibility = View.VISIBLE
            1 -> binding.imagenMuestra.visibility = View.INVISIBLE
            2 -> binding.imagenMuestra.visibility = View.GONE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //TODO("Not yet implemented")
        Log.d(Constantes.ETIQUETA_LOG, "onNothingSelected spinner")
    }
}