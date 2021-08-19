package edu.neo.tpfinal.viewmodel

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import edu.neo.tpfinal.R
import edu.neo.tpfinal.dao.DbHelper
import edu.neo.tpfinal.model.Usuario
import androidx.fragment.app.Fragment

class UsuarioViewModel: ViewModel() {
    val base = DbHelper()

    fun guardarUsuario(usuario:Usuario,context: Context):Boolean{
        if (base.guardarUsuarioFireBase(usuario,context)) return true
        return false
    }
    fun verificarUsuarioYPass(user:String,pass:String,context: Context):Boolean{
        base.autentificarUsuario(user,pass,context)
        if (base.usuarioEncontrado()){
            base.reiniciarDevolver()
            return true
        }
        Toast.makeText(context,"Usuario o contraseña invalidos, vuelva a intentar.",Toast.LENGTH_SHORT).show()
        return false
    }
    fun todosLosUsuarios(context: Context){
        base.todosUsuarios(context)
    }
    fun usuarioExistente(user: String){
        base.verificarUsuario(user)
    }
    fun dniExistente(dni:String){
        base.verificarDocumento(dni)
    }
    fun SeleccionTreatment(treatmentB:RadioButton):String{
        when(treatmentB.getId()){
            R.id.rb_anorexia -> if (treatmentB.isChecked){
                return "Anorexia"
            }
            R.id.rb_bulimia -> if (treatmentB.isChecked){
                return "Bulimia"
            }
            R.id.rb_obesidad  -> if (treatmentB.isChecked){
                return "Obesidad"
            }
        }
        return ""
    }
    fun SeleccionGender(genderB:RadioButton):String {
        when (genderB.getId()) {
            R.id.r_masculino -> if (genderB.isChecked){
                return "Masculino"
            }
            R.id.r_femenino -> if (genderB.isChecked){
                return "Femenino"
            }
        }
        return ""
    }
}