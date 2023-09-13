package com.example.tutno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tutno.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.editTextNumberDecimal.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        val selectedId = binding.radioGroup.checkedRadioButtonId

        if(stringInTextField.isEmpty()){
            Toast.makeText(this, "Please Enter an amount", Toast.LENGTH_LONG).also {
                it.show()
            }
        }

        if (cost==null){
            return
        }


        val tipPercentage = when(selectedId){
            R.id.radioButton -> 0.20
            R.id.radioButton2 -> 0.18
            R.id.radioButton3 -> 0.15
            else ->   0.0
        }

        var tip = tipPercentage * cost


        val roundUp = binding.switch1.isChecked

        if (roundUp){
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)

    }


}