package com.awave.wrkout.onboarding.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment: Fragment(), CreateUserContract.View {
    override lateinit var presenter: CreateUserContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateUserPresenter(this, context, fragmentManager, DbInjection.provideUserDao(context!!))

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isTrainer = false

        trainerSwitch.setOnCheckedChangeListener { _, isChecked ->
            isTrainer = isChecked

            if (isChecked) {
                usernameInputContainer.visibility = View.GONE
                ageInputContainer.visibility = View.GONE
                weightInputContainer.visibility = View.GONE

                emailInputContainer.visibility = View.VISIBLE
            } else {
                usernameInputContainer.visibility = View.VISIBLE
                ageInputContainer.visibility = View.VISIBLE
                weightInputContainer.visibility = View.VISIBLE

                emailInputContainer.visibility = View.GONE
            }
        }

        submit.setOnClickListener {
            val email = getText(emailInputContainer)
            val username = getText(usernameInputContainer)
            val password = getText(passwordInputContainer)
            val firstName = getText(firstNameInputContainer)
            val lastName = getText(lastNameInputContainer)
            val age = getText(ageInputContainer)?.toInt()
            val weight = getText(weightInputContainer)?.toDouble()

            if (!isTrainer) {
                if (!username.isNullOrEmpty() &&
                    !password.isNullOrEmpty() &&
                    !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() &&
                    age != null && weight != null) {
                    presenter.createUser(username!!, password!!, firstName!!, lastName!!, age, weight)
                }
            } else {
                if (!email.isNullOrEmpty() &&
                    !password.isNullOrEmpty() &&
                    !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
                    presenter.createTrainer(email!!, password!!, firstName!!, lastName!!)
                }
            }

            (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

    private fun getText(inputContainer: TextInputLayout): String? {
        if (inputContainer.isVisible) {
            return inputContainer.editText?.text?.toString()
        }

        return null
    }
}