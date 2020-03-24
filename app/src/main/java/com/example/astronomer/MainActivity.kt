package com.example.astronomer

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.shreyaspatil.material.navigationview.MaterialNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: MaterialNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_weight, R.id.nav_eclipses, R.id.nav_config), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val menu = navView.menu
        val general = menu.findItem(R.id.general)
        val others = menu.findItem(R.id.others)
        val spannable1 = SpannableString(general.title)
        val spannable2 = SpannableString(others.title)

        spannable1.setSpan(TextAppearanceSpan(this, R.style.TextAppearanceTitle), 0, spannable1.length, 0)
        spannable2.setSpan(TextAppearanceSpan(this, R.style.TextAppearanceTitle), 0, spannable2.length, 0)
        general.title = spannable1
        others.title = spannable2

        val buttonSwitch: Button = navView.menu.findItem(R.id.switch1).actionView.findViewById(R.id.buttonSwitch)

        buttonSwitch.setOnClickListener(View.OnClickListener {

            Toast.makeText(applicationContext, "Pressed", Toast.LENGTH_SHORT).show()

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
