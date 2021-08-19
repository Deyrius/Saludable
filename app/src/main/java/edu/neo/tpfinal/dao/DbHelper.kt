package edu.neo.tpfinal.dao

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import edu.neo.tpfinal.model.Comida
import edu.neo.tpfinal.model.Usuario

class DbHelper {
    var usuarioDevolver = ""
    var lista : ArrayList<Usuario> = ArrayList()
    lateinit var  firestoreListener: ListenerRegistration
    fun reiniciarDevolver(){
        usuarioDevolver = ""
    }

    fun guardarUsuarioFireBase(usuario: Usuario,context: Context):Boolean{
        val base = FirebaseFirestore.getInstance()
        base.collection("usuario")
            .add(usuario)
            .addOnSuccessListener {  Toast.makeText(context,"Se agrego al usuario: " + usuario.user,Toast.LENGTH_SHORT).show() }
        return true
    }

    fun agregar(usuario: Usuario){
        lista.add(usuario)
    }

    fun usuarioEncontrado():Boolean{
        return usuarioDevolver == "encontrado"
    }
    fun autentificarUsuario(user:String,pass: String,context: Context){
        val base = FirebaseFirestore.getInstance()
        val registroRef = base.collection("usuario")
            .whereEqualTo("user",user)
            .limit(1)

        firestoreListener = registroRef.addSnapshotListener{snapshots, error ->
            if(error != null){
                Toast.makeText(context,"Reintente por favor",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            for (snapshot in snapshots!!.documentChanges){
                val usuario = snapshot.document.toObject(Usuario::class.java)
                usuario.id = snapshot.document.id
                if (usuario.pass == pass){
                    usuarioDevolver = "encontrado"
                }

            }

        }
    }
    fun todosUsuarios(context: Context){
        val base = FirebaseFirestore.getInstance()
        val registroRef = base.collection("usuario")
        firestoreListener = registroRef.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Toast.makeText(context, "Reintente por favor", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            for (snapshot in snapshots!!.documentChanges) {
                val persona = snapshot.document.toObject(Usuario::class.java)
                persona.id = snapshot.document.id
                when (snapshot.type) {
                    DocumentChange.Type.ADDED -> agregar(persona)
                }
            }
        }
    }

    fun verificarUsuario(user:String){
        for (usuarios in lista){
            if (usuarios.user.equals(user.trim().lowercase())) throw IllegalArgumentException("El username ya se encuentra en uso")
        }
    }
    fun verificarDocumento(documento:String){
        for (usuarios in lista){
            if (usuarios.dni.equals(documento.trim().lowercase())) throw IllegalArgumentException("El DNI ya se encuentra en uso")
        }
    }

   fun guardarComidaFireBase(comida: Comida,context: Context):Boolean{
       val base = FirebaseFirestore.getInstance()
       base.collection("comida")
           .add(comida)
           .addOnSuccessListener {  Toast.makeText(context,"Se guardó su " + comida.tipoComida,Toast.LENGTH_SHORT).show() }
       return true
   }

}