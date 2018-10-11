package com.group15.fitnesstracker.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.fragment_onboarding_page.view.*

val MESSAGE = "com.group15.OnboardPageFragment.MESSAGE"
val SHOW_BTN = "com.group15.OnboardPageFragment.SHOW_BTN"

class OnboardingPageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding_page, container, false)
        view.onboardingMessage.text = arguments?.getString(MESSAGE)
        view.onboardingStartBtn.visibility = if (arguments?.getBoolean(SHOW_BTN)!!) View.VISIBLE else View.GONE

        return view
    }

    companion object {
        fun newInstance(message: String, showBtn: Boolean): OnboardingPageFragment {
            val instance = OnboardingPageFragment()
            val args = Bundle()

            args.putString(MESSAGE, message)
            args.putBoolean(SHOW_BTN, showBtn);

            instance.arguments = args
            return instance
        }
    }
}