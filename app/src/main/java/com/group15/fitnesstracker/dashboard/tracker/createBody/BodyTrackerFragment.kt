package com.group15.fitnesstracker.dashboard.tracker.createBody

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.BodyMeasureRecording
import kotlinx.android.synthetic.main.body_item.*
import kotlinx.android.synthetic.main.fragment_body_tracker.*
import java.util.*




class BodyTrackerFragment : Fragment() /*, CreateBodyDialogFragment.NoticeDialogListener*/ {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_body_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        // create test list
        val bodyRecords = ArrayList<BodyMeasureRecording>()
        bodyRecords.add(BodyMeasureRecording(Date(2000, 12, 1), 11.21, 12.2))
        bodyRecords.add(BodyMeasureRecording(Date(1999, 12, 16), 4.21, 1.2))
        bodyRecords.add(BodyMeasureRecording(Date(1998, 12, 5), 1.1, 14.2))
        bodyRecords.add(BodyMeasureRecording(Date(1996, 2, 3), 222.21, 122.12))

        val adapter = BodyTrackerAdapter(context!!, R.layout.body_item, bodyRecords)
        body_list.adapter = adapter

        createBodyBtn.setOnClickListener {
            /*
            // Create an instance of the dialog fragment and show it
            val dialog = CreateBodyDialogFragment()
            dialog.show(fragmentManager, null)
            */
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.tracker_coordinatorLayout, CreateBodyFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }
/*
    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        // User touched the dialog's positive button
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // User touched the dialog's negative button
    }
*/

    inner class BodyTrackerAdapter(private val mContext: Context, private val resourceLayout: Int, items: ArrayList<BodyMeasureRecording>)
        : ArrayAdapter<BodyMeasureRecording>(mContext, resourceLayout, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var v = convertView

            if (v == null) {
                val vi: LayoutInflater = LayoutInflater.from(mContext)
                v = vi.inflate(resourceLayout, null)
            }

            val bodyRecord = getItem(position) as BodyMeasureRecording

            body_item_date?.text = bodyRecord.date.toString()
            body_item_body_fat?.text = bodyRecord.bodyfat.toString()
            body_item_weight?.text = bodyRecord.weight.toString()

            return v!!
        }

    }

}
