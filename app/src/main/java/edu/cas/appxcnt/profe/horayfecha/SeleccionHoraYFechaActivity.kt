package edu.cas.appxcnt.profe.horayfecha

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivitySeleccionHoraYfechaBinding

class SeleccionHoraYFechaActivity : AppCompatActivity(), View.OnFocusChangeListener {

    lateinit var binding: ActivitySeleccionHoraYfechaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionHoraYfechaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.cajaHora.onFocusChangeListener = this
        binding.cajaFecha.onFocusChangeListener = this
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus)
        {
            if (v!!.id==R.id.cajaFecha)
            {
                Log.d(Constantes.ETIQUETA_LOG, "Ha ganado el foco la hora")
                var seleccionFecha = SeleccionFecha()
                seleccionFecha.show(supportFragmentManager, "CALENDARIO")

            } else {

                Log.d(Constantes.ETIQUETA_LOG, "Ha ganado el foco la fecha")
                var seleccionHora = SeleccionHora()
                seleccionHora.show(supportFragmentManager, "RELOJ")
            }
        }
    }


    fun actualizarHoraSeleccionada (hora:String)
    {
        this.binding.cajaHora.setText(hora)

    }

    fun actualizarFechaSeleccionada (fecha:String)
    {
        this.binding.cajaFecha.setText(fecha)

    }
}