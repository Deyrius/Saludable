package edu.neo.tpfinal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import edu.neo.tpfinal.R
import edu.neo.tpfinal.fragment.RegistrarFragment
import edu.neo.tpfinal.model.Usuario
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var  usuario:EditText
    lateinit var  pass:EditText
    lateinit var  ingresar: Button
    lateinit var  registrar: Button
    lateinit var  frameLayout: FrameLayout
    lateinit var  botones: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializador()
        val uvm:UsuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        val registrarFrg = RegistrarFragment()
        val manager = supportFragmentManager
        uvm.todosLosUsuarios(this)

        registrar.setOnClickListener {
            usuario.setVisibility(View.GONE)
            pass.setVisibility(View.GONE)
            botones.setVisibility(View.GONE)
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.main_frame,registrarFrg)
            transaction.commit()
        }
        ingresar.setOnClickListener {
            try {
                var  user = if (usuario.text.toString().isNullOrBlank()) throw IllegalArgumentException() else usuario.text.toString()
                var  contra = if(pass.text.toString().isNullOrBlank()) throw IllegalArgumentException() else pass.text.toString()
                val intent = Intent(this,ComidaActivity::class.java)
                if (uvm.verificarUsuarioYPass(user,contra,this)){
                    intent.putExtra("usuario",usuario.text.toString())
                    intent.putExtra("trago","")
                    startActivity(intent)
                    finish()
                }
            }catch (e:Exception){
                Log.e("Error Login",e.message.toString())
                Toast.makeText(this,"Error al logear",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun inicializador(){
        usuario = findViewById(R.id.main_user)
        pass = findViewById(R.id.main_pass)
        ingresar = findViewById(R.id.main_b_ingresar)
        registrar = findViewById(R.id.main_b_registrar)
        frameLayout = findViewById(R.id.main_frame)
        botones = findViewById(R.id.main_layout_b)
    }


}