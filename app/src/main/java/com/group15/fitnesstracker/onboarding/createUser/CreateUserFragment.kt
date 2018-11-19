package com.group15.fitnesstracker.onboarding.createUser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.MainActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment: Fragment(), CreateUserContract.View {
    override lateinit var presenter: CreateUserContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateUserPresenter(this, context!!, DbInjection.provideUserDao(context!!))
        presenter.start()

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {
            val username = usernameInputContainer.editText?.text?.toString()
            val firstName = firstNameInputContainer.editText?.text?.toString()
            val lastName = lastNameInputContainer.editText?.text?.toString()
            val age = ageInputContainer.editText?.text?.toString()?.toIntOrNull()
            val weight = weightInputContainer.editText?.text?.toString()?.toDoubleOrNull()

            if (!username.isNullOrEmpty() && !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && age != null && weight != null) {
                presenter.createUser(username!!, firstName!!, lastName!!, age, weight)

                val sharedPref = context?.getSharedPreferences(
                        context?.resources?.getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE
                )
                sharedPref?.edit()?.putBoolean(Constants.USER_LOGGED_IN, true)?.apply()
                startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }
        }
    }
}