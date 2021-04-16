package com.github.llmaximll.bashgid.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.R

class AboutUsFragment : Fragment(), OnBackPressedFragment {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)



        return view
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }
}