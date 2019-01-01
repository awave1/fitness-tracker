package com.awave.wrkout.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.awave.wrkout.R
import com.awave.wrkout.onboarding.createUser.CreateUserFragment
import com.awave.wrkout.onboarding.login.LoginFragment
import com.awave.wrkout.util.Constants
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
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, CreateUserFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }

        onboardingLoginBtn.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, LoginFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun showText(text: String) {
        onboardingMessage.text = text
    }

    override fun showButtons(position: Int) {
        onboardingStartBtn.visibility = if (position == 2) View.VISIBLE else View.GONE
        onboardingLoginBtn.visibility = if (position == 2) View.VISIBLE else View.GONE
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