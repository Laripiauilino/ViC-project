package com.larissa.elevator

import android.widget.EditText
import android.widget.Toast

data class Elevator(var currentFloor: Int,
                    val totalFloors: Int,
                    var numPeople: Int,
                    val maxPeople: Int){

    fun floor(floor: Int): Boolean {
        if (floor in 0..totalFloors) {
            currentFloor = floor
            return true
        } else {
            return false
        }
    }

    fun  enterPeople(): Boolean {
        if(numPeople < maxPeople){
            numPeople += 1
            return true
        }else {
            return false
        }
    }

    fun exitPeople(): Boolean {
        if(numPeople > 0){
            numPeople -= 1
            return true
        }else {
            return false
        }
    }
}