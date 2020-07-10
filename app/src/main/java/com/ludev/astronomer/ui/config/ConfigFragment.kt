package com.ludev.astronomer.ui.config

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.ludev.astronomer.BuildConfig
import com.ludev.astronomer.MainActivity
import com.ludev.astronomer.R

class ConfigFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_config, container, false)

        //getting and setting the layout content

        val emailButton: MaterialButton = root.findViewById(R.id.emailButton)
        val textView27: TextView = root.findViewById(R.id.textView27)
        val textVersion: TextView = root.findViewById(R.id.textVersion)
        val textView22: TextView = root.findViewById(R.id.textView22)
        val imageView11: ImageView = root.findViewById(R.id.imageView11)
        val textView24: TextView = root.findViewById(R.id.textView24)
        val imageView12: ImageView = root.findViewById(R.id.imageView12)
        val langTextView: TextView = root.findViewById(R.id.langTextView)
        val langButton: MaterialButton = root.findViewById(R.id.langButton)

        //animations

        val lefttoright = AnimationUtils.loadAnimation(context, R.anim.lefttoright)
        textView22.startAnimation(lefttoright)
        imageView11.startAnimation(lefttoright)
        textView24.startAnimation(lefttoright)
        imageView12.startAnimation(lefttoright)
        emailButton.startAnimation(lefttoright)
        textView27.startAnimation(lefttoright)
        textVersion.startAnimation(lefttoright)
        langTextView.startAnimation(lefttoright)
        langButton.startAnimation(lefttoright)

        textView27.text = Html.fromHtml(resources.getString(R.string.version_text))
        textVersion.text = BuildConfig.VERSION_NAME

        emailButton.setOnClickListener {

            sendEmail()

        }

        // setting button and functionality of language selector

        var checkedItem = 0
        val text1 = resources.getString(R.string.english)
        val text2 = resources.getString(R.string.spanish)
        val title = resources.getString(R.string.langTitle)
        val posButton = resources.getString(R.string.langSelect)
        val cancel = resources.getString(R.string.cancel)
        val appLang = resources.configuration.locale.language
        if (appLang == "en") {
            langButton.text = text1
        }
        else if (appLang == "es") {
            langButton.text = text2
        }
        else {
            langButton.text = text1
        }

        langButton.setOnClickListener {

            val langItems = arrayOf(text1, text2)
            var selectedItem = langButton.text

            val alertDialogBuilder = AlertDialog.Builder(this.context)

            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setCancelable(true)

            val positiveButton = Html.fromHtml("<font color='#000000'>$posButton</font>")
            val neutralButton = Html.fromHtml("<font color='#000000'>$cancel</font>")

            if (langButton.text == text1) {
                checkedItem = 0
            } else if (langButton.text == text2) {
                checkedItem = 1
            }

            alertDialogBuilder.setSingleChoiceItems(langItems, checkedItem) {dialog, which ->
                when(which) {
                    which -> {
                        selectedItem = langItems[which]
                    }
                }
            }

            alertDialogBuilder.setPositiveButton(positiveButton){_, _ ->
                val lang = selectedItem
                langButton.text = lang
                val intent = Intent(activity, MainActivity::class.java)
                if (lang == text1) {
                    // en
                    intent.putExtra("lang", "en")
                    startActivity(intent)
                    (activity as MainActivity).finish()
                } else if (lang == text2) {
                    // es
                    intent.putExtra("lang", "es")
                    startActivity(intent)
                    (activity as MainActivity).finish()
                }
            }

            alertDialogBuilder.setNeutralButton(neutralButton) {_, _ ->
                // neutral button
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()

        }

        return root
    }

    // email function

    private fun sendEmail() {

        val mailto = "lu.dev.spprt@gmail.com"
        val emailClient = getString(R.string.email_option)
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("$mailto"))
            putExtra(Intent.EXTRA_SUBJECT, "<astronomer-support>")
        }
        try {
            startActivity(Intent.createChooser(intent, emailClient))
        } catch (e: Exception) {
            val toast = Toast.makeText(this.context, e.message, Toast.LENGTH_LONG)
            (activity as MainActivity).customToast(toast)
        }

    }

}
