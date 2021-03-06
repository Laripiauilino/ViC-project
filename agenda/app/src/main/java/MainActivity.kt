package com.larissa.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rvContacts: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var edtName : EditText
    private lateinit var edtPhone : EditText
    private lateinit var rdgDataType : RadioGroup
    private lateinit var edtDataType : EditText
    private lateinit var btnSave : Button
    private lateinit var edtSearch : EditText
    private lateinit var btnSearch : Button
    private lateinit var txtDisplay : TextView
    private lateinit var btnDisplayAll : Button

    private val contactList : MutableList<Contact> = mutableListOf()
    private var dataTypeSelected : DataType? = null

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
        rvContacts = findViewById(R.id.rvContacts)

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


        }

        btnSearch.setOnClickListener {
            btnDisplayAll.visibility = View.VISIBLE
            val typedSearch = edtSearch.text.toString()
            val search = contactList.find{contact -> contact.name.contains(typedSearch)}
            val searchList: MutableList<String> = mutableListOf()


            if(typedSearch.isEmpty()) getString(R.string.blankError)
                .also { edtSearch.error = it }
            if(search != null) {
                when (search) {
                    is PersonalContact -> search.displayContact()
                        .also { searchList.add(it) }
                    is ProfessionalContact -> search.displayContact()
                        .also { searchList.add(it) }
                }
            }else{
                Toast.makeText(this,getString(R.string.searchError), Toast.LENGTH_LONG).show()
            }
            contactAdapter = ContactAdapter(context = this, dataSet = searchList)

            edtSearch.text.clear()
        }

        btnDisplayAll.setOnClickListener {
            val sortedList: MutableList<String> = mutableListOf()
            contactList.sortBy { contact -> contact.name }
            for (sorted in contactList) {
                when (sorted) {
                    is PersonalContact -> sorted.displayContact()
                        .also { sortedList.add(it + "\n\n")}
                    is ProfessionalContact -> sorted.displayContact()
                        .also { it.also { sortedList.add( it + "\n\n")} }
                }
            }
            if(contactList.isEmpty()){
                Toast.makeText(this,getString(R.string.registredError), Toast.LENGTH_LONG).show()
            }
            contactAdapter = ContactAdapter(context = this, dataSet = sortedList)
            btnDisplayAll.visibility = View.INVISIBLE
        }
        rvContacts.adapter = contactAdapter
        rvContacts.layoutManager = GridLayoutManager(this,2)

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(LIST_KEY, ArrayList(contactAdapter))
        startActivity(intent)
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
        private fun changeList() {
            contactAdapter.updateList()
    }


    }
}
