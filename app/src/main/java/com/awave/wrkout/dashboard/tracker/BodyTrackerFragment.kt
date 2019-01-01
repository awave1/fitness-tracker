package com.awave.wrkout.dashboard.tracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout
import com.awave.wrkout.R
import com.awave.wrkout.db.BodyMeasureRecording
import com.awave.wrkout.util.Constants
import kotlinx.android.synthetic.main.fragment_body_tracker.*

class BodyTrackerFragment: Fragment(), CreateRecordingContract.BodyTrackerView {
    override lateinit var presenter: CreateRecordingContract.Presenter
    private lateinit var adapter: RecordingAdapter<BodyMeasureRecording>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = CreateRecordingPresenter(this, context)
        return inflater.inflate(R.layout.fragment_body_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val userId = sharedPref?.getInt(Constants.CURRENT_USER_ID, -1)

        presenter.loadBodyRecordings(userId)

        adapter = RecordingAdapter(R.layout.item_recording_body)
        bodyList.layoutManager = LinearLayoutManager(context)
        bodyList.adapter = adapter

        createBodyBtn.setOnClickListener {
            CreateRecordingDialogFactory
                    .create(
                            R.layout.fragment_create_body,
                            it.context,
                            onSave = { _, _, view ->
                                val bodyFat = view.findViewById<TextInputLayout>(R.id.bodyfatInputContainer).editText
                                val weight = view.findViewById<TextInputLayout>(R.id.weightInputContainer).editText

                                presenter.createBodyRecording(bodyFat?.text?.toString(), weight?.text?.toString(), userId) { record ->
                                    adapter.items.add(record)
                                    adapter.notifyDataSetChanged()
                                }
                            },
                            onCancel = { dialog, _ ->  dialog.cancel() }
                    )
                    .show()
        }
    }

    override fun showBodyRecordings(recordings: MutableList<BodyMeasureRecording>) {
        adapter.items = recordings
    }
}
