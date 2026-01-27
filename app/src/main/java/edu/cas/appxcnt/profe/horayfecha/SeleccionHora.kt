package edu.cas.appxcnt.profe.horayfecha

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class SeleccionHora: DialogFragment(), TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var miReloj: Dialog

        var hora:Int
        var minutos:Int
        var calendario: Calendar = Calendar.getInstance() //me da la fecha/hora actuales del Sistema

        hora = calendario.get(Calendar.HOUR_OF_DAY)
        minutos = calendario.get(Calendar.MINUTE)
        miReloj = TimePickerDialog(requireActivity(), this, hora, minutos, true)

        return miReloj
    }




    override fun onTimeSet(
        view: TimePicker?,
        horaSeleccionada: Int,
        minutosSeleccionados: Int
    ) {
        val horaFinal:String
        val hora:String
        val minuto:String

        hora = if (horaSeleccionada<10) "0$horaSeleccionada" else horaSeleccionada.toString()
        minuto = if (minutosSeleccionados<10) "0$minutosSeleccionados" else minutosSeleccionados.toString()
        horaFinal = "$hora:$minuto" //FORMATO HH:MM

        //activity es la actividad padre a la que pertecene este fragemento,
        //que es donde tiene que verse la hora seleccionada
        val actividadPadre =  activity as SeleccionHoraYFechaActivity
        actividadPadre.actualizarHoraSeleccionada((horaFinal))
    }
}