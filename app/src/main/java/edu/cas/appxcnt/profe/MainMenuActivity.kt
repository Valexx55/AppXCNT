package edu.cas.appxcnt.profe

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.cas.appxcnt.profe.databinding.ActivityMainBinding
import edu.cas.appxcnt.profe.databinding.ActivityMainMenuBinding
import edu.cas.appxcnt.profe.perros.PerroActivity
import edu.cas.appxcnt.profe.productos.ListaProductosActivity

class MainMenuActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout //contendor del menú lateral
    lateinit var navigationView: NavigationView//las opciones de ese menú

    var menuVisble: Boolean=false //para controlar el estado de visibilidad de menú


    lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainMenuBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(this.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //TODO completar el funcionamiento del menú lateral

        //val intent = Intent(this, ImcActivity::class.java)
        //val intent = Intent(this, AdivinaActivity::class.java)
        //val intent = Intent(this, SpinnerVisibilityActivity::class.java)
        //intent explícito
        //val intent = Intent(this, BusquedaActivity::class.java)
        //val intent = Intent(this, WebViewActivity::class.java)
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

        val intent = Intent(this, ListaProductosActivity::class.java)
        //val intent = Intent(this, InflarActivity::class.java)
        //val intent = Intent(this, PerroActivity::class.java)
        startActivity(intent)
        /**
         * TODO probar un intent para compartir mensajes por whatssapp (u otras apps quitando el package)
         *   Log.d(this.getClass().getCanonicalName(), "El usuario le ha dado compartir");
         *                 i = new Intent(Intent.ACTION_SEND);
         *                 i.putExtra(Intent.EXTRA_TEXT, MENSAJE_COMPARTIR);
         *                 i.setType("text/plain");
         *                // i.setPackage("com.whatsapp");
         *                 startActivity(i);
         */

        //mostrarAppsInstaladas()
    }

    fun mostrarAppsInstaladas ()
    {
        var apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        Log.d(Constantes.ETIQUETA_LOG, "TIENES ${apps.size} aplicaciones instaladas")
        var listaOrdenadaApps = apps.sortedBy { it.packageName }
        listaOrdenadaApps.forEach {
            Log.d(Constantes.ETIQUETA_LOG, "Paquete = ${it.packageName} Nombre = ${packageManager.getApplicationLabel(it)}")
        }
    }
}