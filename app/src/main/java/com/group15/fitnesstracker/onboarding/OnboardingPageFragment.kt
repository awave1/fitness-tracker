package com.group15.fitnesstracker.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_onboarding_page.*


class OnboardingPageFragment : Fragment(), OnboardingContract.View {
    override lateinit var presenter: OnboardingContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = OnboardingPresenter(this, resources.getStringArray(R.array.onboarding_messages))
        return inflater.inflate(R.layout.fragment_onboarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagePosition = arguments?.getInt(Constants.ONBOARDING_PAGE_POS)
        presenter.showPage(pagePosition!!)
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
        fun newInstance(position: Int): OnboardingPageFragment {
            val instance = OnboardingPageFragment()
            val args = Bundle()

            args.putInt(Constants.ONBOARDING_PAGE_POS, position)

            instance.arguments = args
            return instance
        }
    }
}