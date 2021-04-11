package com.github.llmaximll.bashgid.fragments

import android.os.Bundle
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

class DetailsFragment : Fragment() {

    private lateinit var category: String
    private lateinit var toolbar: Toolbar
    private lateinit var imageViewTransitionName: String
    private lateinit var linearLayoutTransitionName: String
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var coordinatorLayout: CoordinatorLayout

    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString(KEY_CATEGORY, "Null") as String
        position = arguments?.getInt(KEY_POSITION) as Int
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

        reenterTransition = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        toolbar = view.findViewById(R.id.tool_bar)
        imageView = view.findViewById(R.id.image_view)
        textView = view.findViewById(R.id.text_view)
        coordinatorLayout = view.findViewById(R.id.coordinator_layout)

        imageView.transitionName = imageViewTransitionName
        coordinatorLayout.transitionName = linearLayoutTransitionName

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "Агидель"
    }

    companion object {
        fun newInstance(category: String, position: Int, imageViewTransitionName: String,
        linearLayoutTransitionName: String): DetailsFragment {
            val args = Bundle().apply {
                putSerializable(KEY_CATEGORY, category)
                putSerializable(KEY_POSITION, position)
                putSerializable(KEY_T_NAME_IMAGE_VIEW, imageViewTransitionName)
                putSerializable(KEY_T_NAME_LINEAR_LAYOUT, linearLayoutTransitionName)
            }
            return DetailsFragment().apply {
                arguments = args
            }
        }
    }
}