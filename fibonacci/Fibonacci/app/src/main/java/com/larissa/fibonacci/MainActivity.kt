package com.larissa.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var inserirNumeroTermos: EditText
    lateinit var calcularSerie: Button
    lateinit var mostrarSerie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar variaveis e componentes
        inserirNumeroTermos = findViewById(R.id.edtTermos)
        calcularSerie = findViewById(R.id.btnCalcular)
        mostrarSerie = findViewById(R.id.txtSerie)

        calcularSerie.setOnClickListener {
            val numeroTermos = inserirNumeroTermos.text.toString()
            if (numeroTermos.isNotEmpty()) {
                fibonacci(numeroTermos.toInt())
            } else {
                inserirNumeroTermos.error = "Digite um número válido!"
            }
            inserirNumeroTermos.setText(null)
        }
    }

    fun fibonacci(numeroTermos: Int) {
        var termo1: Long = 0
        var termo2: Long = 1
        var listaSerie = ""

        if (numeroTermos == 0) {
            listaSerie = "$termo1"
        } else if (numeroTermos == 1) {
            listaSerie = "$termo1, $termo2"
        } else {
            listaSerie = "$termo1, $termo2"
            for (i in 1..numeroTermos-2) {
                val soma = termo1 + termo2
                termo1 = termo2
                termo2 = soma
                listaSerie += ", $soma"
            }
        }
        mostrarSerie.text = "A série de Fibonacci até o $numeroTermos° termo é: $listaSerie."
    }
}