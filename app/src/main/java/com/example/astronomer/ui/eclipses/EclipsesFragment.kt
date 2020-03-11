package com.example.astronomer.ui.eclipses

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class EclipsesFragment : Fragment() {

    private lateinit var eclipsesViewModel: EclipsesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        eclipsesViewModel =
                ViewModelProviders.of(this).get(EclipsesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_eclipses, container, false)

        val imageButton: ImageButton = root.findViewById(R.id.imageButton)

        imageButton.setOnClickListener {

            val openUrl =  Intent(android.content.Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.timeanddate.com/eclipse/2020")
            startActivity(openUrl)

        }

        return root
    }
}
