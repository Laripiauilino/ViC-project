package com.larissa.agenda

import kotlinx.android.parcel.Parcelize

@Parcelize
open class ProfessionalContact(
    override val name : String,
    override val phone: String,
    override var dataType: DataType?,
    private val email: String) :
            Contact(name, phone, dataType, email)

