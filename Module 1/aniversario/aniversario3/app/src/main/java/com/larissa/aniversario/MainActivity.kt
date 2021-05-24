package com.larissa.aniversario


import android.app.DatePickerDialog
import android.os.Bundle
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
        // date picker dialog
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
            var proximoAniversario = LocalDate.of(executaDataAniversario.year, executaDataAniversario.month, executaDataAniversario.dayOfMonth)
            var dias = ChronoUnit.DAYS.between(dataAtual, proximoAniversario)

            mensagem.text = "Olá $executaNome, faltam $dias dias para o seu aniversário! Espero que você ganhe um(a) $executaPresente!"
        }
    }
}

