package edu.cas.appxcnt.profe.tabs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityTabsBinding

class TabsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTabsBinding
    lateinit var adapterTabs: AdapterTabs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //NOTA: Los fragments deslizan vertical u horizontalmente dentro del viewpager según el atributo orientation del propio view Pager  (XML)
        this.adapterTabs = AdapterTabs(this)
        this.binding.vpt.adapter = adapterTabs
        //Asociamos el TabLayout que son las pestañas al ViewPager mediante el mediador

        TabLayoutMediator(this.binding.tablayout, this.binding.vpt){
                tl, n -> tl.text = "VISTA ${n+1}"

        }.attach()

    }
}