package edu.cas.appxcnt.profe.perros

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterPerros (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    lateinit var listaPerros: List<String>

    override fun createFragment(position: Int): Fragment {
        var perroFragment = PerroFragment()

            //pseudocódigo de whatsaapp
            /*when (position)
            {
                0 -> return fragmentDeChats
                1 -> return fragmentEstados
                    ...
            }*/
            val urlPerro = listaPerros.get(position)
            var textoLeyenda = "${position+1} de ${listaPerros.size} "

            var bundle = Bundle()//"saco" memoria temporal
            bundle.putString("URL_FOTO", urlPerro)
            bundle.putString("LEYENDA", textoLeyenda)

            perroFragment.arguments = bundle//escribo argumentos, que se acceden en onCreateView del fragment

        return perroFragment
    }

    override fun getItemCount(): Int {
       // TODO("Not yet implemented")
        return listaPerros.size
    }
}