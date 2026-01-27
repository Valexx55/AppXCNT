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
}