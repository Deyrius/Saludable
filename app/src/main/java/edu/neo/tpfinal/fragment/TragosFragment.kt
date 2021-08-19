package edu.neo.tpfinal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Tragos
import edu.neo.tpfinal.viewmodel.ComidaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TragosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.tragos_layout,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cvm = ViewModelProvider(this).get(ComidaViewModel::class.java)
        var nombre : TextView = view.findViewById(R.id.t_nombre)
        var descripcion : TextView = view.findViewById(R.id.t_descripcion)
        var foto : ImageView = view.findViewById(R.id.t_foto)

        cvm.getTragos().enqueue(object : Callback<Tragos>{
            override fun onResponse(call: Call<Tragos>, response: Response<Tragos>) {
                if (response.body() != null){
                    val data = response.body()
                    nombre.setText(data?.drinks?.get(0)?.strDrink)
                    descripcion.setText(data?.drinks?.get(0)?.strCategory)
                    Glide
                        .with(view.context)
                        .load(data?.drinks?.get(0)?.strDrinkThumb)
                        .centerCrop()
                        .into(foto)
                }
            }
            override fun onFailure(call: Call<Tragos>, t: Throwable) {
                Toast.makeText(view.context,"Error con API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}