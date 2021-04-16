package com.github.llmaximll.bashgid.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.github.llmaximll.bashgid.R
import com.github.llmaximll.bashgid.dataclasses.ItemInnerHistory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

private const val TAG = "HistoryFragment"

class HistoryFragment : Fragment() {

    interface Callbacks {
        fun onHistoryFragment(categoryInt: Int, positionInt: Int, imageView: ImageView, linearLayout: ViewGroup)
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var itemInnerHistoryGlobalTitle: String

    private var citiesList: List<ItemInnerHistory> = listOf()
    private var monumentsList: List<ItemInnerHistory> = listOf()
    private var natureReservesList: List<ItemInnerHistory> = listOf()

    private var callbacks: Callbacks? = null
    private var categoryInt: Int = 0
    private var positionInt: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)

        viewPager.adapter = PagerAdapter()

        pagerCallback(viewPager, false)

        citiesList = listOf(
                ItemInnerHistory(resources.getString(R.string.city_title_agidel), "Content City 1", 0),
                ItemInnerHistory(resources.getString(R.string.city_title_baimak), "Content City 2", 1),
                ItemInnerHistory(resources.getString(R.string.city_title_belebei), "Content City 3", 2),
                ItemInnerHistory("Title City 4", "Content City 4"),
                ItemInnerHistory("Title City 5", "Content City 5"),
                ItemInnerHistory("Title City 6", "Content City 6"),
                ItemInnerHistory("Title City 7", "Content City 7"),
                ItemInnerHistory("Title City 8", "Content City 8"),
        )
        monumentsList = listOf(
                ItemInnerHistory("Title Monument 1", "Content Monument 1"),
                ItemInnerHistory("Title Monument 2", "Content Monument 2"),
                ItemInnerHistory("Title Monument 3", "Content Monument 3"),
                ItemInnerHistory("Title Monument 4", "Content Monument 4"),
                ItemInnerHistory("Title Monument 5", "Content Monument 5"),
                ItemInnerHistory("Title Monument 6", "Content Monument 6"),
                ItemInnerHistory("Title Monument 7", "Content Monument 7"),
                ItemInnerHistory("Title Monument 8", "Content Monument 8"),
        )
        natureReservesList = listOf(
                ItemInnerHistory("Title Reserve 1", "Content Reserve 1"),
                ItemInnerHistory("Title Reserve 2", "Content Reserve 2"),
                ItemInnerHistory("Title Reserve 3", "Content Reserve 3"),
                ItemInnerHistory("Title Reserve 4", "Content Reserve 4"),
                ItemInnerHistory("Title Reserve 5", "Content Reserve 5"),
                ItemInnerHistory("Title Reserve 6", "Content Reserve 6"),
                ItemInnerHistory("Title Reserve 7", "Content Reserve 7"),
                ItemInnerHistory("Title Reserve 8", "Content Reserve 8"),
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout.setTabTextColors(resources.getColor(R.color.text_white), resources.getColor(R.color.text_white))
        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.text_white))
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getString(R.string.cities)
                }
                1 -> {
                    tab.text = resources.getString(R.string.monuments)
                }
                2 -> {
                    tab.text = resources.getString(R.string.reserves)
                }
            }
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
        pagerCallback(viewPager, true)
    }

    private fun pagerCallback(viewPager2: ViewPager2, remove: Boolean) {
        val onPageChangeCallback = object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                categoryInt = position
                super.onPageSelected(position)
            }
        }
        if (!remove) {
            viewPager2.registerOnPageChangeCallback(onPageChangeCallback)
        } else {
            viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback)
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

    /**
     * Внутренний RecyclerView
     */

    inner class InnerPagerHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnTouchListener {

        init {
            itemView.setOnTouchListener(this)
        }

        private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        private val contentTextView: TextView = itemView.findViewById(R.id.content_text_view)
        private val imageView: ImageView = itemView.findViewById(R.id.inner_image_view)
        private val linearLayout: ViewGroup = itemView.findViewById(R.id.linear_layout)

        fun bind(itemInnerHistory: ItemInnerHistory) {
            titleTextView.text = itemInnerHistory.title
            contentTextView.text = itemInnerHistory.content
            itemInnerHistoryGlobalTitle = itemInnerHistory.title
            when (itemInnerHistory.imageInt) {
                0 -> imageView.setImageResource(R.drawable.agidel)
                1 -> imageView.setImageResource(R.drawable.baimak)
                2 -> imageView.setImageResource(R.drawable.belebei)
                else -> imageView.setBackgroundResource(R.drawable.baimak)
            }
        }

        override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

            positionInt = adapterPosition

            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    animateView(view!!, false)
                }
                MotionEvent.ACTION_CANCEL -> {
                    animateView(view!!, true)
                }
                MotionEvent.ACTION_UP -> {
                    animateView(view!!, true)
                    imageView.transitionName = "transition_name_" + Random.nextInt(10000000).toString()
                    linearLayout.transitionName = "transition_name_" + Random.nextInt(10000000).toString()
                    when (categoryInt) {
                        0 -> {
                            when (positionInt) {
                                0 -> callbacks?.onHistoryFragment(0, 0, imageView, linearLayout)
                                1 -> callbacks?.onHistoryFragment(0, 1, imageView, linearLayout)
                                2 -> callbacks?.onHistoryFragment(0, 2, imageView, linearLayout)
                                3 -> callbacks?.onHistoryFragment(0, 3, imageView, linearLayout)
                                4 -> callbacks?.onHistoryFragment(0, 4, imageView, linearLayout)
                                5 -> callbacks?.onHistoryFragment(0, 5, imageView, linearLayout)
                                6 -> callbacks?.onHistoryFragment(0, 6, imageView, linearLayout)
                                7 -> callbacks?.onHistoryFragment(0, 7, imageView, linearLayout)
                            }
                        }
                        1 -> {
                            when (positionInt) {
                                0 -> callbacks?.onHistoryFragment(1, 0, imageView, linearLayout)
                                1 -> callbacks?.onHistoryFragment(1, 1, imageView, linearLayout)
                                2 -> callbacks?.onHistoryFragment(1, 2, imageView, linearLayout)
                                3 -> callbacks?.onHistoryFragment(1, 3, imageView, linearLayout)
                                4 -> callbacks?.onHistoryFragment(1, 4, imageView, linearLayout)
                                5 -> callbacks?.onHistoryFragment(1, 5, imageView, linearLayout)
                                6 -> callbacks?.onHistoryFragment(1, 6, imageView, linearLayout)
                                7 -> callbacks?.onHistoryFragment(1, 7, imageView, linearLayout)
                            }
                        }
                        2 -> {
                            when (positionInt) {
                                0 -> callbacks?.onHistoryFragment(2, 0, imageView, linearLayout)
                                1 -> callbacks?.onHistoryFragment(2, 1, imageView, linearLayout)
                                2 -> callbacks?.onHistoryFragment(2, 2, imageView, linearLayout)
                                3 -> callbacks?.onHistoryFragment(2, 3, imageView, linearLayout)
                                4 -> callbacks?.onHistoryFragment(2, 4, imageView, linearLayout)
                                5 -> callbacks?.onHistoryFragment(2, 5, imageView, linearLayout)
                                6 -> callbacks?.onHistoryFragment(2, 6, imageView, linearLayout)
                                7 -> callbacks?.onHistoryFragment(2, 7, imageView, linearLayout)
                            }
                        }
                    }
                    view.performClick()
                }
            }
            return true
        }
    }

    inner class InnerPagerAdapter(private val ItemInnerHistoryList: List<ItemInnerHistory>) : RecyclerView.Adapter<InnerPagerHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerPagerHolder {
            val view = layoutInflater.inflate(R.layout.item_inner_history, parent, false)
            return InnerPagerHolder(view)
        }

        override fun onBindViewHolder(holder: InnerPagerHolder, position: Int) {
            val itemInnerHistory = ItemInnerHistoryList[position]
            holder.bind(itemInnerHistory)
        }

        override fun getItemCount(): Int = ItemInnerHistoryList.size
    }

    /**
     * Внешний RecyclerView
     */

    private inner class PagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val recyclerView: RecyclerView = itemView.findViewById(R.id.item_history_recycler_view)

        fun bind(position: Int) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            when (position) {
                0 -> {
                    recyclerView.adapter = InnerPagerAdapter(citiesList)
                }
                1 -> {
                    recyclerView.adapter = InnerPagerAdapter(monumentsList)
                }
                2 -> {
                    recyclerView.adapter = InnerPagerAdapter(natureReservesList)
                }
            }
        }
    }

    private inner class PagerAdapter :
            RecyclerView.Adapter<PagerHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {
            val view = layoutInflater.inflate(R.layout.item_history, parent, false)
            return PagerHolder(view)
        }

        override fun onBindViewHolder(holder: PagerHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int = 3
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}