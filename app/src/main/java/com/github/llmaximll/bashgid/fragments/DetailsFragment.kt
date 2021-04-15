package com.github.llmaximll.bashgid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.github.llmaximll.bashgid.R

private const val KEY_CATEGORY = "key_category"
private const val KEY_POSITION = "key_position"
private const val KEY_T_NAME_IMAGE_VIEW = "transition_name_image_view"
private const val KEY_T_NAME_LINEAR_LAYOUT = "transition_name_linear_layout"

private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolBarTitle: TextView
    private lateinit var imageViewTransitionName: String
    private lateinit var linearLayoutTransitionName: String
    private lateinit var imageView: ImageView
    private lateinit var generalTextView: TextView
    private lateinit var geographyTextView: TextView
    private lateinit var historyTextView: TextView
    private lateinit var populationTextView: TextView
    private lateinit var attractionsTextView: TextView
    private lateinit var coordinatorLayout: CoordinatorLayout

    private var positionInt: Int? = null
    private var categoryInt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryInt = arguments?.getInt(KEY_CATEGORY) as Int
        positionInt = arguments?.getInt(KEY_POSITION) as Int
        imageViewTransitionName = arguments?.getString(KEY_T_NAME_IMAGE_VIEW, "Null") as String
        linearLayoutTransitionName = arguments?.getString(KEY_T_NAME_LINEAR_LAYOUT, "Null") as String

        sharedElementEnterTransition = TransitionSet().apply {
            addTransition(ChangeImageTransform())
            addTransition(ChangeBounds())
            addTransition(ChangeScroll())
            addTransition(ChangeTransform())
        }
        sharedElementReturnTransition = TransitionSet().apply {
            addTransition(ChangeImageTransform())
            addTransition(ChangeBounds())
            addTransition(ChangeScroll())
            addTransition(ChangeTransform())
        }

        enterTransition = Fade()
        exitTransition = Fade()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        toolbar = view.findViewById(R.id.tool_bar)
        toolBarTitle = view.findViewById(R.id.title_tool_bar)
        imageView = view.findViewById(R.id.image_view)
        generalTextView = view.findViewById(R.id.general_text_view)
        geographyTextView = view.findViewById(R.id.geography_text_view)
        historyTextView = view.findViewById(R.id.history_text_view)
        populationTextView = view.findViewById(R.id.population_text_view)
        attractionsTextView = view.findViewById(R.id.attractions_text_view)
        coordinatorLayout = view.findViewById(R.id.coordinator_layout)

        Log.i(TAG, "category = $categoryInt, position = $positionInt")

        imageView.transitionName = imageViewTransitionName
        coordinatorLayout.transitionName = linearLayoutTransitionName

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (categoryInt) {
            0 -> {
                when (positionInt) {
                    0 -> {
                        imageView.setImageResource(R.drawable.agidel)
                        toolBarTitle.text = resources.getString(R.string.city_title_agidel)
                        generalTextView.text = resources.getString(R.string.city_agidel_general)
                        geographyTextView.text = resources.getString(R.string.city_agidel_geography)
                        historyTextView.text = resources.getString(R.string.city_agidel_history)
                        populationTextView.text = resources.getString(R.string.city_agidel_population)
                        attractionsTextView.text = resources.getString(R.string.city_agidel_history)
                    }
                    1 -> {
                        imageView.setImageResource(R.drawable.baimak)
                        toolBarTitle.text = resources.getString(R.string.city_title_baimak)
                        generalTextView.text = resources.getString(R.string.city_baimak_general)
                        geographyTextView.text = resources.getString(R.string.city_baimak_geography)
                        historyTextView.text = resources.getString(R.string.city_baimak_history)
                        populationTextView.text = resources.getString(R.string.city_baimak_population)
                        attractionsTextView.text = resources.getString(R.string.city_baimak_attractions)
                    }
                    2 -> imageView.setImageResource(R.drawable.baimak)
                    3 -> imageView.setImageResource(R.drawable.baimak)
                    4 -> imageView.setImageResource(R.drawable.baimak)
                    5 -> imageView.setImageResource(R.drawable.baimak)
                    6 -> imageView.setImageResource(R.drawable.baimak)
                    7 -> imageView.setImageResource(R.drawable.baimak)
                    8 -> imageView.setImageResource(R.drawable.baimak)
                }
            }
            1 -> {
                when (positionInt) {
                    0 -> imageView.setImageResource(R.drawable.baimak)
                    1 -> imageView.setImageResource(R.drawable.baimak)
                    2 -> imageView.setImageResource(R.drawable.baimak)
                    3 -> imageView.setImageResource(R.drawable.baimak)
                    4 -> imageView.setImageResource(R.drawable.baimak)
                    5 -> imageView.setImageResource(R.drawable.baimak)
                    6 -> imageView.setImageResource(R.drawable.baimak)
                    7 -> imageView.setImageResource(R.drawable.baimak)
                    8 -> imageView.setImageResource(R.drawable.baimak)
                }
            }
            2 -> {
                when (positionInt) {
                    0 -> imageView.setImageResource(R.drawable.baimak)
                    1 -> imageView.setImageResource(R.drawable.baimak)
                    2 -> imageView.setImageResource(R.drawable.baimak)
                    3 -> imageView.setImageResource(R.drawable.baimak)
                    4 -> imageView.setImageResource(R.drawable.baimak)
                    5 -> imageView.setImageResource(R.drawable.baimak)
                    6 -> imageView.setImageResource(R.drawable.baimak)
                    7 -> imageView.setImageResource(R.drawable.baimak)
                    8 -> imageView.setImageResource(R.drawable.baimak)
                }
            }
        }
        toolbar.inflateMenu(R.menu.fragment_details)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.favorites_off -> {
                    toolbar.menu.findItem(R.id.favorites_off).isVisible = false
                    toolbar.menu.findItem(R.id.favorites_on).isVisible = true
                    true
                }
                R.id.favorites_on -> {
                    toolbar.menu.findItem(R.id.favorites_off).isVisible = true
                    toolbar.menu.findItem(R.id.favorites_on).isVisible = false
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun newInstance(categoryInt: Int, positionInt: Int, imageViewTransitionName: String,
        linearLayoutTransitionName: String): DetailsFragment {
            val args = Bundle().apply {
                putSerializable(KEY_CATEGORY, categoryInt)
                putSerializable(KEY_POSITION, positionInt)
                putSerializable(KEY_T_NAME_IMAGE_VIEW, imageViewTransitionName)
                putSerializable(KEY_T_NAME_LINEAR_LAYOUT, linearLayoutTransitionName)
            }
            return DetailsFragment().apply {
                arguments = args
            }
        }
    }
}