package edu.cas.appxcnt.profe.realtimedbfb

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.databinding.ActivityClientesFirebaseBinding
import kotlin.toString


const val URL_REAL_TIME_DABASE = "https://appxcnt-default-rtdb.europe-west1.firebasedatabase.app/"
class ClientesFirebaseActivity : AppCompatActivity() {

    var listaClientes = ArrayList<Cliente>()

    lateinit var databaseReference: DatabaseReference
    lateinit var binding: ActivityClientesFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientesFirebaseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //inicializo la base de datos
        this.databaseReference = FirebaseDatabase.getInstance(URL_REAL_TIME_DABASE).reference
    }

    fun crearClienteFB(view: View) {
        //TODO: crear un registro en la base de datos remota de Firebase
        //1 con los datos del formulario, creo un cliente

        val cliente = Cliente(
            binding.edadCliente.text.toString().toLong(),
            binding.localidadCliente.text.toString(),
            binding.nombreCliente.text.toString(),
            binding.emailCliente.text.toString()
        )

        Log.d(Constantes.ETIQUETA_LOG, "CLiente a insertar $cliente")
        //generamos una clave para ese cliente
        val idCliente = this.databaseReference.push().key
        cliente.clave = idCliente!!

        Log.d(Constantes.ETIQUETA_LOG, "CLiente id =  $idCliente")
        //estoy insertando
        this.databaseReference.child("clientes").child(cliente.clave).setValue(cliente)
            .addOnCompleteListener { tarea ->
                Toast.makeText(this, "CLIENTE INSERTADO", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "ERROR INSERTANDO CLI", Toast.LENGTH_LONG).show()
                Log.e(Constantes.ETIQUETA_LOG, "Error al insertar ", it)
            }

    }

    fun mostrarClientesFB(view: View) {

        Log.d(Constantes.ETIQUETA_LOG, "Mostrar clientes FB")
        //this.databaseReference.child("clientes").child("clave").get()//con esto accedemos a un dato concreto por su clave
        this.databaseReference.child("clientes").get().addOnSuccessListener {
            datos ->
            var clave = datos.key
            Log.d(Constantes.ETIQUETA_LOG, "Clave $clave")
            var lista = datos.value as Map<String, Map<String, Any>>
            var entradas = lista.entries
            var ncliens = entradas.size

            Log.d(Constantes.ETIQUETA_LOG, "Hay $ncliens clientes")

            var cliente: Cliente
            //para cada cliente, obtenemos los datos
            entradas.forEach { (claveId, valor) ->
                Log.d(Constantes.ETIQUETA_LOG, "idCliente = $claveId")
                Log.d(Constantes.ETIQUETA_LOG, "nombre = ${valor.get("nombre")}")
                Log.d(Constantes.ETIQUETA_LOG, "email = ${valor.get("email")}")
                Log.d(Constantes.ETIQUETA_LOG, "localidad = ${valor.get("localidad")}")
                Log.d(Constantes.ETIQUETA_LOG, "edad = ${valor.get("edad")}")
                cliente = Cliente(
                    valor.get("edad") as Long,
                    valor.get("localidad").toString(),
                    valor.get("nombre").toString(),
                    valor.get("email").toString(),
                    claveId
                )

                listaClientes.add(cliente)
                if (listaClientes.size == ncliens) {
                    listaClientes.forEachIndexed { n, c -> Log.d(Constantes.ETIQUETA_LOG, "Cliente $n = $c.toString()")}
                }
            }
        }
    }


    fun borrarUltimoPorClave (view: View){

        if (listaClientes.size>0)
        {
            val claveUltimo = listaClientes.get(listaClientes.size-1).clave

            Log.d(Constantes.ETIQUETA_LOG, "Clave ultimo = $claveUltimo")

            claveUltimo.let {

                Log.d(Constantes.ETIQUETA_LOG, "A eliminar cliente con id clave $claveUltimo")
                val refCli =  FirebaseDatabase.getInstance(URL_REAL_TIME_DABASE).getReference("clientes/$claveUltimo")

                refCli.removeValue()
                    .addOnSuccessListener {
                        Log.d(Constantes.ETIQUETA_LOG, "Cliente eliminado")
                        listaClientes.removeAt(listaClientes.size-1)
                        Toast.makeText(this, "CLIENTE ELIMINADO", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { e ->
                        Log.e(Constantes.ETIQUETA_LOG, "Error: ${e.message}")
                    }

            }
        } else {
            Log.d(Constantes.ETIQUETA_LOG, "Sin clientes que borrar")
            Toast.makeText(this, "Sin clientes que borrar/n Clique mostrar primero", Toast.LENGTH_LONG).show()
        }



    }


}