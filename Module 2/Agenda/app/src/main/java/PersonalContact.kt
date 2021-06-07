package com.larissa.agenda

class PersonalContact(name: String,
                           phone: Int,
                           dataType: DataType,
                           private val email: String) :
            Contact(name,
                phone,
                dataType) {
    fun displayContact(): String {
        return "Contato ${dataType?.description} - Nome: $name / Celular: $phone / E-mail: $email"
    }
}