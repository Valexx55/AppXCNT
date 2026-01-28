package edu.cas.appxcnt.profe.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cas.appxcnt.profe.databinding.FragmentoTabBinding

class FragmentoTabs: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentoTab = FragmentoTabBinding.inflate(inflater, container, false)

        var num = arguments?.getInt("VALOR") ?: 0
        fragmentoTab.numvista.text = "VISTA $num"

        return  fragmentoTab.root;
    }
}