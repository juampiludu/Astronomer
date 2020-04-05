package com.example.astronomer.ui.eclipses

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.astronomer.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_eclipses.*

class EclipsesFragment : Fragment() {

    private val url = "https://www.timeanddate.com/eclipse/2020"
    var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_eclipses, container, false)

        val bottomNav: BottomNavigationView = root.findViewById(R.id.bottomNav)
        progressBar = root.findViewById(R.id.progressBar)
        val webView: WebView = root.findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webView.webViewClient = MyWebClient()
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.loadsImagesAutomatically = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webView.loadUrl(url)


        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.back -> goBack()

                R.id.event -> {
                    val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(
                            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            System.currentTimeMillis()
                        )
                        .putExtra(
                            CalendarContract.EXTRA_EVENT_END_TIME,
                            System.currentTimeMillis()
                        )
                    startActivity(intent)
                }

                R.id.next -> goNext()

            }
            return@OnNavigationItemSelectedListener true
        })

        //val icon: Drawable = resources.getDrawable(R.drawable.ic_refresh)
        //icon.setColorFilter(resources.getColor(R.color.colorText), PorterDuff.Mode.SRC_IN)
        setHasOptionsMenu(true)

        return root
    }

    inner class MyWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            progressBar!!.visibility = View.VISIBLE
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar!!.visibility = View.GONE
        }

    }

    private fun goBack() {
        if (webView.canGoBack()) {
            webView.goBack()
            progressBar!!.visibility = View.VISIBLE
        } else {
            Toast.makeText(this.context, "No pages back", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goNext() {
        if (webView.canGoForward()) {
            webView.goForward()
            progressBar!!.visibility = View.VISIBLE
        } else {
            Toast.makeText(this.context, "No pages forward", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val item: MenuItem = menu.findItem(R.id.reload)
        val icon: Drawable = resources.getDrawable(R.drawable.ic_refresh)
        icon.setColorFilter(resources.getColor(R.color.colorText), PorterDuff.Mode.SRC_IN)
        item.icon = icon

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.reload_button, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.reload -> {
                webView.reload()
                progressBar!!.visibility = View.VISIBLE
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
