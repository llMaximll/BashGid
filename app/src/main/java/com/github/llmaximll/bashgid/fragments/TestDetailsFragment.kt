package com.github.llmaximll.bashgid.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.MainActivity
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

private const val KEY_POSITION = "position"

class TestDetailsFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onTestDetails(position: Int, mode: Int)
    }

    private lateinit var questionTextView: TextView
    private lateinit var frameLayout1: ViewGroup
    private lateinit var frameLayout2: ViewGroup
    private lateinit var frameLayout3: ViewGroup
    private lateinit var frameLayout4: ViewGroup

    private var callbacks: Callbacks? = null
    private var position = -1
    private var backPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getSerializable(KEY_POSITION) as Int
        MainActivity.question++
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test_details, container, false)

        questionTextView = view.findViewById(R.id.question_text_view)
        frameLayout1 = view.findViewById(R.id.f_l_1)
        frameLayout2 = view.findViewById(R.id.f_l_2)
        frameLayout3 = view.findViewById(R.id.f_l_3)
        frameLayout4 = view.findViewById(R.id.f_l_4)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (position) {
            0 -> {
                MainActivity.test = 0
            }
            1 -> {
                MainActivity.test = 1
            }
            2 -> {
                MainActivity.test = 2
            }
            3 -> {
                MainActivity.test = 3
            }
        }
    }

    override fun onStart() {
        super.onStart()
        frameLayout1.touchListener(1)
        frameLayout2.touchListener(2)
        frameLayout3.touchListener(3)
        frameLayout4.touchListener(4)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        val timer = BackPressedTimer()
        if (!backPressed) {
            Toast.makeText(
                    requireContext(),
                    "Вы уверены?",
                    Toast.LENGTH_LONG
            ).show()
            backPressed = true
            timer.start()
        } else {
            MainActivity.testAnswersList.clear()
            MainActivity.test = -1
            MainActivity.question = 0
            callbacks?.onTestDetails(-1, 1)
        }
        return false
    }

    private inner class BackPressedTimer : CountDownTimer(2000L, 2000L) {
        override fun onTick(time: Long) {
        }
        override fun onFinish() {
            backPressed = false
        }
    }

    private fun View.touchListener(button: Int) {
        Toast.makeText(
                requireContext(),
                "${MainActivity.testAnswersList}",
                Toast.LENGTH_LONG
        ).show()
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
                            MainActivity.testAnswersList.add(1)
                            callbacks?.onTestDetails(position, 0)
                        }
                        2 -> {
                            animateView(it, true)
                            MainActivity.testAnswersList.add(2)
                            callbacks?.onTestDetails(position, 0)
                        }
                        3 -> {
                            animateView(it, true)
                            MainActivity.testAnswersList.add(3)
                            callbacks?.onTestDetails(position, 0)
                        }
                        4 -> {
                            animateView(it, true)
                            MainActivity.testAnswersList.add(4)
                            callbacks?.onTestDetails(position, 0)
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
        fun newInstance(position: Int): TestDetailsFragment {
            val args = Bundle().apply {
                putSerializable(KEY_POSITION, position)
            }
            return TestDetailsFragment().apply {
                arguments = args
            }
        }
    }
}