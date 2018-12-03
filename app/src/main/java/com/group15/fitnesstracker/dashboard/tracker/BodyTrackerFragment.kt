package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.tracker.CreateRecordingDialogFactory
import com.group15.fitnesstracker.db.BodyMeasureRecording
import kotlinx.android.synthetic.main.body_item.*
import kotlinx.android.synthetic.main.fragment_body_tracker.*
import java.util.*

class BodyTrackerFragment: Fragment(), CreateRecordingContract.View {
    override lateinit var presenter: CreateRecordingContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateRecordingPresenter(context)
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
            CreateRecordingDialogFactory
                    .create(
                        R.layout.fragment_create_body,
                        layoutInflater,
                        it.context,
                        onSave = { dialog, id, view ->
                            val bodyFat = view.findViewById<TextInputLayout>(R.id.bodyfatInputContainer).editText
                            val weight = view.findViewById<TextInputLayout>(R.id.weightInputContainer).editText

                            presenter.createBodyRecording(bodyFat?.text?.toString(), weight?.text?.toString())
                        },
                        onCancel = { dialog, _ ->  dialog.cancel() }
                    )
                    .show()
        }
    }

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
