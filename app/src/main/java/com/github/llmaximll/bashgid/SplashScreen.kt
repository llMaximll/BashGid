package com.github.llmaximll.bashgid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

private const val NAME_SHARED_PREFERENCES = "first_launch"

private const val TAG = "SplashScreen"

class SplashScreen : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        viewPager = findViewById(R.id.view_pager)

        viewPager.adapter = PagerAdapter()
    }

    inner class PagerHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

            fun bind(mode: Int) {
                when (mode) {
                    3 -> {
                        val button: Button = itemView.findViewById(R.id.button)
                        button.setOnClickListener {
                            Log.i(TAG, "button.setOnClickListener")
                            val sharedPreference =
                                getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
                            val editor = sharedPreference.edit()
                            editor.putBoolean(NAME_SHARED_PREFERENCES, true)
                            editor.apply()
                            val intent = Intent(this@SplashScreen, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }

    inner class PagerAdapter : RecyclerView.Adapter<PagerHolder>() {

        private var count = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {
            var view: View? = null
            when (count) {
                0 -> {
                    view = layoutInflater.inflate(
                        R.layout.fragment_splash_screen_language,
                        parent,
                        false
                    )
                    count++
                }
                1 -> {
                    view = layoutInflater.inflate(
                        R.layout.fragment_splash_screen_history,
                        parent,
                        false
                    )
                    count++
                }
                2 -> {
                    view = layoutInflater.inflate(
                        R.layout.fragment_splash_screen_test,
                        parent,
                        false
                    )
                    count++
                }
                3 -> {
                    view = layoutInflater.inflate(
                        R.layout.fragment_splash_screen_share,
                        parent,
                        false
                    )
                }
            }
            return PagerHolder(view!!)
        }

        override fun onBindViewHolder(holder: PagerHolder, position: Int) {
            when (count) {
                3 -> {
                    holder.bind(3)
                }
            }
        }

        override fun getItemCount(): Int = 4
    }
}