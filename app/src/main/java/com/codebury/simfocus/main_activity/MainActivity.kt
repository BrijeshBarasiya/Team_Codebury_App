package com.codebury.simfocus.main_activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.codebury.simfocus.R
import com.simform.custombottomnavigation.Model
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activeIndex = savedInstanceState?.getInt("activeIndex") ?: 0
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigationHome,
                R.id.navigationChat,
                R.id.navigationSearch,
                R.id.navigationProfile
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)

        var models = arrayOf(
            Model(R.drawable.dashboard_home, R.id.navigationHome, 0, R.string.home),
            Model(R.drawable.dashboard_chat, R.id.navigationChat, 1, R.string.chat),
            Model(R.drawable.dashboard_search, R.id.navigationSearch, 2, R.string.search),
            Model(R.drawable.ic_person, R.id.navigationProfile, 3, R.string.profile)
        )

        bottomNavigation.apply {
            setMenuItems(models, activeIndex)
            setupWithNavController(navController)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.appbar_add_post_menu, menu)
        return true
    }

}