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
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R
import com.github.llmaximll.bashgid.dataclasses.ItemTest

class TestFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onTestFragment(position: Int)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolBar: Toolbar

    private val testList: List<ItemTest> = listOf(
            ItemTest("Города", ""),
            ItemTest("Памятники", ""),
            ItemTest("Заповедники", ""),
            ItemTest("Смешанное", "")
    )

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        toolBar = view.findViewById(R.id.tool_bar)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TestAdapter(testList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBar.title = resources.getString(R.string.tests)
        toolBar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.text_white))
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        return true
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

    private inner class TestHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnTouchListener {

        init {
            itemView.setOnTouchListener(this)
        }

        private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        private val contentTextView: TextView = itemView.findViewById(R.id.content_text_view)

        fun bind(test: ItemTest) {
            titleTextView.text = test.title
            contentTextView.text = test.content
        }

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateView(view!!, false)
                }
                MotionEvent.ACTION_CANCEL -> {
                    animateView(view!!, true)
                }
                MotionEvent.ACTION_UP -> {
                    animateView(view!!, true)
                    callbacks?.onTestFragment(adapterPosition)
                    view.performClick()
                }
            }
            return true
        }
    }

    private inner class TestAdapter(var testList: List<ItemTest>)
        : RecyclerView.Adapter<TestHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
            val view = layoutInflater.inflate(R.layout.item_test, parent, false)
            return TestHolder(view)
        }

        override fun onBindViewHolder(holder: TestHolder, position: Int) {
            val test = testList[position]
            holder.bind(test)
        }

        override fun getItemCount(): Int = testList.size
    }


    companion object {
        fun newInstance(): TestFragment {
            return TestFragment()
        }
    }
}