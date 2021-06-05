package com.larissa.agenda


data class Contact(private val nome: String,
                       private val credencial: String,
                       private var setor: Int){

    fun getCredencial() : String = credencial

    fun exibirRegistro() : String{
        return "$nome - $credencial do setor $setor"
    }
}