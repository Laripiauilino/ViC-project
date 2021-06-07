package com.larissa.agenda

open class Contact(open val name: String,
                   open val phone: Int,
                   open var dataType: DataType? = null){


    open fun getName() : String = name

    open fun getPhone(): Int = phone

}
