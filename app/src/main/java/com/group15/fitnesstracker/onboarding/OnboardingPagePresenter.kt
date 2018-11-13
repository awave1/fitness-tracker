package com.group15.fitnesstracker.onboarding

import com.group15.fitnesstracker.R

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
        view.showButton(position)

        when(position) {
            0 -> image = R.drawable.ic_heart_24dp
            1 -> image = R.drawable.ic_fitness_24dp
            2 -> image = R.drawable.ic_smile_24dp
        }

        view.showImage(image)
    }
}