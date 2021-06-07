package com.larissa.agenda

class ProfessionalContact(name : String,
                               phone: String,
                               dataType: DataType,
                               private val reference: String) :
            Contact(name,
                phone,
                dataType) {
    fun displayContact(): String {
        return "Contato ${dataType?.description} - Nome: $name / Celular: $phone / Referência: $reference"
    }
}
