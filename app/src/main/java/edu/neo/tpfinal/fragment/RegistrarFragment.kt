package edu.neo.tpfinal.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Usuario
import edu.neo.tpfinal.view.MainActivity
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import java.lang.Exception

class RegistrarFragment(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view: View = inflater.inflate(R.layout.registrar_usuario_layout,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val uvm = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        uvm.todosLosUsuarios(view.context)
        val user: EditText = view.findViewById(R.id.r_user)
        val pass: EditText = view.findViewById(R.id.r_pass)
        val name: EditText = view.findViewById(R.id.r_name)
        val lastname: EditText = view.findViewById(R.id.r_lastname)
        val dni: EditText = view.findViewById(R.id.r_dni)
        val gender: RadioGroup = view.findViewById(R.id.r_rbg_gender)
        val treatment: RadioGroup = view.findViewById(R.id.r_rbg_treatment)
        val localidad: EditText = view.findViewById(R.id.r_localidad)
        val registrar: Button = view.findViewById(R.id.r_saveuser)
        val fecha:TextView = view.findViewById(R.id.r_fecha)
         fun FechaCalendar() {
            view?.findViewById<ImageButton?>(R.id.picker)?.setOnClickListener {
                val builder = MaterialDatePicker.Builder.datePicker()
                val pickerVerdadero: MaterialDatePicker<*> = builder.build()
                pickerVerdadero.show(childFragmentManager, pickerVerdadero.toString())
                pickerVerdadero.addOnPositiveButtonClickListener {
                    view?.findViewById<TextView>(R.id.r_fecha)
                        ?.setText(pickerVerdadero.headerText)
                }
            }
        }
        FechaCalendar()
        registrar.setOnClickListener {
            try {
                uvm.usuarioExistente(user.text.toString().trim().lowercase())
                uvm.dniExistente(dni.text.toString().trim().lowercase())
                uvm.guardarUsuario(
                    Usuario(
                        user = if(user.text.toString().isNullOrBlank())throw IllegalArgumentException() else user.text.toString().trim().lowercase(),
                        pass = if (pass.text.toString().isNullOrBlank())throw IllegalArgumentException() else pass.text.toString().trim().lowercase(),
                        nombre = if (name.text.toString().isNullOrBlank())throw IllegalArgumentException() else name.text.toString().trim().lowercase(),
                        apellido = if (lastname.text.toString().isNullOrBlank()) throw IllegalArgumentException() else lastname.text.toString().trim().lowercase(),
                        dni = if (dni.text.toString().isNullOrBlank())throw IllegalArgumentException() else dni.text.toString().trim().lowercase(),
                        nacimiento = if (fecha.text.toString().isNullOrBlank())throw IllegalArgumentException() else fecha.text.toString().trim().lowercase(),
                        localidad = if (localidad.text.toString().isNullOrBlank())throw IllegalArgumentException() else localidad.text.toString().trim().lowercase(),
                        tratamiento = uvm.SeleccionTreatment(view.findViewById(treatment.checkedRadioButtonId)),
                        genero = uvm.SeleccionGender(view.findViewById(gender.checkedRadioButtonId))
                    ),it.context)
                val intent = Intent(it.context,MainActivity::class.java)
                startActivity(intent)
                getActivity()?.finish()

            }catch (e:Exception){
                Log.e("Error",e.message.toString())
                Toast.makeText(it.context,"Usuario/DNI ya registrados ó un campo vacio",Toast.LENGTH_SHORT).show()
            }
        }
    }
}