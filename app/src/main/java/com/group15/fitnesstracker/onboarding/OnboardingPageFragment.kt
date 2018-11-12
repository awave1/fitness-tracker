package com.group15.fitnesstracker.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.MainActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_onboarding_page.*


class OnboardingPageFragment : Fragment(), OnboardingPageContract.View {
    override lateinit var presenter: OnboardingPageContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = OnboardingPagePresenter(this, resources.getStringArray(R.array.onboarding_messages))
        return inflater.inflate(R.layout.fragment_onboarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagePosition = arguments?.getInt(Constants.ONBOARDING_PAGE_POS)
        presenter.showPage(pagePosition!!)

        onboardingStartBtn.setOnClickListener {
            val sharedPref = context?.getSharedPreferences(context?.resources?.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            sharedPref?.edit()?.putBoolean(Constants.USER_FIRST_TIME, false)?.apply()
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }
    }

    override fun showText(text: String) {
        onboardingMessage.text = text
    }

    override fun showButton(position: Int) {
        onboardingStartBtn.visibility = if (position == 2) View.VISIBLE else View.GONE
    }

    override fun showImage(image: Int) {
        onboardingIcon.setImageResource(image)
    }

    companion object {
        fun newInstance(position: Int) = OnboardingPageFragment().apply {
            arguments = bundleOf(
                Constants.ONBOARDING_PAGE_POS to position
            )
        }
    }
}