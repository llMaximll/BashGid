package com.github.llmaximll.bashgid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.llmaximll.bashgid.fragments.*

private const val TAG = "MainActivity"

private const val NAME_SHARED_PREFERENCES = "first_launch"

class MainActivity : AppCompatActivity(),
    HomeFragment.Callbacks,
    SettingsFragment.Callbacks,
    HistoryFragment.Callbacks {

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
                fragment = HistoryFragment.newInstance()
            }
            2 -> {

            }
            3 -> {
                fragment = SettingsFragment.newInstance()
            }
        }
        supportFragmentManager.commit {
            setCustomAnimations(
                    android.R.animator.fade_in,
                    android.R.animator.fade_out,
                    android.R.animator.fade_in,
                    android.R.animator.fade_out
            )
            replace(R.id.container_fragment, fragment!!)
            addToBackStack(null)
        }
    }

    override fun onSettingsFragment(mode: Int) {
        var fragment: Fragment? = null
        when (mode) {
            2 -> {
                fragment = AboutUsFragment.newInstance()
            }
            3 -> {
                fragment = DonateFragment.newInstance()
            }
        }
        supportFragmentManager.commit {
            setCustomAnimations(
                    android.R.animator.fade_in,
                    android.R.animator.fade_out,
                    android.R.animator.fade_in,
                    android.R.animator.fade_out
            )
            replace(R.id.container_fragment, fragment!!)
            addToBackStack(null)
        }
    }

    override fun onHistoryFragment(category: String, position: Int, imageView: ImageView, linearLayout: ViewGroup) {
        var fragment: Fragment? = null
        when (category) {
            "Cities" -> {
                when (position) {
                    0 -> {
                        Log.i(TAG, "Cities")
                        fragment = DetailsFragment.newInstance("Monuments", 1, imageView.transitionName,
                                linearLayout.transitionName)
                    }
                }
            }
            "Monuments" -> {
                when (position) {
                    0 -> {
                        Log.i(TAG, "Monuments")
                        fragment = DetailsFragment.newInstance("Monuments", 1, imageView.transitionName,
                                linearLayout.transitionName)
                    }
                }
            }
            "Reserves" -> {
                when (position) {
                    0 -> {
                        Log.i(TAG, "Monuments")
                        fragment = DetailsFragment.newInstance("Monuments", 1, imageView.transitionName,
                                linearLayout.transitionName)
                    }
                }
            }
        }
        supportFragmentManager.commit {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
            hide(currentFragment!!)
            add(R.id.container_fragment, fragment!!)
            addSharedElement(imageView, imageView.transitionName)
            addSharedElement(linearLayout, linearLayout.transitionName)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}