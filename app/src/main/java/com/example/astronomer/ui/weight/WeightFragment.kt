package com.example.astronomer.ui.weight

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.Planets
import com.example.astronomer.R
import com.example.astronomer.Planets.*
import com.google.android.material.button.MaterialButton

class WeightFragment : Fragment() {

    private lateinit var weightViewModel: WeightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        weightViewModel = ViewModelProviders.of(this).get(WeightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weight, container, false)


        val editWeight: EditText = root.findViewById(R.id.editWeight)
        val buttonWeight: MaterialButton = root.findViewById(R.id.buttonWeight)
        val textMercury: TextView = root.findViewById(R.id.textMercury)
        val textVenus: TextView = root.findViewById(R.id.textVenus)
        val textMars: TextView = root.findViewById(R.id.textMars)
        val textJupiter: TextView = root.findViewById(R.id.textJupiter)
        val textSaturn: TextView = root.findViewById(R.id.textSaturn)
        val textUranus: TextView = root.findViewById(R.id.textUranus)
        val textNeptune: TextView = root.findViewById(R.id.textNeptune)
        val textPluto: TextView = root.findViewById(R.id.textPluto)
        val textMoon: TextView = root.findViewById(R.id.textMoon)
        val planets = Planets()

        buttonWeight.setOnClickListener {

            val weight = editWeight.text.toString()
            if (weight.equals(".")) {
                val toast = Toast.makeText(this.context, "The input cannot have only a point", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            } else if (weight.equals("")) {
                val toast = Toast.makeText(this.context, "Enter your weight, the input cannot be empty", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            } else if (weight.length > 10) {
                val toast = Toast.makeText(this.context, "The input cannot be longer than 10 digits", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            } else {

                weight.toFloat()
                val op1 = (weight.toFloat() * planets.mercury) / planets.earth
                val op2 = (weight.toFloat() * planets.venus) / planets.earth
                val op3 = (weight.toFloat() * planets.mars) / planets.earth
                val op4 = (weight.toFloat() * planets.jupiter) / planets.earth
                val op5 = (weight.toFloat() * planets.saturn) / planets.earth
                val op6 = (weight.toFloat() * planets.uranus) / planets.earth
                val op7 = (weight.toFloat() * planets.neptune) / planets.earth
                val op8 = (weight.toFloat() * planets.pluto) / planets.earth
                val op9 = (weight.toFloat() * planets.moon) / planets.earth
                textMercury.text = "%.2f".format(op1) + " kg"
                textVenus.text = "%.2f".format(op2) + " kg"
                textMars.text = "%.2f".format(op3) + " kg"
                textJupiter.text = "%.2f".format(op4) + " kg"
                textSaturn.text = "%.2f".format(op5) + " kg"
                textUranus.text = "%.2f".format(op6) + " kg"
                textNeptune.text = "%.2f".format(op7) + " kg"
                textPluto.text = "%.2f".format(op8) + " kg"
                textMoon.text = "%.2f".format(op9) + " kg"

                editWeight.onEditorAction(EditorInfo.IME_ACTION_DONE)

            }

        }

        return root
    }
}
