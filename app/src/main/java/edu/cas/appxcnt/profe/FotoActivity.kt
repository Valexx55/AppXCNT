package edu.cas.appxcnt.profe

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class FotoActivity : AppCompatActivity() {


    lateinit var uriFotoPrivada:Uri //LA RUTA FÍSICA /PRIVADA DONDE SE GUARDA LA FOTO

    lateinit var uriFotoPublica:Uri //LA RUTA QUE LE DAMOS A LA APP DE LA CÁMARA (Y QUE ESTÁ ASOCIADA A MI PRIVADA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_foto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun tomarFoto(view: View) {
        pedirPermisosCamara()
    }

    fun pedirPermisosCamara ()
    {
        requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 500)
    }


    private fun crearFicheroDestino():Uri? {
        var rutaUriFoto:Uri? = null
        val fechaActual = Date()
        val momentoActual = SimpleDateFormat("yyyyMMdd_HHmmss").format(fechaActual)
        val nombreFichero = "FOTO_CNTX_$momentoActual.jpg"
        //var rutaFoto =  "${Environment.getExternalStorageDirectory()?.path}/$nombreFichero" //ruta pública NO SE PUEDE - Security Exception
        //var rutaFoto =  "${Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOWNLOADS)?.path}/$nombreFichero" //ruta pública de DESCARGAS NO SE PUEDE - Security Exception /storage/emulated/0/Download/FOTO_ADF_20250923_10344 (EXPLORADOR) /storage/sdcard0/Download
        //var rutaFoto =  "${Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DCIM)?.path}/$nombreFichero" //ruta pública de DESCARGAS NO SE PUEDE - Security Exception /storage/emulated/0/DCIM/FOTO_ADF_20250923_10344 (EXPLORADOR) /storage/sdcard0/DCIM
        //var rutaFoto =  "${getExternalFilesDir(null)?.path}/$nombreFichero" //ruta pública/privada /storage/emulated/0/Android/data/edu.adf.profe/files/FOTO_ADF_20250922_124524 (EXPLORADOR) /storage/sdcard0/Android/data/edu.adf.profe/files/FOTO_ADF_20250922_124524
        var rutaFoto = "${filesDir.path}/$nombreFichero" //ruta privada ruta completa fichero =  /data/user/0/edu.adf.profe/files/FOTO_ADF_20250922_122916 (EXPLORADOR) /data/data/edu.adf.profe/files/FOTO_ADF_20250922_122916
        //Log.d(Constantes.ETIQUETA_LOG, "ruta privada completa fichero =  $rutaFoto ")

        val ficheroFoto = File(rutaFoto)
        try{
            ficheroFoto.createNewFile()//este métod tira una excepción, pero para KOTLIN todas las excepciones son de tipo RUNTIME o UnCHECKED - NO ME OBLIGA A GESTIONARLAS CON TRY/CATCH -
            uriFotoPrivada = ficheroFoto.toUri()
            Log.d(Constantes.ETIQUETA_LOG, "Fichero destino creado OK $uriFotoPrivada")
            uriFotoPublica = FileProvider.getUriForFile(this, "edu.cas.appxcnt.profe", ficheroFoto)
            Log.d(Constantes.ETIQUETA_LOG, "ruta pública $uriFotoPublica")
        } catch (e:Exception)
        {
            Log.e(Constantes.ETIQUETA_LOG, "Errro al crear el fichero destino de la foto", e)
        }

        return uriFotoPublica
    }

    val launcherIntentFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
            resultado ->
        if (resultado.resultCode== RESULT_OK)
        {
            Log.d(Constantes.ETIQUETA_LOG, "La foto fue bien")
            findViewById<ImageView>(R.id.fotoTomada).setImageURI(this.uriFotoPublica)
            //actualizarGaleria()
        } else {
            Log.d(Constantes.ETIQUETA_LOG, "La foto fue mal")
        }

    }

    fun lanzarCamara()
    {
        //TIRAMOS UN INTENT Para la cámara
        val uri = crearFicheroDestino() //al lanzar la cámara necesito darle la ruta de una foto donde vaya a escribirse

        uri?.let { //si uri es != null
            this.uriFotoPublica = it
            Log.d(Constantes.ETIQUETA_LOG, "URI FOTO = ${this.uriFotoPublica}")
            val intentFoto = Intent()
            intentFoto.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
            intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, this.uriFotoPublica)
            launcherIntentFoto.launch(intentFoto)
        } ?: run {
            Toast.makeText(this, "NO FUE POSIBLE CREAR EL FICHERO DESTINO", Toast.LENGTH_LONG).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Log.d(Constantes.ETIQUETA_LOG, "PERMISO CÁMARA CONCEDIDO")
            lanzarCamara()
        } else {
            Log.d(Constantes.ETIQUETA_LOG, "PERMISO CÁMARA NO CONCEDIDO")
            Toast.makeText(this, "SIN PERMISOS PARA HACER FOTOS", Toast.LENGTH_LONG).show()
        }
    }
}