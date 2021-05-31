package com.larissa.elevator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    private lateinit var mostrarAndar: TextView
    private lateinit var mostrarPessoas: TextView
    private lateinit var editarAndar: EditText
    private lateinit var btnAndar: Button
    private lateinit var btnEntrar: Button
    private lateinit var btnSair: Button

    private val totalAndares: Int = 12
    private val maximoPessoas: Int = 6
    private var numeroPessoas: Int = 0
    private var atualAndar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
    }

    private fun bindViews() {
        mostrarAndar = findViewById(R.id.txtAndar)
        mostrarPessoas = findViewById(R.id.txtPessoas)
        editarAndar = findViewById(R.id.edtAndar)
        btnAndar = findViewById(R.id.btnAndar)
        btnEntrar = findViewById(R.id.btnEntrar)
        btnSair = findViewById(R.id.btnSair)

        mostrarAndar.text = ("$atualAndar° andar")
        mostrarPessoas.text = ("$numeroPessoas/$maximoPessoas")


        btnAndar.setOnClickListener {
            var atualAndar = editarAndar.text.toString().toInt()
            if (atualAndar == null || atualAndar > totalAndares){
                editarAndar.error = "Digite um andar válido"

            }else{
                mostrarAndar.text = ("$atualAndar° andar")
            }
            editarAndar?.setText(null)
        }

        btnEntrar.setOnClickListener {
            numeroPessoas += 1
            if(numeroPessoas == null || numeroPessoas==0){
                Toast.makeText(this,
                    "Não tem ninguém no elevador!",
                    Toast.LENGTH_SHORT).show()
            } else if (numeroPessoas> maximoPessoas){
                Toast.makeText(this,
                    "O elevador já atingiu a quantidade máxima de pessoas!",
                    Toast.LENGTH_SHORT).show()
            }else {
                mostrarPessoas.text = ("$numeroPessoas/$maximoPessoas")
            }
        }

        btnSair.setOnClickListener {
            numeroPessoas -= 1
            if(numeroPessoas == null || numeroPessoas<0){
                Toast.makeText(this,
                    "Não tem ninguém no elevador!",
                    Toast.LENGTH_SHORT).show()
            }else {
                mostrarPessoas.text = ("$numeroPessoas/$maximoPessoas")
            }
        }
    }
}
