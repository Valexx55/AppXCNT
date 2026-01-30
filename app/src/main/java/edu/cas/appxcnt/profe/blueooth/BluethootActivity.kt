package edu.cas.appxcnt.profe.blueooth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R

class BluethootActivity : AppCompatActivity() {
    private val pickFileLauncher =
        //FORMA ANTIGUA Intent(Intent.ACTION_OPEN_DOCUMENT)
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                Log.d(Constantes.ETIQUETA_LOG, "URI = $uri")
                sendFileViaBluetooth(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluethoot)

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            pickFile()
        }
    }

    private fun pickFile() {
        pickFileLauncher.launch(arrayOf("*/*"))//tipo mime de archivo "image/*"
    }

    private fun sendFileViaBluetooth(fileUri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_STREAM, fileUri)
            setPackage("com.android.bluetooth")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(
            Intent.createChooser(intent, "Enviar archivo por Bluetooth")
        )
    }
}