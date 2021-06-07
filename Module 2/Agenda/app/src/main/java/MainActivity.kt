package com.larissa.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils.isEmpty
import android.view.View
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
    private var contactList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            val phoneTyped = edtPhone.text.toString()
            val dataTyped = edtDataType.text.toString()

            edtName.error = if(nameTyped.isNotEmpty()) null else "Insira a informação solicitada!"
            edtPhone.error = if(phoneTyped.isNotEmpty()) null else "Insira a informação solicitada!"

            dataTypeSelected?.let{
                edtDataType.error = if(dataTyped.isNotEmpty()) null else "Insira a informação solicitada!"
                if (dataTypeSelected?.description == "pessoal"){
                    var personalContact = PersonalContact(nameTyped,phoneTyped,it,dataTyped).displayContact()
                    txtDisplay.text ="$personalContact"
                    contactList.add(personalContact)
                }else if(dataTypeSelected?.description == "profissional"){
                    var professionalContact = ProfessionalContact(nameTyped,phoneTyped,it,dataTyped).displayContact()
                    txtDisplay.text ="$professionalContact"
                    contactList.add(professionalContact)
                }
            }
        }
        btnSearch.setOnClickListener {
            btnDisplayAll.visibility = View.VISIBLE
            var search = edtSearch.text.toString()
            var listSearch = contactList.contains(search)
            txtDisplay.text = "$listSearch"

        }
        btnDisplayAll.setOnClickListener {
            btnSearch.visibility = View.INVISIBLE
            val sortedList = contactList.sortedDescending()
            txtDisplay.text = "$sortedList"

        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            when (view.id) {
                R.id.rdPersonal ->
                    if (checked) {
                        dataTypeSelected  = DataType.PERSONAL
                        edtDataType.hint = "Referência"
                        edtDataType.inputType = InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE
                    }
                R.id.rdProfessional -> {
                    if (checked) {
                        dataTypeSelected  = DataType.PROFESSIONAL
                        edtDataType.hint = "E-mail"
                        edtDataType.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    }
                }
            }
        }
    }

}

