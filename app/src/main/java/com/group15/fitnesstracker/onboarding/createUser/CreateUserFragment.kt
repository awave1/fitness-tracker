package com.group15.fitnesstracker.onboarding.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.User
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment: Fragment(), CreateUserContract.View {
    override lateinit var presenter: CreateUserContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateUserPresenter(this, context!!)

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = firstNameInputContainer.editText?.text?.toString()
        val lastName = lastNameInputContainer.editText?.text?.toString()
        val age = ageInputContainer.editText.toString().toIntOrNull()
        val weight = weightInputContainer.editText.toString().toDoubleOrNull()

        submit.setOnClickListener {
            if (firstName != null && lastName != null && age != null && weight != null) {
                presenter.createUser(firstName, lastName, age, weight)
            }

//            val sharedPref = context?.getSharedPreferences(context?.resources?.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//            sharedPref?.edit()?.putBoolean(Constants.USER_FIRST_TIME, false)?.apply()
//            startActivity(Intent(context, MainActivity::class.java))
//            activity?.finish()
        }
    }
}