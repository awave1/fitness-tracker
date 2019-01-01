package com.awave.wrkout.onboarding.login

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
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(), LoginContract.View {
    override lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = LoginPresenter(this, context, fragmentManager, DbInjection.provideUserDao(context!!))
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isTrainer = false

        trainerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            isTrainer = isChecked
            if (isChecked) {
                emailInputContainer.visibility = View.VISIBLE
                usernameInputContainer.visibility = View.GONE
            } else {
                emailInputContainer.visibility = View.GONE
                usernameInputContainer.visibility = View.VISIBLE
            }
        }

        submit.setOnClickListener {
            val email = getText(emailInputContainer)
            val username = getText(usernameInputContainer)
            val password = getText(passwordInputContainer)

            if (!isTrainer) {
                if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    presenter.loginUser(username, password)
                    (activity as AppCompatActivity).supportActionBar?.show()
                }
            } else {
                if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    presenter.loginTrainer(email, password)
                    (activity as AppCompatActivity).supportActionBar?.show()
                }

            }
        }
    }

    private fun getText(inputContainer: TextInputLayout): String? {
        if (inputContainer.isVisible) {
            return inputContainer.editText?.text?.toString()
        }

        return null
    }
}