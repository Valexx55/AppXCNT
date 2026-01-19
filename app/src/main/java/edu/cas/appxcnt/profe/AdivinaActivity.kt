package edu.cas.appxcnt.profe

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import edu.cas.appxcnt.profe.databinding.ActivityAdivinaBinding
import kotlin.random.Random

class AdivinaActivity : AppCompatActivity() {

    //variables miembro / propiedades / campos / atributos

    var numeroSecreto: Int = 0
    var numeroVidas: Int = Constantes.NUM_VIDAS_JUEGO_ADIVINA

    var haGanado: Boolean = false //los tipos básicos hay que inicializarlos al declarlos
    var haPerdido: Boolean =false // en una clase

    lateinit var cajaNumeroUsuario: EditText // tipos "complejos"/clase puedo usar el lateinit var

    lateinit var cajaTextoVidas: TextView

    lateinit var binding: ActivityAdivinaBinding //demo de uso de ViewBinding

    lateinit var menuBarra : Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdivinaBinding.inflate(layoutInflater)//demo de uso de ViewBinding
        
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //si bundle es null, es que está vacío --> la primera que se ejecuta
        //si bundle != null, es que ya estaba en la partida

        cajaTextoVidas =  findViewById<TextView>(R.id.numVidas)
        cajaNumeroUsuario = findViewById<EditText>(R.id.numeroUsuario)

        if (savedInstanceState!=null)
        {
            //obtenemos los valores
        }

        //?: operador Elvis
        //?. safe null access
        //!! not null operator
        this.numeroSecreto =  savedInstanceState?.getInt("num_secreto") ?: generarNumeroSecreto()
        this.numeroVidas = savedInstanceState?.getInt("num_vidas") ?: Constantes.NUM_VIDAS_JUEGO_ADIVINA

        //cajaTextoVidas.text = "$numeroVidas VIDAS"
        binding.numVidas.text = "$numeroVidas VIDAS" //demo de uso de ViewBinding - Vinculación de Vistas
        Log.d(Constantes.ETIQUETA_LOG, "Num secreto = $numeroSecreto")
    }


    override fun onStart() {
        super.onStart()
        Log.d(Constantes.ETIQUETA_LOG, "En onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constantes.ETIQUETA_LOG, "En onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constantes.ETIQUETA_LOG, "En onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constantes.ETIQUETA_LOG, "En onStop()")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constantes.ETIQUETA_LOG, "En onDestroy()")
    }

    fun generarNumeroSecreto(): Int
    {
        var numeroSecretoLocal: Int = 0

            numeroSecretoLocal = Random.nextInt(1,100)

        return numeroSecretoLocal
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuBarra = menu!! //me guardo una referencia al menú, para luego poder modificarlo sobre la marcha
        return super.onCreateOptionsMenu(menu)
    }

    fun intentoAdivina(view: View) {
        Log.d (Constantes.ETIQUETA_LOG, "El usuario ha dado a probar")

        //val cajaNumUsuario = findViewById<EditText>(R.id.numeroUsuario)
        val numeroUsuario = this.cajaNumeroUsuario.text.toString().toInt()
        when {
            numeroUsuario > numeroSecreto -> {
                informarMenor()
                //this.cajaNumeroUsuario.setText("")
            }
            numeroUsuario < numeroSecreto -> {
                informarMayor ()
                //this.cajaNumeroUsuario.setText("")
            }
            else -> {
                //findViewById<ImageButton>(R.id.botonReinicio).visibility = View.VISIBLE
                 ganador()
               // pintarReinicioEnMenu()
                //
                haGanado = true
                actulizarImangen(R.drawable.imagen_victoria)
                pintarReinicioEnMenu ()
            }
        }
        if (!haGanado) //haGando == false
        {
            Log.d (Constantes.ETIQUETA_LOG, "El usuario no ha acertado")
            this.numeroVidas = this.numeroVidas-1
            cajaTextoVidas.text = "$numeroVidas VIDAS"
            if (this.numeroVidas==0)
            {
                //findViewById<ImageButton>(R.id.botonReinicio).visibility = View.VISIBLE
                informarGameOver()
                actulizarImangen(R.drawable.imagen_derrota)
                pintarReinicioEnMenu ()
                 //pintarReinicioEnMenu()
                //findViewById<Button>(R.id.botonReinicio).visibility = View.VISIBLE
            }
        }

    }

    fun reiniciarPartida ()
    {
        finish()
        startActivity(intent)

        //recreate() //Opción válida pero llama a saveInstanceState
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            1 -> {
                reiniciarPartida ()
                this.menuBarra.clear()//borramos el menú
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun pintarReinicioEnMenu ()
    {
        this.menuBarra.add(Menu.NONE, 1, 1, "Reiniciar")
            .setIcon(R.drawable.outline_autorenew_24)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
    }

    fun actulizarImangen (refImagen: Int)
    {   //Gracias a la librería de glide puedo incrustar un gif en un image view
        Glide.with(this)
            .asGif()
            .load(refImagen)
            .into(binding.imagenAdivina)
    }


    fun informarGameOver()
    {
        this.haPerdido = true
        val cajaMensajeFinal =  findViewById<TextView>(R.id.textoFinal)
        cajaMensajeFinal.text = "HAS PERDIDO, EL NÚMERO BUSCADO ERA $numeroSecreto"
        cajaMensajeFinal.visibility = View.VISIBLE
        findViewById<Button>(R.id.botonJugar).isEnabled = false
        //actualizarImagen(R.drawable.imagen_derrota)
    }

    fun ganador ()
    {
        val cajaMensajeFinal =  findViewById<TextView>(R.id.textoFinal)
        cajaMensajeFinal.text = "HAS ACERTADO, ENHORABUENA"
        cajaMensajeFinal.visibility = View.VISIBLE
        findViewById<Button>(R.id.botonJugar).isEnabled = false
        //actualizarImagen(R.drawable.imagen_victoria)
    }


    fun informarMenor ()
    {
        Toast.makeText(this, "El número buscado es menor", Toast.LENGTH_LONG).show()
    }

    fun informarMayor ()
    {
        Toast.makeText(this, "El número buscado es mayor", Toast.LENGTH_LONG).show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(Constantes.ETIQUETA_LOG, "Actividad destruyéndose, guardando cosas ...")
        outState.putInt("num_secreto", this.numeroSecreto)
        outState.putInt("num_vidas", this.numeroVidas)
    }
}