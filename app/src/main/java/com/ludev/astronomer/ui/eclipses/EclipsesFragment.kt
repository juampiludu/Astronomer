package com.ludev.astronomer.ui.eclipses

import android.content.Intent
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.Fragment
import com.ludev.astronomer.R
import kotlinx.android.synthetic.main.fragment_eclipses.*
import java.util.*

class EclipsesFragment : Fragment() {

    private val year = Calendar.getInstance().get(Calendar.YEAR)
    var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_eclipses, container, false)

        progressBar = root.findViewById(R.id.progressBar)
        val webView: WebView = root.findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webView.webViewClient = MyWebClient()
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.loadsImagesAutomatically = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.javaScriptEnabled = true
        webView.loadUrl(getString(R.string.eclipses_link)+year.toString())

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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val item: MenuItem = menu.findItem(R.id.reload)
        val item2: MenuItem = menu.findItem(R.id.add_event)
        val icon: Drawable = resources.getDrawable(R.drawable.ic_refresh)
        val icon2: Drawable = resources.getDrawable(R.drawable.ic_event)
        icon.setColorFilter(resources.getColor(R.color.colorText), PorterDuff.Mode.SRC_IN)
        icon2.setColorFilter(resources.getColor(R.color.colorText), PorterDuff.Mode.SRC_IN)
        item.icon = icon
        item2.icon = icon2

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.eclipses_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.reload -> {
                webView.reload()
                progressBar!!.visibility = View.VISIBLE
            }

            R.id.add_event -> {
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

        }
        return super.onOptionsItemSelected(item)
    }
}
