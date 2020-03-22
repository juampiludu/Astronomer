package com.example.astronomer.ui.weight

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.Planets
import com.example.astronomer.R
import com.example.astronomer.Planets.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val imageMercury: ImageView = root.findViewById(R.id.imageView2)
        val imageVenus: ImageView = root.findViewById(R.id.imageView3)
        val imageMars: ImageView = root.findViewById(R.id.imageView4)
        val imageJupiter: ImageView = root.findViewById(R.id.imageView5)
        val imageSaturn: ImageView = root.findViewById(R.id.imageView6)
        val imageUranus: ImageView = root.findViewById(R.id.imageView7)
        val imageNeptune: ImageView = root.findViewById(R.id.imageView8)
        val imagePluto: ImageView = root.findViewById(R.id.imageView9)
        val imageMoon: ImageView = root.findViewById(R.id.imageView10)
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

        val dialog_title = getString(R.string.alert)
        val dialog_text = getString(R.string.alert_text)
        val dialog_go = getString(R.string.go)
        val dialog_cancel = getString(R.string.cancel)
        val toast = Toast.makeText(this.context, "Redirecting...", Toast.LENGTH_SHORT)

        imageMercury.setOnClickListener {

            val mercury = getString(R.string.weight3)
            val link = getString(R.string.mercury_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $mercury.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageVenus.setOnClickListener {

            val venus = getString(R.string.weight4)
            val link = getString(R.string.venus_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $venus.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageMars.setOnClickListener {

            val mars = getString(R.string.weight5)
            val link = getString(R.string.mars_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $mars.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageJupiter.setOnClickListener {

            val jupiter = getString(R.string.weight6)
            val link = getString(R.string.jupiter_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $jupiter.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageSaturn.setOnClickListener {

            val saturn = getString(R.string.weight7)
            val link = getString(R.string.saturn_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $saturn.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageUranus.setOnClickListener {

            val uranus = getString(R.string.weight8)
            val link = getString(R.string.uranus_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $uranus.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageNeptune.setOnClickListener {

            val neptune = getString(R.string.weight9)
            val link = getString(R.string.neptune_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $neptune.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imagePluto.setOnClickListener {

            val pluto = getString(R.string.weight10)
            val link = getString(R.string.pluto_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $pluto.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        imageMoon.setOnClickListener {

            val moon = getString(R.string.weight11)
            val link = getString(R.string.moon_link)
            MaterialAlertDialogBuilder(context)
                .setTitle(dialog_title)
                .setMessage("$dialog_text $moon.")
                .setPositiveButton(dialog_go
                ) { dialog, which ->
                    val uri = Uri.parse(link)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    toast.show()}
                .setNegativeButton(dialog_cancel, null)
                .show()

        }

        return root
    }

}
