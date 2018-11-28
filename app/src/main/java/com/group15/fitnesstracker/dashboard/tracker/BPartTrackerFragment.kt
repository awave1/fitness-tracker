package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.fragment_body_tracker.*
import kotlinx.android.synthetic.main.fragment_bpart_tracker.*
import kotlinx.android.synthetic.main.fragment_tracker_page.*

class BPartTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bpart_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        bpart_text.text = getString(R.string.bpart)

        create_tracker_fab.setOnClickListener {

        }

    }
}
