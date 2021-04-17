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
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView

    private var callbacks: Callbacks? = null
    private var position = -1
    private var backPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getSerializable(KEY_POSITION) as Int
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
        textView1 = view.findViewById(R.id.t_v_1)
        textView2 = view.findViewById(R.id.t_v_2)
        textView3 = view.findViewById(R.id.t_v_3)
        textView4= view.findViewById(R.id.t_v_4)

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
                updateUI(questionTextView, 1)
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

    override fun onStop() {
        super.onStop()
        MainActivity.question++
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

    private fun updateUI(questionTextView: TextView, position: Int) {
        when (position) {
            1 -> {
                when (MainActivity.question) {
                    0 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_1)
                        textView1.text = resources.getString(R.string.answer_monuments_1_1)
                        textView2.text = resources.getString(R.string.answer_monuments_1_2)
                        textView3.text = resources.getString(R.string.answer_monuments_1_3)
                        textView4.text = resources.getString(R.string.answer_monuments_1_4)
                    }
                    1 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_2)
                        textView1.text = resources.getString(R.string.answer_monuments_2_1)
                        textView2.text = resources.getString(R.string.answer_monuments_2_2)
                        textView3.text = resources.getString(R.string.answer_monuments_2_3)
                        textView4.text = resources.getString(R.string.answer_monuments_2_4)
                    }
                    2 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_3)
                        textView1.text = resources.getString(R.string.answer_monuments_3_1)
                        textView2.text = resources.getString(R.string.answer_monuments_3_2)
                        textView3.text = resources.getString(R.string.answer_monuments_3_3)
                        textView4.text = resources.getString(R.string.answer_monuments_3_4)
                    }
                    3 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_4)
                        textView1.text = resources.getString(R.string.answer_monuments_4_1)
                        textView2.text = resources.getString(R.string.answer_monuments_4_2)
                        textView3.text = resources.getString(R.string.answer_monuments_4_3)
                        textView4.text = resources.getString(R.string.answer_monuments_4_4)
                    }
                    4 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_5)
                        textView1.text = resources.getString(R.string.answer_monuments_5_1)
                        textView2.text = resources.getString(R.string.answer_monuments_5_2)
                        textView3.text = resources.getString(R.string.answer_monuments_5_3)
                        textView4.text = resources.getString(R.string.answer_monuments_5_4)
                    }
                    5 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_6)
                        textView1.text = resources.getString(R.string.answer_monuments_6_1)
                        textView2.text = resources.getString(R.string.answer_monuments_6_2)
                        textView3.text = resources.getString(R.string.answer_monuments_6_3)
                        textView4.text = resources.getString(R.string.answer_monuments_6_4)
                    }
                    6 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_7)
                        textView1.text = resources.getString(R.string.answer_monuments_7_1)
                        textView2.text = resources.getString(R.string.answer_monuments_7_2)
                        textView3.text = resources.getString(R.string.answer_monuments_7_3)
                        textView4.text = resources.getString(R.string.answer_monuments_7_4)
                    }
                    7 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_8)
                        textView1.text = resources.getString(R.string.answer_monuments_8_1)
                        textView2.text = resources.getString(R.string.answer_monuments_8_2)
                        textView3.text = resources.getString(R.string.answer_monuments_8_3)
                        textView4.text = resources.getString(R.string.answer_monuments_8_4)
                    }
                    8 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_9)
                        textView1.text = resources.getString(R.string.answer_monuments_9_1)
                        textView2.text = resources.getString(R.string.answer_monuments_9_2)
                        textView3.text = resources.getString(R.string.answer_monuments_9_3)
                        textView4.text = resources.getString(R.string.answer_monuments_9_4)
                    }
                    9 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_10)
                        textView1.text = resources.getString(R.string.answer_monuments_10_1)
                        textView2.text = resources.getString(R.string.answer_monuments_10_2)
                        textView3.text = resources.getString(R.string.answer_monuments_10_3)
                        textView4.text = resources.getString(R.string.answer_monuments_10_4)
                    }
                    10 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_11)
                        textView1.text = resources.getString(R.string.answer_monuments_11_1)
                        textView2.text = resources.getString(R.string.answer_monuments_11_2)
                        textView3.text = resources.getString(R.string.answer_monuments_11_3)
                        textView4.text = resources.getString(R.string.answer_monuments_11_4)
                    }
                    11 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_12)
                        textView1.text = resources.getString(R.string.answer_monuments_12_1)
                        textView2.text = resources.getString(R.string.answer_monuments_12_2)
                        textView3.text = resources.getString(R.string.answer_monuments_12_3)
                        textView4.text = resources.getString(R.string.answer_monuments_12_4)
                    }
                    12 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_13)
                        textView1.text = resources.getString(R.string.answer_monuments_13_1)
                        textView2.text = resources.getString(R.string.answer_monuments_13_2)
                        textView3.text = resources.getString(R.string.answer_monuments_13_3)
                        textView4.text = resources.getString(R.string.answer_monuments_13_4)
                    }
                    13 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_14)
                        textView1.text = resources.getString(R.string.answer_monuments_14_1)
                        textView2.text = resources.getString(R.string.answer_monuments_14_2)
                        textView3.text = resources.getString(R.string.answer_monuments_14_3)
                        textView4.text = resources.getString(R.string.answer_monuments_14_4)
                    }
                    14 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_15)
                        textView1.text = resources.getString(R.string.answer_monuments_15_1)
                        textView2.text = resources.getString(R.string.answer_monuments_15_2)
                        textView3.text = resources.getString(R.string.answer_monuments_15_3)
                        textView4.text = resources.getString(R.string.answer_monuments_15_4)
                    }
                    15 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_16)
                        textView1.text = resources.getString(R.string.answer_monuments_16_1)
                        textView2.text = resources.getString(R.string.answer_monuments_16_2)
                        textView3.text = resources.getString(R.string.answer_monuments_16_3)
                        textView4.text = resources.getString(R.string.answer_monuments_16_4)
                    }
                    16 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_17)
                        textView1.text = resources.getString(R.string.answer_monuments_17_1)
                        textView2.text = resources.getString(R.string.answer_monuments_17_2)
                        textView3.text = resources.getString(R.string.answer_monuments_17_3)
                        textView4.text = resources.getString(R.string.answer_monuments_17_4)
                    }
                    17 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_18)
                        textView1.text = resources.getString(R.string.answer_monuments_18_1)
                        textView2.text = resources.getString(R.string.answer_monuments_18_2)
                        textView3.text = resources.getString(R.string.answer_monuments_18_3)
                        textView4.text = resources.getString(R.string.answer_monuments_18_4)
                    }
                    18 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_19)
                        textView1.text = resources.getString(R.string.answer_monuments_19_1)
                        textView2.text = resources.getString(R.string.answer_monuments_19_2)
                        textView3.text = resources.getString(R.string.answer_monuments_19_3)
                        textView4.text = resources.getString(R.string.answer_monuments_19_4)
                    }
                    19 -> {
                        questionTextView.text = resources.getString(R.string.question_monuments_20)
                        textView1.text = resources.getString(R.string.answer_monuments_20_1)
                        textView2.text = resources.getString(R.string.answer_monuments_20_2)
                        textView3.text = resources.getString(R.string.answer_monuments_20_3)
                        textView4.text = resources.getString(R.string.answer_monuments_20_4)
                    }
                }
            }
        }
    }

    private inner class BackPressedTimer : CountDownTimer(2000L, 2000L) {
        override fun onTick(time: Long) {
        }
        override fun onFinish() {
            backPressed = false
        }
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