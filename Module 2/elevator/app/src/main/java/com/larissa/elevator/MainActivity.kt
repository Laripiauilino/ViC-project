package com.larissa.elevator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var showFloor: TextView
    private lateinit var showPeople: TextView
    private lateinit var editFloor: EditText
    private lateinit var btnFloor: Button
    private lateinit var btnEnter: Button
    private lateinit var btnExit: Button

    var numPeople: Int = 0
    var currentFloor: Int = 0
    val totalFloors: Int = 12
    val maxPeople: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        firstSetting()
        callClickViews()
    }

    private fun bindViews() {
        showFloor = findViewById(R.id.txtFloor)
        showPeople = findViewById(R.id.txtPeople)
        editFloor = findViewById(R.id.edtFloor)
        btnFloor = findViewById(R.id.btnFloor)
        btnEnter = findViewById(R.id.btnEnter)
        btnExit = findViewById(R.id.btnExit)
    }

    private fun firstSetting(){
        showFloor.text = ("$currentFloor° andar")
        showPeople.text = ("$numPeople/$maxPeople")
    }


    private fun callClickViews() {
        btnFloor.setOnClickListener {
            currentFloor = Elevator.floor()
            showFloor.text = ("$currentFloor° andar")
            currentFloor.setText(null)

        }
            showFloor.error

        btnEnter.setOnClickListener {
            numPeople = Elevator.enterPeople()
            showPeople.text = ("$numPeople/$maxPeople")
        }

        btnExit.setOnClickListener {
            numPeople = Elevator.exitPeople()
            showPeople.text = ("$numPeople/$maxPeople")
        }
    }
}
