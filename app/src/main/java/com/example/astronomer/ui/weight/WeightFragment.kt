package com.example.astronomer.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.Planets
import com.example.astronomer.R
import com.example.astronomer.Planets.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class WeightFragment : Fragment() {

    private lateinit var weightViewModel: WeightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        weightViewModel = ViewModelProviders.of(this).get(WeightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weight, container, false)

        val fragmentManager: FragmentManager

        val editWeight: TextInputLayout = root.findViewById(R.id.editWeight)
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

            val weight = editWeight.editText?.text.toString()
            if (weight.equals(".")) {
                editWeight.error = "The input cannot have only a point"
            } else if (weight.equals("")) {
                editWeight.error = "Enter your weight, the input cannot be empty"
            } else if (weight.length > 10) {
                editWeight.error = "The input cannot be longer than 10 digits"
            } else {

                val wght = weight.toFloat()
                val op1 = (wght * planets.mercury) / planets.earth
                val op2 = (wght * planets.venus) / planets.earth
                val op3 = (wght * planets.mars) / planets.earth
                val op4 = (wght * planets.jupiter) / planets.earth
                val op5 = (wght * planets.saturn) / planets.earth
                val op6 = (wght * planets.uranus) / planets.earth
                val op7 = (wght * planets.neptune) / planets.earth
                val op8 = (wght * planets.pluto) / planets.earth
                val op9 = (wght * planets.moon) / planets.earth
                textMercury.text = "%.2f".format(op1) + " kg"
                textVenus.text = "%.2f".format(op2) + " kg"
                textMars.text = "%.2f".format(op3) + " kg"
                textJupiter.text = "%.2f".format(op4) + " kg"
                textSaturn.text = "%.2f".format(op5) + " kg"
                textUranus.text = "%.2f".format(op6) + " kg"
                textNeptune.text = "%.2f".format(op7) + " kg"
                textPluto.text = "%.2f".format(op8) + " kg"
                textMoon.text = "%.2f".format(op9) + " kg"

                editWeight.error = null



            }

        }

        return root
    }

}
