package edu.cas.appxcnt.profe.autenticacionfb

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R

class LoginActivity : AppCompatActivity() {


    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.firebaseAuth = FirebaseAuth.getInstance()
    }

    fun login(view: View) {

        Log.d(Constantes.ETIQUETA_LOG, "En login ")
        val correo = findViewById<EditText>(R.id.editTextTextEmailAddressAuth).text.toString()
        val passWord = findViewById<EditText>(R.id.editTextTextPasswordAuth).text.toString()

        firebaseAuth.signInWithEmailAndPassword(correo, passWord).addOnCompleteListener {
            tarea ->
            if (tarea.isSuccessful) {
                Toast.makeText(this, "LOGIN CORRECTO", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "ERROR AL hacer LOGIN", Toast.LENGTH_LONG).show()
            }
        }
    }
}