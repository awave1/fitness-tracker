package com.group15.fitnesstracker.onboarding.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.MainActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(), LoginContract.View {
    override lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = LoginPresenter(this, context!!, DbInjection.provideUserDao(context!!))
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {
            val username = usernameInputContainer.editText?.text?.toString()
            val password = passwordInputContainer.editText?.text?.toString()

            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                presenter.login(username!!, password!!)

                startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }
        }
    }
}