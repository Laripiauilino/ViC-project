package com.larissa.agenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Contact(open val name: String,
                   open val phone: String,
                   open var dataType: DataType? = null,
                   val complement: String ): Parcelable {
    open fun displayContact(): String {
        return "Contato ${dataType?.description} - Nome: $name /n Celular: $phone /n  $complement"
    }
}