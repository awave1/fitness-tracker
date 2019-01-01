package com.awave.wrkout.onboarding

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView

interface OnboardingPageContract {
    interface View: BaseView<Presenter> {
        fun showButtons(position: Int)
        fun showText(text: String)
        fun showImage(image: Int)
    }

    interface Presenter: BasePresenter {
        fun showPage(position: Int)
    }
}