package com.example.astronomer.ui.eclipses

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.astronomer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        Toast.makeText(this.context, "Press + to add an event", Toast.LENGTH_LONG).show()

        val webView: WebView = root.findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.timeanddate.com/eclipse/2020")

        val webSettings: WebSettings = webView.settings
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.loadsImagesAutomatically = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.javaScriptEnabled = true

        val fab: FloatingActionButton = root.findViewById(R.id.fab)

        fab.setOnClickListener { view ->

            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + (60*60*1000))
            startActivity(intent)

        }

        return root

    }

    //fun canGoBack(): Boolean {
      //  return webView.canGoBack()
    //}

    //fun goBack() {
      //  webView.goBack()
    //}

}
