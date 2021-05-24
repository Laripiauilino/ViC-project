package com.larissa.listaaniversario

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {

    // Declarar variaveis
    var nome: EditText? = null
    var presente: EditText? = null
    lateinit var dataAniversario: TextView
    var selecionaDataAniversario: DatePickerDialog? = null
    lateinit var executar: Button
    lateinit var mensagem: TextView
    val adicionarMensagem = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar variaveis
        nome = findViewById(R.id.edtNome)
        presente = findViewById(R.id.edtPresente)
        dataAniversario = findViewById(R.id.txtData)
        executar = findViewById(R.id.btnExecutar)
        mensagem = findViewById(R.id.txtMensagem)

        val calendario = Calendar.getInstance()
        val dia = calendario[Calendar.DAY_OF_MONTH]
        val mes = calendario[Calendar.MONTH]
        val ano = calendario[Calendar.YEAR]

        dataAniversario.setOnClickListener {
            selecionaDataAniversario = DatePickerDialog(
                this@MainActivity,
                { view, ano, executaMes, executaDia -> dataAniversario.setText(executaDia.toString() + "/" + (executaMes + 1) + "/" + ano) },
                ano,
                mes,
                dia
            )
            selecionaDataAniversario!!.show()
        }
        executar.setOnClickListener {
            val executaNome = nome?.text.toString()
            val executaPresente = presente?.text.toString()
            val executaDataAniversario = LocalDate.parse(dataAniversario?.text.toString(), DateTimeFormatter.ofPattern("dd/M/yyyy"))

            val dataAtual = LocalDate.now()
            val proximoAniversario = LocalDate.of(executaDataAniversario.year, executaDataAniversario.month, executaDataAniversario.dayOfMonth)
            val dias = ChronoUnit.DAYS.between(dataAtual, proximoAniversario)

            adicionarMensagem.add("Faltam $dias dias para o aniversário do(a) $executaNome e ele(a) quer ganhar um(a) $executaPresente.")
            var listaMensagem = ""
            for(umaMensagem in adicionarMensagem) {
                listaMensagem+= "$umaMensagem \n"
            }
            mensagem.text = listaMensagem

            nome?.setText(null)
            presente?.setText(null)
            dataAniversario?.setText(null)
        }




    }


}
