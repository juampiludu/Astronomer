package com.example.astronomer.ui.weight

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.astronomer.Planets
import com.example.astronomer.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class WeightFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_weight, container, false)

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

            if (weight == ".") {
                val error = getString(R.string.error1)
                editWeight.error = error
            } else if (weight == "") {
                val error = getString(R.string.error2)
                editWeight.error = error
            } else if (weight.length > 10) {
                val error = getString(R.string.error3)
                editWeight.error = error
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

        val dialogTitle = getString(R.string.alert)
        val dialogText = getString(R.string.alert_text)
        val dialogGo = getString(R.string.go)
        val dialogCancel = getString(R.string.cancel)
        val toast = Toast.makeText(this.context, "Redirecting...", Toast.LENGTH_SHORT)

        imageMercury.setOnClickListener {

            val mercury = getString(R.string.weight3)
            val link = getString(R.string.mercury_link)
            materialAlert(dialogTitle, dialogText, mercury, dialogGo, link, toast, dialogCancel)

        }

        imageVenus.setOnClickListener {

            val venus = getString(R.string.weight4)
            val link = getString(R.string.venus_link)
            materialAlert(dialogTitle, dialogText, venus, dialogGo, link, toast, dialogCancel)

        }

        imageMars.setOnClickListener {

            val mars = getString(R.string.weight5)
            val link = getString(R.string.mars_link)
            materialAlert(dialogTitle, dialogText, mars, dialogGo, link, toast, dialogCancel)

        }

        imageJupiter.setOnClickListener {

            val jupiter = getString(R.string.weight6)
            val link = getString(R.string.jupiter_link)
            materialAlert(dialogTitle, dialogText, jupiter, dialogGo, link, toast, dialogCancel)

        }

        imageSaturn.setOnClickListener {

            val saturn = getString(R.string.weight7)
            val link = getString(R.string.saturn_link)
            materialAlert(dialogTitle, dialogText, saturn, dialogGo, link, toast, dialogCancel)

        }

        imageUranus.setOnClickListener {

            val uranus = getString(R.string.weight8)
            val link = getString(R.string.uranus_link)
            materialAlert(dialogTitle, dialogText, uranus, dialogGo, link, toast, dialogCancel)

        }

        imageNeptune.setOnClickListener {

            val neptune = getString(R.string.weight9)
            val link = getString(R.string.neptune_link)
            materialAlert(dialogTitle, dialogText, neptune, dialogGo, link, toast, dialogCancel)

        }

        imagePluto.setOnClickListener {

            val pluto = getString(R.string.weight10)
            val link = getString(R.string.pluto_link)
            materialAlert(dialogTitle, dialogText, pluto, dialogGo, link, toast, dialogCancel)

        }

        imageMoon.setOnClickListener {

            val moon = getString(R.string.weight11)
            val link = getString(R.string.moon_link)
            materialAlert(dialogTitle, dialogText, moon, dialogGo, link, toast, dialogCancel)

        }

        return root
    }

    private fun materialAlert(title: String, message: String, planet: String, positiveButton: String, link: String, toast: Toast, negativeButton: String) {

        val positiveButton = Html.fromHtml("<font color='#041138'>$positiveButton</font>")
        val negativeButton = Html.fromHtml("<font color='#041138'>$negativeButton</font>")

        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage("$message $planet.")
            .setPositiveButton(positiveButton
            ) { dialog, which ->
                val uri = Uri.parse(link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                toast.show()}
            .setNegativeButton(negativeButton, null)
            .show()

    }

}
