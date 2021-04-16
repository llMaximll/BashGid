package com.github.llmaximll.bashgid.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.llmaximll.bashgid.MainActivity
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

class ResultTestFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onResultTest(backPressed: Boolean)
    }

    private lateinit var countTextView: TextView
    private lateinit var recyclerView: RecyclerView

    private var count = 0

    private var callbacks: Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (MainActivity.test) {
            0 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    if (testAnswer == MainActivity.testCityList[index]) {
                        count++
                    }
                }
            }
            1 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    if (testAnswer == MainActivity.testMonumentList[index]) {
                        count++
                    }
                }
            }
            2 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    if (testAnswer == MainActivity.testReserveList[index]) {
                        count++
                    }
                }
            }
            3 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    if (testAnswer == MainActivity.testCityList[index]) {
                        count++
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result_test, container, false)

        countTextView = view.findViewById(R.id.count_text_view)
        recyclerView = view.findViewById(R.id.recycler_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(count)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        MainActivity.testAnswersList.clear()
        MainActivity.test = -1
        MainActivity.question = 0
        callbacks?.onResultTest(true)
        return false
    }

    private fun updateUI(count: Int) {
        when {
            count <= 10 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                countTextView.text = "$count"
            }
            count in 11..17 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                countTextView.text = "$count"
            }
            count >= 18 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                countTextView.text = "$count"
            }
        }
    }

    companion object {
        fun newInstance(): ResultTestFragment {
            return ResultTestFragment()
        }
    }
}