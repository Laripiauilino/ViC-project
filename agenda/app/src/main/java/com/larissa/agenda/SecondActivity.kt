package com.larissa.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    private lateinit var edtSearch : EditText
    private lateinit var btnSearch : Button
    private lateinit var txtDisplay : TextView
    private lateinit var btnDisplayAll : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)
        txtDisplay = findViewById(R.id.txtDisplay)
        btnDisplayAll = findViewById(R.id.btnDisplayAll)

        val contactList = intent.extras?.get(MainActivity.LIST_KEY) as  MutableList<Contact>


        btnSearch.setOnClickListener {
            btnDisplayAll.visibility = View.VISIBLE
            val typedSearch = edtSearch.text.toString()
            val search = contactList.find{contact -> contact.name.contains(typedSearch)}

            if(typedSearch.isEmpty()) getString(R.string.blankError)
                .also { edtSearch.error = it }
            if(search != null) {
                when (search) {
                    is PersonalContact -> search.displayContact()
                        .also { txtDisplay.text = it }
                    is ProfessionalContact -> search.displayContact()
                        .also { txtDisplay.text = it }
                }
            }else{
                Toast.makeText(this,getString(R.string.searchError), Toast.LENGTH_LONG).show()
            }
            edtSearch.text.clear()
        }

        btnDisplayAll.setOnClickListener {
            var message = " "
            contactList.sortBy { contact -> contact.name }
            for (sorted in contactList) {
                when (sorted) {
                    is PersonalContact -> sorted.displayContact()
                        .also { message += it + "\n\n"}
                    is ProfessionalContact -> sorted.displayContact()
                        .also { it.also { message += it + "\n\n"} }                }
            }
            if(contactList.isEmpty()){
                Toast.makeText(this,getString(R.string.registredError), Toast.LENGTH_LONG).show()
            }else{
                txtDisplay.text = message
            }
            btnDisplayAll.visibility = View.INVISIBLE
        }
    }
}