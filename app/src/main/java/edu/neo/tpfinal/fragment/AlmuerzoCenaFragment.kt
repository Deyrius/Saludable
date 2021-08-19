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
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.view.ComidaActivity
import edu.neo.tpfinal.viewmodel.ComidaViewModel
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import java.lang.Exception

class AlmuerzoCenaFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.almuerzo_cena_layout,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cvm = ViewModelProvider(this).get(ComidaViewModel::class.java)
        var comidaPrincipal: EditText = view.findViewById(R.id.a_edt_comidaPrincipal)
        var comidaSecundaria: EditText = view.findViewById(R.id.a_edt_comidaSecundaria)
        var hambre: RadioGroup = view.findViewById(R.id.ac_rg_hambre)
        val guardar: Button = view.findViewById(R.id.ac_b_guardar)
        val args = arguments
        val tipoUsuario = (args?.getStringArrayList("tipoUsuario")as ArrayList<String>)
        val tipo = tipoUsuario[0]
        val user = tipoUsuario[1]
        val spnBebidas: Spinner = view.findViewById(R.id.a_spn_bebida)
        val listaBebidas = arrayOf("Agua","Jugo","Gaseosa","Vino","Cerveza")
        val adaptador = ArrayAdapter(view.context,android.R.layout.simple_spinner_item,listaBebidas)
        spnBebidas.adapter = adaptador
        var posicion:Int
        fun SpnBebidas(): String {
            posicion = spnBebidas.selectedItemPosition
            return listaBebidas[posicion]
        }
        var postre: EditText = view.findViewById(R.id.ac_edt_postre)
        val postreSi: Button = view.findViewById(R.id.ac_b_postre_si)
        val postreNo:Button = view.findViewById(R.id.ac_b_postre_no)
        postreSi.setOnClickListener {
            postre.setVisibility(View.VISIBLE)
        }
        postreNo.setOnClickListener {
            postre.setVisibility(View.GONE)
            Toast.makeText(view.context,"No selecciono postre.",Toast.LENGTH_SHORT).show()
            postre.setText("")
        }
        val comidaExtra: EditText = view.findViewById(R.id.ac_alimExtra)
        val comidaExtraSi: Button = view.findViewById(R.id.ac_b_alimento_extra_si)
        val comidaExtraNo: Button = view.findViewById(R.id.ac_b_alimento_ext_no)
        comidaExtraSi.setOnClickListener {
            comidaExtra.setVisibility(View.VISIBLE)
        }
        comidaExtraNo.setOnClickListener {
            comidaExtra.setVisibility(View.GONE)
            Toast.makeText(view.context,"No selecciono comida extra",Toast.LENGTH_SHORT).show()
            comidaExtra.setText("")
        }
        guardar.setOnClickListener {
            try {
                cvm.guardarComida(Comida(
                    comidaPrincipal = if(comidaPrincipal.text.toString().isNullOrBlank())throw IllegalArgumentException() else comidaPrincipal.text.toString().trim().lowercase(),
                    comidaSecundaria = if(comidaSecundaria.text.toString().isNullOrBlank())throw IllegalArgumentException() else comidaSecundaria.text.toString().trim().lowercase(),
                    bebida = SpnBebidas(),
                    postre = if(postre.text.toString().isNullOrBlank())"no quiso postre." else postre.text.toString().trim().lowercase(),
                    otroAlimento = if(comidaExtra.text.toString().isNullOrBlank())"no quiso comida extra." else comidaExtra.text.toString().trim().lowercase(),
                    fecha = cvm.fechaActual(),
                    hambre = cvm.hambre(opcion = view.findViewById(hambre.checkedRadioButtonId)),
                    tipoComida = tipo,
                    user = user),it.context)
                Toast.makeText(view.context,"Se guardo su comida.",Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(view.context,ComidaActivity::class.java)
                intent.putExtra("usuario",user)
                intent.putExtra("trago","si")
                startActivity(intent)
                getActivity()?.finish()
            }catch (e:Exception){
                Log.e("Error guardar comida.",e.message.toString())
                Toast.makeText(it.context,"Hubo un error al guardar.",Toast.LENGTH_SHORT).show()
            }
        }


    }
}