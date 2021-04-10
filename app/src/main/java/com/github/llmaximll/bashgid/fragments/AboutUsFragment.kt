package com.github.llmaximll.bashgid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.R

class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)



        return view
    }

    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }
}