package com.example.astronomer.ui.config

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
import com.example.astronomer.BuildConfig
import com.example.astronomer.R
import com.google.android.material.button.MaterialButton

class ConfigFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_config, container, false)

        val emailButton: MaterialButton = root.findViewById(R.id.emailButton)
        val textView27: TextView = root.findViewById(R.id.textView27)
        val textVersion: TextView = root.findViewById(R.id.textVersion)
        val textView22: TextView = root.findViewById(R.id.textView22)
        val imageView11: ImageView = root.findViewById(R.id.imageView11)
        val textView24: TextView = root.findViewById(R.id.textView24)
        val imageView12: ImageView = root.findViewById(R.id.imageView12)

        //animations

        val lefttoright = AnimationUtils.loadAnimation(context, R.anim.lefttoright)
        textView22.startAnimation(lefttoright)
        imageView11.startAnimation(lefttoright)
        textView24.startAnimation(lefttoright)
        imageView12.startAnimation(lefttoright)
        emailButton.startAnimation(lefttoright)
        textView27.startAnimation(lefttoright)
        textVersion.startAnimation(lefttoright)

        textView27.text = Html.fromHtml(resources.getString(R.string.version_text))
        textVersion.text = BuildConfig.VERSION_NAME

        emailButton.setOnClickListener {

            sendEmail()

        }

        return root
    }

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
            Toast.makeText(this.context, e.message, Toast.LENGTH_LONG).show()
        }

    }

}
