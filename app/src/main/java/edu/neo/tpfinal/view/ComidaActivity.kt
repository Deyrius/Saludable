package edu.neo.tpfinal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import edu.neo.tpfinal.R
import edu.neo.tpfinal.fragment.AlmuerzoCenaFragment
import edu.neo.tpfinal.fragment.DesayunoMeriendaFragment
import edu.neo.tpfinal.fragment.TragosFragment
import edu.neo.tpfinal.viewmodel.UsuarioViewModel

class ComidaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida)
        val trago = intent.getSerializableExtra("trago") as String
        if (trago != ""){
            val tragoFrg = TragosFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frame_comida,tragoFrg)
            transaction.commit()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val almuerzoCenaFrg = AlmuerzoCenaFragment()
        val desayunoMeriendaFrg = DesayunoMeriendaFragment()
        val manager = supportFragmentManager
        val args = Bundle()
        val user = intent.getSerializableExtra("usuario") as String


        return when(item.itemId){
            R.id.m_desayuno ->{
                val transaction = manager.beginTransaction()
                val tipo = "desayuno"
                val tipoUsuario : ArrayList<String> = ArrayList()
                tipoUsuario.add(tipo)
                tipoUsuario.add(user)
                args.putStringArrayList("tipoUsuario",tipoUsuario)
                desayunoMeriendaFrg.arguments = args
                transaction.replace(R.id.frame_comida,desayunoMeriendaFrg)
                transaction.commit()
                true
            }
            R.id.m_almuerzo ->{
                val transaction = manager.beginTransaction()
                val tipo = "almuerzo"
                val tipoUsuario : ArrayList<String> = ArrayList()
                tipoUsuario.add(tipo)
                tipoUsuario.add(user)
                args.putStringArrayList("tipoUsuario",tipoUsuario)
                almuerzoCenaFrg.arguments = args
                transaction.replace(R.id.frame_comida,almuerzoCenaFrg)
                transaction.commit()
                true
            }
            R.id.m_merienda ->{
                val transaction = manager.beginTransaction()
                val tipo = "merienda"
                val tipoUsuario : ArrayList<String> = ArrayList()
                tipoUsuario.add(tipo)
                tipoUsuario.add(user)
                args.putStringArrayList("tipoUsuario",tipoUsuario)
                desayunoMeriendaFrg.arguments = args
                transaction.replace(R.id.frame_comida,desayunoMeriendaFrg)
                transaction.commit()
                true
            }
            R.id.m_cena ->{
                val transaction = manager.beginTransaction()
                val tipo = "Cena"
                val tipoUsuario : ArrayList<String> = ArrayList()
                tipoUsuario.add(tipo)
                tipoUsuario.add(user)
                args.putStringArrayList("tipoUsuario",tipoUsuario)
                almuerzoCenaFrg.arguments = args
                transaction.replace(R.id.frame_comida,almuerzoCenaFrg)
                transaction.commit()
                true
            }
            R.id.m_logout ->{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                true
            }
            else ->{
                Toast.makeText(this,"Opcion erronea.", Toast.LENGTH_SHORT).show()
                true
            }
        }

    }
}