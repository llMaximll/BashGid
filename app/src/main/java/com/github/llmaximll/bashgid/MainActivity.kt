package com.github.llmaximll.bashgid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.llmaximll.bashgid.fragments.*

private const val TAG = "MainActivity"

private const val NAME_SHARED_PREFERENCES = "first_launch"

class MainActivity : AppCompatActivity(),
        HomeFragment.Callbacks,
        SettingsFragment.Callbacks,
        HistoryFragment.Callbacks,
        TestFragment.Callbacks,
        TestDetailsFragment.Callbacks,
        ResultTestFragment.Callbacks {

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

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
        (fragment as? OnBackPressedFragment)?.onBackPressed()?.let {
            if (it) {
                super.onBackPressed()
            }
        }
    }

    override fun onHomeFragment(mode: Int, backPressed: Boolean) {
        if (!backPressed) {
            var fragment: Fragment? = null
            when (mode) {
                1 -> {
                    fragment = HistoryFragment.newInstance()
                }
                2 -> {
                    fragment = TestFragment.newInstance()
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
        } else {
            finish()
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

    override fun onHistoryFragment(categoryInt: Int, positionInt: Int, imageView: ImageView, linearLayout: ViewGroup) {
        var fragment: Fragment? = null
        when (categoryInt) {
            0 -> {
                when (positionInt) {
                    0 -> fragment = DetailsFragment.newInstance(0, 0, imageView.transitionName, linearLayout.transitionName)
                    1 -> fragment = DetailsFragment.newInstance(0, 1, imageView.transitionName, linearLayout.transitionName)
                    2 -> fragment = DetailsFragment.newInstance(0, 2, imageView.transitionName, linearLayout.transitionName)
                    3 -> fragment = DetailsFragment.newInstance(0, 3, imageView.transitionName, linearLayout.transitionName)
                    4 -> fragment = DetailsFragment.newInstance(0, 4, imageView.transitionName, linearLayout.transitionName)
                    5 -> fragment = DetailsFragment.newInstance(0, 5, imageView.transitionName, linearLayout.transitionName)
                    6 -> fragment = DetailsFragment.newInstance(0, 6, imageView.transitionName, linearLayout.transitionName)
                    7 -> fragment = DetailsFragment.newInstance(0, 7, imageView.transitionName, linearLayout.transitionName)
                    8 -> fragment = DetailsFragment.newInstance(0, 8, imageView.transitionName, linearLayout.transitionName)
                }
            }
            1 -> {
                when (positionInt) {
                    0 -> fragment = DetailsFragment.newInstance(1, 0, imageView.transitionName, linearLayout.transitionName)
                    1 -> fragment = DetailsFragment.newInstance(1, 1, imageView.transitionName, linearLayout.transitionName)
                    2 -> fragment = DetailsFragment.newInstance(1, 2, imageView.transitionName, linearLayout.transitionName)
                    3 -> fragment = DetailsFragment.newInstance(1, 3, imageView.transitionName, linearLayout.transitionName)
                    4 -> fragment = DetailsFragment.newInstance(1, 4, imageView.transitionName, linearLayout.transitionName)
                    5 -> fragment = DetailsFragment.newInstance(1, 5, imageView.transitionName, linearLayout.transitionName)
                    6 -> fragment = DetailsFragment.newInstance(1, 6, imageView.transitionName, linearLayout.transitionName)
                    7 -> fragment = DetailsFragment.newInstance(1, 7, imageView.transitionName, linearLayout.transitionName)
                    8 -> fragment = DetailsFragment.newInstance(1, 8, imageView.transitionName, linearLayout.transitionName)
                }
            }
            2 -> {
                when (positionInt) {
                    0 -> fragment = DetailsFragment.newInstance(2, 0, imageView.transitionName, linearLayout.transitionName)
                    1 -> fragment = DetailsFragment.newInstance(2, 1, imageView.transitionName, linearLayout.transitionName)
                    2 -> fragment = DetailsFragment.newInstance(2, 2, imageView.transitionName, linearLayout.transitionName)
                    3 -> fragment = DetailsFragment.newInstance(2, 3, imageView.transitionName, linearLayout.transitionName)
                    4 -> fragment = DetailsFragment.newInstance(2, 4, imageView.transitionName, linearLayout.transitionName)
                    5 -> fragment = DetailsFragment.newInstance(2, 5, imageView.transitionName, linearLayout.transitionName)
                    6 -> fragment = DetailsFragment.newInstance(2, 6, imageView.transitionName, linearLayout.transitionName)
                    7 -> fragment = DetailsFragment.newInstance(2, 7, imageView.transitionName, linearLayout.transitionName)
                    8 -> fragment = DetailsFragment.newInstance(2, 8, imageView.transitionName, linearLayout.transitionName)
                }
            }
            else -> {
                Log.i(TAG, "Null")
                fragment = DetailsFragment.newInstance(-1, 0,
                        imageView.transitionName, linearLayout.transitionName)
            }
        }
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addSharedElement(imageView, imageView.transitionName)
            addSharedElement(linearLayout, linearLayout.transitionName)
            add(R.id.container_fragment, fragment!!)
            hide(currentFragment!!)
            addToBackStack(null)
        }
    }

    override fun onTestFragment(position: Int) {
        val fragment = TestDetailsFragment.newInstance(position)
        supportFragmentManager.commit {
            setCustomAnimations(
                    android.R.animator.fade_in,
                    android.R.animator.fade_out,
                    android.R.animator.fade_in,
                    android.R.animator.fade_out
            )
            replace(R.id.container_fragment, fragment)
            addToBackStack(null)
        }
    }

    override fun onTestDetails(position: Int, mode: Int) {
        var fragment: Fragment? = null
        when (mode) {
            0 -> {
                fragment = if (testAnswersList.size >= 20) {
                    ResultTestFragment.newInstance()
                } else {
                    TestDetailsFragment.newInstance(position)
                }
            }
            1 -> {
                fragment = HomeFragment.newInstance()
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
        }
    }

    override fun onResultTest(backPressed: Boolean) {
        if (backPressed) {
            val fragment = HomeFragment.newInstance()
            supportFragmentManager.commit {
                setCustomAnimations(
                        android.R.animator.fade_in,
                        android.R.animator.fade_out,
                        android.R.animator.fade_in,
                        android.R.animator.fade_out
                )
                replace(R.id.container_fragment, fragment)
            }
        }
    }

    companion object {
        val testAnswersList = mutableListOf<Int>()
        var test = -1
        var question = -1
        val testCityList = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
        val testMonumentList = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
        val testReserveList = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
    }
}