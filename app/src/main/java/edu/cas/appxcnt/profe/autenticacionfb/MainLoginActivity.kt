package edu.cas.appxcnt.profe.autenticacionfb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.R
import kotlin.jvm.java

class MainLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun nuevaCuenta(view: View) {
        val intentNueva = Intent(this, RegistroActivity::class.java)
        startActivity(intentNueva)
        finish()
    }
    fun acceder(view: View) {
        val intentAuth = Intent(this, LoginActivity::class.java)
        startActivity(intentAuth)
        finish()

    }
}