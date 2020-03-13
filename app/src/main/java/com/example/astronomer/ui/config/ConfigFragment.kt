package com.example.astronomer.ui.config

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.BuildConfig
import com.example.astronomer.R

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

        val emailButton: ImageButton = root.findViewById(R.id.emailButton)
        val textView27: TextView = root.findViewById(R.id.textView27)
        val textVersion: TextView = root.findViewById(R.id.textVersion)

        textView27.text = Html.fromHtml(resources.getString(R.string.version_text))
        textVersion.text = BuildConfig.VERSION_NAME

        emailButton.setOnClickListener {

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                data = Uri.parse("mailto:")
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "<TYPE IN 'to: juanzakka@gmail.com'>")
                putExtra(Intent.EXTRA_SUBJECT, "<atr-support>")
            }
            if (intent.resolveActivity(activity!!.packageManager) != null) {
                intent.setPackage("com.google.android.gm")
                startActivity(intent)
            } else {
                Log.d(TAG, "No app available to send email.")
            }

        }

        return root
    }
}
