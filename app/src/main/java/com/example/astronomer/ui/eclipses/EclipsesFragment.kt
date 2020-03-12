package com.example.astronomer.ui.eclipses

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

        val imageButton: ImageButton = root.findViewById(R.id.imageButton)

        imageButton.setOnClickListener {

            val openUrl =  Intent(android.content.Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://www.timeanddate.com/eclipse/2020")
            startActivity(openUrl)

        }

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
}
