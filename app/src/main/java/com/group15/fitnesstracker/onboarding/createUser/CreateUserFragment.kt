package com.group15.fitnesstracker.onboarding.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R

class CreateUserFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            val sharedPref = context?.getSharedPreferences(context?.resources?.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//            sharedPref?.edit()?.putBoolean(Constants.USER_FIRST_TIME, false)?.apply()
//            startActivity(Intent(context, MainActivity::class.java))
//            activity?.finish()
    }
}