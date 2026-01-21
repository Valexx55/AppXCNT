package edu.cas.appxcnt.profe.productos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.util.RedUtil
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ListaProductosActivity : AppCompatActivity() {

    lateinit var listaProductos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (RedUtil.hayInternet(this)) {
            Log.d(Constantes.ETIQUETA_LOG, "Sí hay conexión a internet")

            //ESTA SECCIÓN REPRESENTA EL ÁREA DE LA CORRUTINA
            //DENTRO DE ELLA, PUEDE HABER LLAMADAS BLOQUEANTES
            //COMO POR EJEMPLO, SERÁ LA CONEXIÓN A INTERNET CON RETROFIT
            //TODO: explicar el sentido de lifecycleScope
            lifecycleScope.launch {

                try{
                    Log.d(Constantes.ETIQUETA_LOG, "Lanzando petición 1")
                    val productoService = ProductosRetrofitHelper.getProductoServiceInstance()
                    listaProductos =  productoService.obtenerProductos()
                    Log.d(Constantes.ETIQUETA_LOG, "Mostrando productos ...")
                    listaProductos.forEach { producto ->
                        Log.d(Constantes.ETIQUETA_LOG, "${producto.toString()}")
                    }
                    mostrarListaProductos ()
                }catch (excepcion: Exception)
                {
                    Log.e(Constantes.ETIQUETA_LOG, "Error al conectar con servidor de productos", excepcion)
                    val toast = Toast.makeText(this@ListaProductosActivity, "Error al obtener el listado", Toast.LENGTH_LONG)
                    //this@ListaProductosActivity es el contexto, el this de la clase contenedora/ la actividad
                    toast.show()
                    this.cancel()//this es la corrutina / proceso independiente


                }

            }
            //mostrarListaProductos () //si llamo fuera, podría ocurrir que la función suspend obtenerProductos dentro
            //de la corrutina no ha terminado
            Log.d(Constantes.ETIQUETA_LOG, "Fuera de la corrutina")//esto se ejecuta generalmente antes que el foreach



        } else {
            val toast = Toast.makeText(this, "Sin  conexión a internet", Toast.LENGTH_LONG)
            toast.show()

        }


    }

    fun mostrarListaProductos () {

    }

    //
}