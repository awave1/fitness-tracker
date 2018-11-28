package com.group15.fitnesstracker.dashboard.tracker.createBody

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.BodyMeasureRecording
import kotlinx.android.synthetic.main.body_item.*
import kotlinx.android.synthetic.main.fragment_body_tracker.*
import java.util.*




class BodyTrackerFragment : Fragment() {

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
        bodyRecords.add(BodyMeasureRecording(Date(2000, 12, 1), 11.21, 12.2))
        bodyRecords.add(BodyMeasureRecording(Date(2000, 12, 1), 11.21, 12.2))
        bodyRecords.add(BodyMeasureRecording(Date(2000, 12, 1), 11.21, 12.2))

        val adapter = BodyTrackerAdapter(context!!, R.layout.body_item, bodyRecords)
        body_list.adapter = adapter

    }

    inner class BodyTrackerAdapter(private val mContext: Context, private val resourceLayout: Int, items: ArrayList<BodyMeasureRecording>) : ArrayAdapter<BodyMeasureRecording>(mContext, resourceLayout, items) {

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
