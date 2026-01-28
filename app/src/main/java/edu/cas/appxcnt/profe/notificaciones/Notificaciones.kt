package edu.cas.appxcnt.profe.notificaciones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R

object Notificaciones {

    val NOTIFICATION_CHANNEL_ID = "CNTGX"

    val NOTIFICATION_CHANNEL_NAME = "FICHAJES"

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearCanalNotificacion (context: Context): NotificationChannel?
    {
        var  notificationChannel: NotificationChannel? = null

            notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)

        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        //vibración patron suena 500 ms, no vibra otros 500 ms
        notificationChannel.vibrationPattern = longArrayOf(
            500,
            500,
            500,
            500,
            500,
            500,
        )
        notificationChannel.lightColor = context.applicationContext.getColor(R.color.mirojo)
        //sonido de  la notificación si el api es inferior a la 8, hay que setear el sonido aparte
        //si es igual o superior, la notificación "hereda" el sonido del canal asociado
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        //Importante que estén activadas el sonido de las notificaciones en la aplicación porque si no los hará aunque le metan sonido
        notificationChannel.setSound(
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti),
            audioAttributes
        )

        notificationChannel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC



        return notificationChannel
    }

    fun lanzarNotificacion (context: Context)
    {

        //tenemos que crear un canal o asociar nuestra notificación a un canal sólo si estoy
        //en api 8 o mayor. al estar con minversion en nuestro proyecto para la 7(API 24), debo comprobar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Log.d (Constantes.ETIQUETA_LOG, "ESTAMOS EN VERSIÓN 8 O SUPERIOR")
            var notificationChannel = crearCanalNotificacion(context)
        }


        val servicioNotificaciones = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //lanzar la notficación
        //servicioNotificaciones.notify()
    }
}