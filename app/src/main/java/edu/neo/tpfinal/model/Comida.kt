package edu.neo.tpfinal.model

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class  Comida(
    @get:Exclude var id: String? = null,
    var tipoComida: String? = "",
    var comidaPrincipal: String? = "",
    var comidaSecundaria: String? = "",
    var bebida: String? ="",
    var postre: String?="",
    var otroAlimento:String?="",
    var hambre: String?="",
    var user:String?="",
    var fecha:String?=""):Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comida

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}