package com.larissa.agenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PersonalContact(
    override var name: String,
    override var phone: String,
    override var dataType: DataType?,
    private val reference: String)
    : Contact(name, phone,dataType,reference)

