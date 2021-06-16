package com.larissa.agenda

open class PersonalContact(name: String,phone: String, dataType: DataType, private val reference: String)
    : Contact(name, phone,dataType) {
    open fun displayPersonalContact(): String {
        return "Contato ${dataType?.description} - Nome: $name / Celular: $phone / Referência: $reference"
    }
}

