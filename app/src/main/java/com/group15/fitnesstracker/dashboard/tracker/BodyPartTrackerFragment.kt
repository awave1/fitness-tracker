package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout

import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.BodyPartMeasureRecording
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_body_part_tracker.*

class BodyPartTrackerFragment : Fragment(), CreateRecordingContract.BodyPartTrackerView {
    override lateinit var presenter: CreateRecordingContract.Presenter
    private lateinit var adapter: RecordingAdapter<BodyPartMeasureRecording>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = CreateRecordingPresenter(this, context)
        return inflater.inflate(R.layout.fragment_body_part_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val userId = sharedPref?.getInt(Constants.CURRENT_USER_ID, 0)

        presenter.loadBodyPartRecordings(userId)

        adapter = RecordingAdapter(R.layout.item_recording_body_part)
        bodyPartList.layoutManager = LinearLayoutManager(context)
        bodyPartList.adapter = adapter

        createBodyPart.setOnClickListener {
            CreateRecordingDialogFactory.create(
                    R.layout.fragment_create_body_part,
                    layoutInflater,
                    it.context,
                    onSave = { _, _, view ->
                        val bodyPart = view.findViewById<TextInputLayout>(R.id.bodypartInputContainer).editText
                        val bodyPartSize = view.findViewById<TextInputLayout>(R.id.bodypartSizeInputContainer).editText

                        presenter.createBodyPartRecording(bodyPart?.text.toString(),
                                bodyPartSize?.text.toString().toDoubleOrNull(), userId) {
                            adapter.items.add(it)
                            adapter.notifyDataSetChanged()
                        }
                    },
                    onCancel = { dialog, _ -> dialog.cancel() }
            )
        }
    }

    override fun showBodyPartRecordings(recordings: MutableList<BodyPartMeasureRecording>) {
        adapter.items = recordings
    }
}
