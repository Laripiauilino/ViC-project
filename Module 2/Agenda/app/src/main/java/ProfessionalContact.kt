package com.larissa.agenda

open class ProfessionalContact(name : String,
                               phone: String,
                               dataType: DataType,
                               private val email: String) :
            Contact(name,
                phone,
                dataType) {
    open fun displayProfessionalContact(): String {
        return "Contato ${dataType?.description} - Nome: $name / Celular: $phone / E-mail: $email"
    }
}
