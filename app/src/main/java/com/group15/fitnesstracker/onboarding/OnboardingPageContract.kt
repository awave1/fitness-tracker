package com.group15.fitnesstracker.onboarding

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface OnboardingPageContract {
    interface View: BaseView<Presenter> {
        fun showButton(position: Int)
        fun showText(text: String)
        fun showImage(image: Int)
    }

    interface Presenter: BasePresenter {
        fun showPage(position: Int)
    }
}