package com.larissa.primeiroappemandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textoGlobal : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textoGlobal = findViewById(R.id.texto)

        var nome : String = "Larissa"
        exibirTexto(nome)

    }
    fun exibirTexto(nome: String){
        textoGlobal.text = "Este é o primeiro app da $nome!"
    }
}