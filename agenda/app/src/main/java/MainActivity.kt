package com.larissa.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnSave : Button
    private lateinit var edtName : EditText
    private lateinit var edtPhone : EditText
    private lateinit var rdgDataType : RadioGroup
    private lateinit var edtDataType : EditText
    private val contactList : MutableList<Contact> = mutableListOf()
    private var dataTypeSelected : DataType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSave = findViewById(R.id.btnSave)

        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        rdgDataType = findViewById(R.id.rdgDataType)
        edtDataType = findViewById(R.id.edtDataType)


        btnSave.setOnClickListener {
            val typedName = edtName.text.toString()
            val typedPhone = edtPhone.text.toString()
            val typedData = edtDataType.text.toString()

            if(typedName.isEmpty()) edtName.error = getString(R.string.blankError)
            if(typedPhone.isEmpty()) edtPhone.error =  getString(R.string.blankError)
            if(typedData.isEmpty()) edtDataType.error = getString(R.string.blankError)

            dataTypeSelected?.let {
                when(dataTypeSelected){
                    DataType.PERSONAL -> contactList.add(PersonalContact(typedName , typedPhone , it , typedData))
                    else ->  contactList.add(ProfessionalContact(typedName , typedPhone , it , typedData))
                }
            }
            edtName.text.clear()
            edtPhone.text.clear()
            edtDataType.text.clear()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(LIST_KEY, ArrayList(contactList))
            startActivity(intent)
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            when (view.id) {
                R.id.rdPersonal ->
                    if (checked) {
                        dataTypeSelected  = DataType.PERSONAL
                        edtDataType.hint = getString(R.string.reference)
                        edtDataType.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                    }
                R.id.rdProfessional -> {
                    if (checked) {
                        dataTypeSelected  = DataType.PROFESSIONAL
                        edtDataType.hint = getString(R.string.email)
                        edtDataType.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    }
                }
            }
        }
    }
    companion object{
        val LIST_KEY = "CONTACTLIST"
    }
}
