package edu.cas.appxcnt.profe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //INFLAR: de XML a Objeto VISUAL
        menuInflater.inflate(R.menu.menu_imc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Esta función recibe una llamada cuando se toca el menú del app bar
     *
     * @param item la opción seleccionada
     * @return true si gestionas la opción
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(Constantes.ETIQUETA_LOG, "Opción ${item.title} tocada")
        when (item.itemId) {
            R.id.opcionSalir -> {
                Log.d(Constantes.ETIQUETA_LOG, "Opción SALIR")
                //this.finish()//cierro la ventana actual y voy a la anterior visible
                //this.finishAffinity()//salgo de la app
                //Preguntar al usuario si quiere salir de la app o no

                val dialogo = AlertDialog.Builder(this)
                    .setTitle("AVISO SALIR")
                    .setMessage("¿Desea salir?")
                    .setIcon(R.drawable.ic_salir)
                    .setPositiveButton("Sí"){dialog, which ->
                        Log.d(Constantes.ETIQUETA_LOG, "Confirma salír Sí")
                        this.finishAffinity()
                    }
                    .setNeutralButton("Cancelar"){dialog, which ->
                        Log.d(Constantes.ETIQUETA_LOG, "Confirma cancelar")
                        dialog.cancel()
                    }
                    .setNegativeButton("No"){dialog, which ->
                        Log.d(Constantes.ETIQUETA_LOG, "Confirma No salir")
                        dialog.dismiss()
                    }

                    dialogo.show() //mostamos el diálogo

            }

            R.id.opcionLimpiar -> {
                Log.d(Constantes.ETIQUETA_LOG, "Opción SALIR")
                findViewById<EditText>(R.id.editTextPeso).setText("")
                findViewById<EditText>(R.id.editTextAltura).setText("")
            }
        }
        return true
    }

    fun calcularImc(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "El usuario ha tocado el botón de calcular")
        //TODO validar
        // OBTENERPESO
        val peso = obtenerPeso()
        // OBTENER ALTURA
        val altura = obtenerAltura()
        // HACER EL CÁLCULO
        val resultadoImcNum = calculoIMCNumerico(peso, altura)
        // MOSTRAR
        mostrarResultadoImcNum(resultadoImcNum)
        //TRANSITAMOS A RESULTADO ACTIVITY
        //INTENE EXPLÍCITO
        val intent: Intent = Intent(this, ResultadoImcActivity::class.java)
        intent.putExtra("resultado_imc", resultadoImcNum)
        startActivity(intent)
    }

    fun obtenerPeso(): Float {
        var peso: Float = 0f

        peso = this.findViewById<EditText>(R.id.editTextPeso).text.toString().toFloat()

        return peso

    }

    fun obtenerAltura(): Float {
        var altura: Float = 0f

        altura = this.findViewById<EditText>(R.id.editTextAltura).text.toString().toFloat()

        return altura
    }

    fun calculoIMCNumerico(peso: Float, altura: Float): Float {
        var resultadoImc: Float = 0f

        resultadoImc = peso / (altura * altura)

        return resultadoImc

    }

    fun mostrarResultadoImcNum(imcNum: Float) {
        val notiToast = Toast.makeText(this, "Su IMC es $imcNum", Toast.LENGTH_LONG)
        notiToast.show()
    }
    /*
        fun limpiar(item: MenuItem) {
            Log.d(Constantes.ETIQUETA_LOG, "Opción ${item.title} tocada 2")
        }
    */
}