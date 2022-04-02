package com.codebury.simfocus.dashboard

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.Menu
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.codebury.simfocus.R
import com.codebury.simfocus.fragments.ChatFragment
import com.codebury.simfocus.fragments.HomeFragment
import com.codebury.simfocus.fragments.ProfileFragment
import com.codebury.simfocus.fragments.SearchFragment
import com.simform.custombottomnavigation.Model
import com.simform.custombottomnavigation.SSCustomBottomNavigation
import kotlinx.android.synthetic.main.activity_dashboard.*

class dashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val activeIndex = savedInstanceState?.getInt("activeIndex") ?: 2
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
            Model(R.drawable.dashboard_profile, R.id.navigationProfile, 3, R.string.profile)
        )

        bottomNavigation.apply {
            setMenuItems(models, activeIndex)
            setupWithNavController(navController)
        }

    }
}