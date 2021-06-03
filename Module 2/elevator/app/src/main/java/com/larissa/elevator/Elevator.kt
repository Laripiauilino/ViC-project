package com.larissa.elevator

import android.widget.EditText
import android.widget.Toast

data class Elevator(var editFloor: Int,
                    var numPeople: Int,
                    var currentFloor: Int,
                    val totalFloor:Int,
                    val maxPeople: Int){

    fun floor(){
        currentFloor = editFloor

        if (currentFloor == null || currentFloor > totalFloor){
            editFloor.error = "Digite um andar válido"

        }else{
            return editFloor
        }
        currentFloor?.setText(null)
    }

    fun  enterPeople(){
        numPeople += 1
        if(numPeople == null || numPeople == 0){
            Toast.makeText(this@MainActivity,
            "Não tem ninguém no elevador!",
            Toast.LENGTH_SHORT).show()
        } else if (numPeople> maxPeople){
            Toast.makeText(this@MainActivity,
            "O elevador já atingiu a quantidade máxima de pessoas!",
            Toast.LENGTH_SHORT).show()
        }else {
            return numPeople
        }
    }

    fun exitPeople(){
        numPeople -= 1
        if(numPeople == null || numPeople<0){
            Toast.makeText(this@MainActivity,
            "Não tem ninguém no elevador!",
            Toast.LENGTH_SHORT).show()
        }else {
            return numPeople
        }
    }
}