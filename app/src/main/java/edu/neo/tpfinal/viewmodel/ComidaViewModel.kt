package edu.neo.tpfinal.viewmodel

import android.content.Context
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import edu.neo.tpfinal.api.implementation.ApiTragosImp
import edu.neo.tpfinal.dao.DbHelper
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Tragos
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class ComidaViewModel : ViewModel() {
    val base = DbHelper()

    fun guardarComida(comida: Comida, context: Context):Boolean{
        if (base.guardarComidaFireBase(comida,context)) return true
        return false
    }
    fun fechaActual():String{
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate.toString()
    }

    fun getTragos():Call<Tragos>{
        val api: ApiTragosImp = ApiTragosImp()
        return api.getTrago("random.php")
    }

    fun hambre (opcion: RadioButton):String{
        when(opcion.text.toString().lowercase()){
            "si" -> {return "si"}
            "no" -> {return "no"}
        }
        return ""
    }

}