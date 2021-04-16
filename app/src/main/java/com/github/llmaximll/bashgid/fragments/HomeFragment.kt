package com.github.llmaximll.bashgid.fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

class HomeFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onHomeFragment(mode: Int, backPressed: Boolean)
    }

    private lateinit var historyFrameLayout: ViewGroup
    private lateinit var testFrameLayout: ViewGroup
    private lateinit var settingsFrameLayout: ViewGroup

    private var callbacks: Callbacks? = null
    private var backPressed = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        historyFrameLayout = view.findViewById(R.id.frame_layout)
        testFrameLayout = view.findViewById(R.id.frame_layout_2)
        settingsFrameLayout = view.findViewById(R.id.frame_layout_3)

        return view
    }

    override fun onStart() {
        super.onStart()
        historyFrameLayout.touchListener(1)
        testFrameLayout.touchListener(2)
        settingsFrameLayout.touchListener(3)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        callbacks?.onHomeFragment(-1, true)
        return false
    }

    private inner class BackPressedTimer : CountDownTimer(2000L, 2000L) {
        override fun onTick(time: Long) {
        }
        override fun onFinish() {
            backPressed = false
        }
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
                            callbacks?.onHomeFragment(1, false)
                        }
                        2 -> {
                            animateView(it, true)
                            callbacks?.onHomeFragment(2, false)
                        }
                        3 -> {
                            animateView(it, true)
                            callbacks?.onHomeFragment(3, false)
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
            val animatorX = ObjectAnimator.ofFloat(
                view,
                "scaleX",
                0.95f
            )
            animatorX.duration = 150
            animatorX.start()
            val animatorY = ObjectAnimator.ofFloat(
                view,
                "scaleY",
                0.95f
            )
            animatorY.duration = 150
            animatorY.start()
        } else {
            val animatorX = ObjectAnimator.ofFloat(
                view,
                "scaleX",
                1.0f
            )
            animatorX.duration = 150
            animatorX.start()
            val animatorY = ObjectAnimator.ofFloat(
                view,
                "scaleY",
                1.0f
            )
            animatorY.duration = 150
            animatorY.start()
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}