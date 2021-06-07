package com.larissa.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var rdgDataType: RadioGroup
    private lateinit var edtDataType: EditText
    private lateinit var btnSave: Button
    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var txtDisplay: TextView
    private lateinit var btnDisplayAll: Button

    private var dataTypeSelected: DataType? = null
//    private var funcionarios: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
    }

    private fun bindViews() {
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        rdgDataType = findViewById(R.id.rdgDataType)
        edtDataType = findViewById(R.id.edtDataType)
        btnSave = findViewById(R.id.btnSave)
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        txtDisplay = findViewById(R.id.txtDisplay)
        btnDisplayAll = findViewById(R.id.btnDisplayAll)


        btnSave.setOnClickListener {
            val nameTyped = edtName.text.toString()
            val phoneTyped = edtPhone.text.toString().toInt()

            if (dataTypeSelected != null){
                
            }
            dataTypeSelected?.let{
                Contact(nameTyped,phoneTyped,it)
            }
        }
    }
}