package com.example.astronomer.ui.config

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.BuildConfig
import com.example.astronomer.R
import com.google.android.material.button.MaterialButton

class ConfigFragment : Fragment() {

    private lateinit var configViewModel: ConfigViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        configViewModel =
                ViewModelProviders.of(this).get(ConfigViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_config, container, false)

        val emailButton: MaterialButton = root.findViewById(R.id.emailButton)
        val textView27: TextView = root.findViewById(R.id.textView27)
        val textVersion: TextView = root.findViewById(R.id.textVersion)

        textView27.text = Html.fromHtml(resources.getString(R.string.version_text))
        textVersion.text = BuildConfig.VERSION_NAME

        emailButton.setOnClickListener {

            sendEmail()

        }

        return root
    }

    private fun sendEmail() {

        val mailto = "lu.dev.spprt@gmail.com"
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("$mailto"))
            putExtra(Intent.EXTRA_SUBJECT, "<astronomer-support>")
        }
        try {
            startActivity(Intent.createChooser(intent, "Choose Email Client"))
        } catch (e: Exception) {
            Toast.makeText(this.context, e.message, Toast.LENGTH_LONG).show()
        }

    }

}
