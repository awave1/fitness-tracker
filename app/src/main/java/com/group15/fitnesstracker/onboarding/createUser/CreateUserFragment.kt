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
        presenter = CreateUserPresenter(this)

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val firstName = firstName.text.toString()
//        val lastName = lastName.text.toString()
//        val age = age.text.toString().toInt()
//        val weight = weight.text.toString().toDouble()

        submit.setOnClickListener {
            presenter.createUser()
            Toast.makeText(context, "Create user", Toast.LENGTH_LONG).show()
            
//            val sharedPref = context?.getSharedPreferences(context?.resources?.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//            sharedPref?.edit()?.putBoolean(Constants.USER_FIRST_TIME, false)?.apply()
//            startActivity(Intent(context, MainActivity::class.java))
//            activity?.finish()
        }
    }
}