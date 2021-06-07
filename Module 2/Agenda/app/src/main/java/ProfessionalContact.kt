package com.larissa.agenda

class ProfessionalContact(name : String,
                               phone: Int,
                               dataType: DataType,
                               val reference: String) :
            Contact(name,
                phone,
                dataType) {
    fun displayContact(): String {
        return "Contato ${dataType?.description} - Nome: $name / Celular: $phone / Referência: $reference"
    }
}
