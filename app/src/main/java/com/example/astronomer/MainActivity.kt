package com.example.astronomer

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_weight, R.id.nav_eclipses, R.id.nav_config), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        val menu = navView.menu
        val general = menu.findItem(R.id.general)
        val others = menu.findItem(R.id.others)
        val spannable1 = SpannableString(general.title)
        val spannable2 = SpannableString(others.title)
        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingsPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOff: Boolean = appSettingPrefs.getBoolean("NightMode", false)
        val buttonSwitch: MaterialButton = navView.menu.findItem(R.id.switch1).actionView.findViewById(R.id.buttonSwitch)

        val color = getColor(R.color.colorText)
        toolbar.setTitleTextColor(color)
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.colorText), PorterDuff.Mode.SRC_IN)

        spannable1.setSpan(TextAppearanceSpan(this, R.style.TextAppearanceTitle), 0, spannable1.length, 0)
        spannable2.setSpan(TextAppearanceSpan(this, R.style.TextAppearanceTitle), 0, spannable2.length, 0)
        general.title = spannable1
        others.title = spannable2

        val headerView = navView.getHeaderView(0)

        if(isNightModeOff){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            buttonSwitch.text = "OFF"
            headerView.setBackgroundResource(R.drawable.header_image)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            buttonSwitch.text = "ON"
            headerView.setBackgroundResource(R.drawable.header_image_day)
        }

        buttonSwitch.setOnClickListener {

            if(isNightModeOff){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }

        }

        val share = navView.menu.findItem(R.id.share)
        share.setOnMenuItemClickListener {
            onNavigationItemSelected(share)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val share = item.itemId
        val title = getString(R.string.share_option1)
        val extra = getString(R.string.share_option2)
        val link = "https://bit.ly/37lyVzO"

        if (share == R.id.share) {

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "$extra $link")
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, title))
        }
        return false
    }
}
