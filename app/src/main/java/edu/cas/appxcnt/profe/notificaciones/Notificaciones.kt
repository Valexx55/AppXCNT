package edu.cas.appxcnt.profe.notificaciones

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.cas.appxcnt.profe.Constantes
import edu.cas.appxcnt.profe.R
import edu.cas.appxcnt.profe.perros.PerroActivity

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

        val servicioNotificaciones = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //tenemos que crear un canal o asociar nuestra notificación a un canal sólo si estoy
        //en api 8 o mayor. al estar con minversion en nuestro proyecto para la 7(API 24), debo comprobar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Log.d (Constantes.ETIQUETA_LOG, "ESTAMOS EN VERSIÓN 8 O SUPERIOR")
            var notificationChannel = crearCanalNotificacion(context)
            servicioNotificaciones.createNotificationChannel(notificationChannel!!)
        }



        //CREAR LA NOTIFICACIÓN

        var nb = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_casino_24)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.emoticono_risa))
            .setContentTitle("BUENOS DÍAS")
            .setSubText("aviso")
            .setContentText("Vamos a ver perros")
            .setAutoCancel(true)//desaparezca la notificación al tocarla
            .setDefaults(Notification.DEFAULT_ALL)

        //dejamos programada la acción que se lanzará al tocar la notificación
        val intent = Intent(context, PerroActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 200, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        nb.setContentIntent(pendingIntent)

        //si estoy en la versión 7, pongo el sonido porque no lo pilla del canal
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            nb.setSound(Uri.parse
                ("android.resource://" + context.packageName + "/" + R.raw.snd_noti))
        }

        //construyo la notificación
        val notificacion = nb.build()

        servicioNotificaciones.notify(500, notificacion)

    }
}