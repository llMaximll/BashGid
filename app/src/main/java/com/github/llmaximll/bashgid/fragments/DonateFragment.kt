package com.github.llmaximll.bashgid.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

class DonateFragment : Fragment(), OnBackPressedFragment {

    private lateinit var coffeeFrameLayout: ViewGroup
    private lateinit var fastFoodFrameLayout: ViewGroup
    private lateinit var soupFrameLayout: ViewGroup
    private lateinit var dinnerFrameLayout: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        coffeeFrameLayout = view.findViewById(R.id.frame_layout)
        fastFoodFrameLayout = view.findViewById(R.id.frame_layout_2)
        soupFrameLayout = view.findViewById(R.id.frame_layout_3)
        dinnerFrameLayout = view.findViewById(R.id.frame_layout_4)

        return view
    }

    override fun onStart() {
        super.onStart()
        coffeeFrameLayout.touchListener(1)
        fastFoodFrameLayout.touchListener(2)
        soupFrameLayout.touchListener(3)
        dinnerFrameLayout.touchListener(4)
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    private fun View.touchListener(button: Int) {
        this.setOnTouchListener { it, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateView(it, false)
                }
                MotionEvent.ACTION_CANCEL -> {
                    animateView(it, true)
                }
                MotionEvent.ACTION_UP -> {
                    when (button) {
                        1 -> {
                            animateView(it, true)
                        }
                        2 -> {
                            animateView(it, true)
                        }
                        3 -> {
                            animateView(it, true)
                        }
                        4 -> {
                            animateView(it, true)
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

    companion object {
        fun newInstance(): DonateFragment {
            return DonateFragment()
        }
    }
}