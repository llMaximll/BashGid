package com.github.llmaximll.bashgid.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

private const val TAG = "SettingsFragment"

class SettingsFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onSettingsFragment(mode: Int)
    }

    private lateinit var languageFrameLayout: ViewGroup
    private lateinit var aboutUsFrameLayout: ViewGroup
    private lateinit var donateFrameLayout: ViewGroup
    private lateinit var flagsLinearLayout: LinearLayout
    private lateinit var russiaImageView: ImageView
    private lateinit var bashkortostanImageView: ImageView
    private lateinit var englandImageView: ImageView

    private var callbacks: Callbacks? = null
    private var flagVisible = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        languageFrameLayout = view.findViewById(R.id.language_frame_layout)
        aboutUsFrameLayout = view.findViewById(R.id.about_us_frame_layout)
        donateFrameLayout = view.findViewById(R.id.donate_frame_layout)
        flagsLinearLayout = view.findViewById(R.id.flags_linear_layout)
        russiaImageView = view.findViewById(R.id.russia_image_view)
        bashkortostanImageView = view.findViewById(R.id.bashkortostan_image_view)
        englandImageView = view.findViewById(R.id.england_image_view)

        return view
    }

    override fun onStart() {
        super.onStart()
        languageFrameLayout.touchListener(1)
        aboutUsFrameLayout.touchListener(2)
        donateFrameLayout.touchListener(3)
        russiaImageView.touchListener(4)
        bashkortostanImageView.touchListener(5)
        englandImageView.touchListener(6)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        return true
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
                            if (!flagVisible) {
                                animateFlags(false)
                            } else {
                                animateFlags(true)
                            }
                        }
                        2 -> {
                            animateView(it, true)
                            callbacks?.onSettingsFragment(2)
                        }
                        3 -> {
                            animateView(it, true)
                            callbacks?.onSettingsFragment(3)
                        }
                        4 -> {
                            animateView(it, true)
                            if (flagVisible) {
                                toast("Русский")
                            }
                        }
                        5 -> {
                            animateView(it, true)
                            if (flagVisible) {
                                toast("Башкирский")
                            }
                        }
                        6 -> {
                            animateView(it, true)
                            if (flagVisible) {
                                toast("Английский")
                            }
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

    private fun animateFlags(reverse: Boolean) {
        if (!reverse) {
            val animatorA = ObjectAnimator.ofFloat(russiaImageView, "alpha", 1.0f)
            val animatorY = ObjectAnimator.ofFloat(russiaImageView, "translationY", -20f)
            AnimatorSet().apply {
                playTogether(animatorA, animatorY)
                duration = 500
                start()
            }
            val animatorA2 = ObjectAnimator.ofFloat(bashkortostanImageView, "alpha", 1.0f)
            val animatorY2 = ObjectAnimator.ofFloat(bashkortostanImageView, "translationY", -20f)
            AnimatorSet().apply {
                playTogether(animatorA2, animatorY2)
                startDelay = 100
                duration = 500
                start()
            }
            val animatorA3 = ObjectAnimator.ofFloat(englandImageView, "alpha", 1.0f)
            val animatorY3 = ObjectAnimator.ofFloat(englandImageView, "translationY", -20f)
            AnimatorSet().apply {
                playTogether(animatorA3, animatorY3)
                startDelay = 200
                duration = 500
                start()
            }
            flagVisible = true
        } else {
            val animatorA = ObjectAnimator.ofFloat(russiaImageView, "alpha", 0.0f)
            val animatorY = ObjectAnimator.ofFloat(russiaImageView, "translationY", 20f)
            AnimatorSet().apply {
                playTogether(animatorA, animatorY)
                duration = 500
                start()
            }
            val animatorA2 = ObjectAnimator.ofFloat(bashkortostanImageView, "alpha", 0.0f)
            val animatorY2 = ObjectAnimator.ofFloat(bashkortostanImageView, "translationY", 20f)
            AnimatorSet().apply {
                playTogether(animatorA2, animatorY2)
                startDelay = 100
                duration = 500
                start()
            }
            val animatorA3 = ObjectAnimator.ofFloat(englandImageView, "alpha", 0.0f)
            val animatorY3 = ObjectAnimator.ofFloat(englandImageView, "translationY", 20f)
            AnimatorSet().apply {
                playTogether(animatorA3, animatorY3)
                startDelay = 200
                duration = 500
                start()
            }
            flagVisible = false
        }
    }

    private fun toast(message: String) {
        val toast = Toast.makeText(
                requireContext(),
                message,
                Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}