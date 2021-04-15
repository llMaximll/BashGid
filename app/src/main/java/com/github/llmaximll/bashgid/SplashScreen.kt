package com.github.llmaximll.bashgid

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar

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

    private fun View.touchListener(mode: Int) {
        this.setOnTouchListener { it, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateView(it, false)
                }
                MotionEvent.ACTION_CANCEL -> {
                    animateView(it, true)
                }
                MotionEvent.ACTION_UP -> {
                    when (mode) {
                        1 -> {
                            animateView(it, true)
                            snackBar("Выбран русский язык")
                        }
                        2 -> {
                            animateView(it, true)
                            snackBar("Выбран башкирский язык")
                        }
                        3 -> {
                            animateView(it, true)
                            snackBar("Выбран английский язык")
                        }
                    }
                    it.performClick()
                }
            }
            true
        }
    }

    private fun animateView(view: View, reverse: Boolean) {
        if (!reverse) {
            val animatorX = ObjectAnimator.ofFloat(view, "scaleX", 0.95f)
            val animatorY = ObjectAnimator.ofFloat(view, "scaleY", 0.95f)
            AnimatorSet().apply {
                playTogether(animatorX, animatorY)
                duration = 150
                start()
            }
        } else {
            val animatorX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f)
            val animatorY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f)
            AnimatorSet().apply {
                playTogether(animatorX, animatorY)
                duration = 150
                start()
            }
        }
    }

    private fun snackBar(message: String) {
        val snackBar = Snackbar.make(
                this.findViewById(R.id.russia),
                message,
                Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }

    inner class PagerHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

            fun bind(mode: Int) {
                when (mode) {
                    0 -> {
                        val russiaButton: ImageView = itemView.findViewById(R.id.russia)
                        val bashButton: ImageView = itemView.findViewById(R.id.bashkortostan)
                        val englandButton: ImageView = itemView.findViewById(R.id.united_kingdom)
                        russiaButton.touchListener(1)
                        bashButton.touchListener(2)
                        englandButton.touchListener(3)
                    }
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
            when (position) {
                0 -> {
                    holder.bind(0)
                }
                3 -> holder.bind(3)
            }
        }

        override fun getItemCount(): Int = 4
    }
}