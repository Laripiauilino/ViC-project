package com.larissa.elevator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var showFloor: TextView
    private lateinit var showPeople: TextView
    private lateinit var editFloor: EditText
    private lateinit var btnFloor: Button
    private lateinit var btnEnter: Button
    private lateinit var btnExit: Button

    private var numPeople: Int = 0
    private var currentFloor: Int = 0
    private val totalFloors: Int = 12
    private val maxPeople: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFloor = findViewById(R.id.txtFloor)
        showPeople = findViewById(R.id.txtPeople)
        editFloor = findViewById(R.id.edtFloor)
        btnFloor = findViewById(R.id.btnFloor)
        btnEnter = findViewById(R.id.btnEnter)
        btnExit = findViewById(R.id.btnExit)

        val elevator = Elevator(currentFloor = currentFloor ,
            totalFloors = totalFloors ,
            numPeople = numPeople ,
            maxPeople = maxPeople)

        btnFloor.setOnClickListener {

            val currentFloor = editFloor.text.toString()

            if (currentFloor.isNotBlank()) {
                if (elevator.floor(currentFloor.toInt())) {
                    if (currentFloor.toInt() == 0) {
                        showFloor.text = "Térreo"
                    } else {
                        showFloor.text = "${currentFloor}° andar"
                    }
                } else {
                    editFloor.error = "Digite um andar válido!"
                }
                editFloor.setText(null)
            }else{
                editFloor.error = "O andar não foi inserido!"
            }
        }

        btnEnter.setOnClickListener {
            if (elevator.enterPeople()) {
                showPeople.text = ("${elevator.numPeople}/${elevator.maxPeople}")
            } else {
                Toast.makeText(this@MainActivity ,
                    "O elevador já atingiu a quantidade máxima de pessoas!" ,
                    Toast.LENGTH_SHORT).show()
            }
        }

        btnExit.setOnClickListener {
            if (elevator.exitPeople()) {
                showPeople.text = ("${elevator.numPeople}/${elevator.maxPeople}")
            } else {
                Toast.makeText(this@MainActivity ,
                    "Não tem ninguém no elevador!" ,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}

