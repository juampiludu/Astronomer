package com.example.astronomer.ui.eclipses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.R

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

        val webView: WebView = root.findViewById(R.id.webView)
        webView.loadUrl("file:///android_asset/myhtml.html")

        val webView2: WebView = root.findViewById(R.id.webView2)
        webView2.loadUrl("file:///android_asset/myhtml2.html")

        return root
    }
}
