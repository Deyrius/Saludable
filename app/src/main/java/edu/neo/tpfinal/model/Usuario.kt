package edu.neo.tpfinal.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import java.io.Serializable


data class Usuario(
    @get:Exclude var id: String? = null,
    var user: String? = "",
    var pass: String? = "",
    var nombre: String? = "",
    var apellido: String? = "",
    var dni: String? = "",
    var nacimiento: String? = "",
    var localidad: String? = "",
    var tratamiento: String? = "",
    var genero:String? = ""):Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}