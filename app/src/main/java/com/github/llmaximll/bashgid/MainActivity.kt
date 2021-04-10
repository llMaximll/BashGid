package com.github.llmaximll.bashgid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.llmaximll.bashgid.fragments.AboutUsFragment
import com.github.llmaximll.bashgid.fragments.DonateFragment
import com.github.llmaximll.bashgid.fragments.HomeFragment
import com.github.llmaximll.bashgid.fragments.SettingsFragment

private const val TAG = "MainActivity"

private const val NAME_SHARED_PREFERENCES = "first_launch"

class MainActivity : AppCompatActivity(),
    HomeFragment.Callbacks,
    SettingsFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =
            getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val firstLaunch = sharedPreference.getBoolean(NAME_SHARED_PREFERENCES, false)
        if (!firstLaunch) {
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        } else {
            val currentFragment = supportFragmentManager
                .findFragmentById(R.id.container_fragment)
            if (currentFragment == null) {
                val fragment = HomeFragment.newInstance()
                supportFragmentManager.commit {
                    add(R.id.container_fragment, fragment)
                }
            }
        }
    }

    override fun onHomeFragment(mode: Int) {
        var fragment: Fragment? = null
        when (mode) {
            1 -> {

            }
            2 -> {

            }
            3 -> {
                fragment = SettingsFragment.newInstance()
            }
        }
        supportFragmentManager.commit {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            replace(R.id.container_fragment, fragment!!)
            addToBackStack(null)
        }
    }

    override fun onSettingsFragment(mode: Int) {
        var fragment: Fragment? = null
        when (mode) {
            2 -> {
                Log.i(TAG, "AboutUsFragment")
                fragment = AboutUsFragment.newInstance()
            }
            3 -> {
                fragment = DonateFragment.newInstance()
            }
        }
        supportFragmentManager.commit {
            replace(R.id.container_fragment, fragment!!)
            addToBackStack(null)
        }
    }
}