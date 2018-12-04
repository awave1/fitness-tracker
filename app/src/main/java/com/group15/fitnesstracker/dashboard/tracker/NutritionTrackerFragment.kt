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
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.db.NutritionRecording
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_nutrition_tracker.*

class NutritionTrackerFragment : Fragment(), CreateRecordingContract.NutritionTrackerView {
    override lateinit var presenter: CreateRecordingContract.Presenter
    private lateinit var adapter: RecordingAdapter<NutritionRecording>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = CreateRecordingPresenter(this, context)
        return inflater.inflate(R.layout.fragment_nutrition_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val userId = sharedPref?.getInt(Constants.CURRENT_USER_ID, 0)

        presenter.loadNutritionRecordings(userId)

        adapter = RecordingAdapter(R.layout.item_recording_nutrition)
        nutritionList.layoutManager = LinearLayoutManager(context)
        nutritionList.adapter = adapter

        createNutrition.setOnClickListener {
            CreateRecordingDialogFactory.create(
                    R.layout.fragment_create_nutrition,
                    layoutInflater,
                    it.context,
                    onSave = { dialog, id, view ->
                        val calories = view.findViewById<TextInputLayout>(R.id.caloriesInputContainer).editText
                        val protein = view.findViewById<TextInputLayout>(R.id.macroProteinInputContainer).editText
                        val carbs = view.findViewById<TextInputLayout>(R.id.macroCarbInputContainer).editText
                        val fat = view.findViewById<TextInputLayout>(R.id.macroFatInputContainer).editText

                        presenter.createNutritionRecording(
                                userId = userId,
                                calories = calories?.text.toString().toDoubleOrNull(),
                                protein = protein?.text.toString().toDoubleOrNull(),
                                carbohydrate = carbs?.text.toString().toDoubleOrNull(),
                                fat = fat?.text.toString().toDoubleOrNull()
                        ) { record ->
                            adapter.items.add(record)
                            adapter.notifyDataSetChanged()
                        }
                    },
                    onCancel = { dialog, _ -> dialog.cancel() }
            )
            .show()
        }

    }

    override fun showNutritionRecordings(recordings: MutableList<NutritionRecording>) {
        adapter.items = recordings
    }

}
