package edu.neo.tpfinal

import edu.neo.tpfinal.model.Usuario
import edu.neo.tpfinal.viewmodel.UsuarioViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito


class ExampleUnitTest {
  val uservm: UsuarioViewModel = Mockito.mock(UsuarioViewModel::class.java)
    val usuario: Usuario = Mockito.mock(Usuario::class.java)

    @Before
    fun initializateElements(){
        Mockito.`when`(uservm.usuarioExistente("test"))
    }

    @Test
    fun verifica(){
        assertEquals(uservm.usuarioExistente("test2"),false)
    }
}