package edu.cas.appxcnt.profe.perros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import edu.cas.appxcnt.profe.databinding.PerroFragmentBinding

class PerroFragment : Fragment() {

    /**
     * En esta función si entendemos el código lo que estamos haciendo es inflar el XML
     * que representa el fragmento del perro y rellenarlo con los datos del perro actual
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*  val vistaPerrro =  inflater.inflate(
              R.layout.perro_fragment, container, false
          )*/

        val vistaPerro = PerroFragmentBinding.inflate(inflater, container, false)

            val urlFoto = arguments?.getString("URL_FOTO")
            val leyenda = arguments?.getString("LEYENDA")

            vistaPerro.leyenda.text = leyenda

            Glide.with(this).load(urlFoto?.toUri()).into(vistaPerro.fotoPerro)

        return vistaPerro.root
    }
}