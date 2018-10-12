package com.group15.fitnesstracker.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_onboarding_page.view.*

class OnboardingPageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding_page, container, false)
        view.onboardingMessage.text = arguments?.getString(Constants.ONBOARDING_MESSAGE)
        view.onboardingStartBtn.visibility = if (arguments?.getBoolean(Constants.ONBOARDING_SHOW_BTN_FLAG)!!) View.VISIBLE else View.GONE

        return view
    }

    companion object {
        fun newInstance(message: String, showBtn: Boolean): OnboardingPageFragment {
            val instance = OnboardingPageFragment()
            val args = Bundle()

            args.putString(Constants.ONBOARDING_MESSAGE, message)
            args.putBoolean(Constants.ONBOARDING_SHOW_BTN_FLAG, showBtn)

            instance.arguments = args
            return instance
        }
    }
}