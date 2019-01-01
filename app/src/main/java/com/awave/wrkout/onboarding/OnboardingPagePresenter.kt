package com.awave.wrkout.onboarding

import com.awave.wrkout.R

class OnboardingPagePresenter(val view: OnboardingPageContract.View, private val data: Array<String>)
    : OnboardingPageContract.Presenter {
    init {
        view.presenter = this
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPage(position: Int) {
        var image = 0
        view.showText(data[position])
        view.showButtons(position)

        when(position) {
            0 -> image = R.drawable.ic_heart_24dp
            1 -> image = R.drawable.ic_fitness_24dp
            2 -> image = R.drawable.ic_smile_24dp
        }

        view.showImage(image)
    }
}