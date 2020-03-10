package com.example.astronomer.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.R

class WeightFragment : Fragment() {

    private lateinit var weightViewModel: WeightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        weightViewModel =
                ViewModelProviders.of(this).get(WeightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weight, container, false)


        val editWeight: EditText = root.findViewById(R.id.editWeight)
        val buttonWeight: Button = root.findViewById(R.id.buttonWeight)
        val textMars: TextView = root.findViewById(R.id.textMars)

        buttonWeight.setOnClickListener {

            val weight = editWeight.text.toString()
            weight.toFloat()
            val op = (weight.toFloat()*3.711) / 9.807
            textMars.text = "$op kg"

        }

        return root
    }
}
