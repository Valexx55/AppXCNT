package edu.cas.appxcnt.profe

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.cas.appxcnt.profe.databinding.ActivityMainBinding
import edu.cas.appxcnt.profe.databinding.ActivityMainMenuBinding
import edu.cas.appxcnt.profe.perros.PerroActivity
import edu.cas.appxcnt.profe.productos.ListaProductosActivity

class MainMenuActivity : AppCompatActivity() {

    var menuVisble: Boolean = false //para controlar el estado de visibilidad de menú


    lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainMenuBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(this.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)//dibujamos la fechita de ir hacia atrás
        this.supportActionBar?.setHomeAsUpIndicator(R.drawable.outline_menu_24) //ponemos la hamburguesa

        this.binding.navigationView.setNavigationItemSelectedListener { item ->

            val intentDestino = when (item.order) {
                1 -> Intent(this, SumaActivity::class.java)
                2 -> Intent(this, ImcActivity::class.java)
                3 -> Intent(this, AdivinaActivity::class.java)
                4 -> Intent(this, SpinnerVisibilityActivity::class.java)
                5 -> Intent(this, BusquedaActivity::class.java)
                6 -> Intent(this, WebViewActivity::class.java)
                7 -> Intent(this, InflarActivity::class.java)
                8 -> Intent(this, ListaProductosActivity::class.java)
                9 -> Intent(this, PerroActivity::class.java)
                else /*0*/ -> Intent(this, MainActivity::class.java)
            }
            startActivity(intentDestino)
            binding.drawer.closeDrawers()
            this.menuVisble = false
            //NOTA: IMPORTANTE https://kotlinlang.org/docs/returns.html
            //No puedo poner return true directamente porque kotlin interpreta ese return de la función contenedora (onCreate)
            //-que ademas es Unit, no devuelve nada- y no de la función del listener . Esto se llama non-local return
            //podría valer esto return@setNavigationItemSelectedListener true especificamos que este return es de la lambda
            //o siemplemnte, el valor
            true
        }


        //mostrarAppsInstaladas()
    }




    /**
     * Toco el menú hamburguesa
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d(Constantes.ETIQUETA_LOG, "Botón hamburguesa tocado")
                if (this.menuVisble) {
                    this.binding.drawer.closeDrawers()
                    this.menuVisble = false
                } else {
                    this.binding.drawer.openDrawer(GravityCompat.START) //abrimos el meníu de iz a der
                    this.menuVisble = true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun mostrarAppsInstaladas() {
        var apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        Log.d(Constantes.ETIQUETA_LOG, "TIENES ${apps.size} aplicaciones instaladas")
        var listaOrdenadaApps = apps.sortedBy { it.packageName }
        listaOrdenadaApps.forEach {
            Log.d(
                Constantes.ETIQUETA_LOG,
                "Paquete = ${it.packageName} Nombre = ${packageManager.getApplicationLabel(it)}"
            )
        }
    }


    //        val intentWebCnt = Intent(Intent.ACTION_VIEW, URL_CNT.toUri())//"QUIERO VER; UNA WEB"
//        if (intentWebCnt.resolveActivity(packageManager)!=null)
//        {
//            Log.d(Constantes.ETIQUETA_LOG, "Este dispositivo sí cuenta con al menos una app que puede ver sitios web")
//            //CHOOSER
//            //startActivity(intentBusqueda)
//            startActivity(Intent.createChooser(intentWebCnt, "Elige app"))
//        } else {
//            //Emoticono windows + . punto
//            Toast.makeText(this, "No se ha detectado un app para ver la página de la Xunta 😥  ", Toast.LENGTH_LONG ).show()
//        }

    /*val intent = Intent(this, ListaProductosActivity::class.java)
    //val intent = Intent(this, InflarActivity::class.java)
    //val intent = Intent(this, PerroActivity::class.java)
    startActivity(intent)*/
    /**
     * TODO probar un intent para compartir mensajes por whatssapp (u otras apps quitando el package)
     *   Log.d(this.getClass().getCanonicalName(), "El usuario le ha dado compartir");
     *                 i = new Intent(Intent.ACTION_SEND);
     *                 i.putExtra(Intent.EXTRA_TEXT, MENSAJE_COMPARTIR);
     *                 i.setType("text/plain");
     *                // i.setPackage("com.whatsapp");
     *                 startActivity(i);
     */

}