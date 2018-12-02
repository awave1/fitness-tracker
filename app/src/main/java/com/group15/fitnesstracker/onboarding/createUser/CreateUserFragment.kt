package com.group15.fitnesstracker.onboarding.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment: Fragment(), CreateUserContract.View {
    override lateinit var presenter: CreateUserContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateUserPresenter(this, context, fragmentManager, DbInjection.provideUserDao(context!!))
        presenter.start()

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {
            val username = usernameInputContainer.editText?.text?.toString()
            val password = passwordInputContainer.editText?.text?.toString()
            val firstName = firstNameInputContainer.editText?.text?.toString()
            val lastName = lastNameInputContainer.editText?.text?.toString()
            val age = ageInputContainer.editText?.text?.toString()?.toIntOrNull()
            val weight = weightInputContainer.editText?.text?.toString()?.toDoubleOrNull()

            // @TODO: clean this up, show warnings for missing labels
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty() && !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && age != null && weight != null) {
                presenter.createUser(username!!, password!!, firstName!!, lastName!!, age, weight)
                (activity as AppCompatActivity).supportActionBar?.show()
            }
        }
    }
}