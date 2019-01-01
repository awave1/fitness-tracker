package com.awave.wrkout.dashboard.tracker

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.MicronutrientRecording

class CreateRecordingDialogFactory {
    companion object {
        fun create(
                layout: Int,
                context: Context,
                onSave: (dialog: DialogInterface, id: Int, view: View) -> Unit,
                onCancel: (dialog: DialogInterface, id: Int) -> Unit
        ): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val view = View.inflate(context, layout, null)

            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton(R.string.save_recording_ok) { dialog, id -> onSave(dialog, id, view) }
                    .setNegativeButton(R.string.cancel) { dialog, id -> onCancel(dialog, id) }
            return builder.create()
        }

        fun createNutritionDialog(
                layout: Int = R.layout.fragment_create_nutrition,
                context: Context,
                onSave: (microNutrients: List<MicronutrientRecording>, view: View) -> Unit
        ): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val view = View.inflate(context, layout, null)

            val microList = view.findViewById<RecyclerView>(R.id.micronutrientsList)
            val addMicro = view.findViewById<Button>(R.id.addMicronutrient)

            val adapter = MicronutrientAdapter<MicronutrientRecording>()

            microList.layoutManager = LinearLayoutManager(view.context)
            microList.adapter = adapter

            addMicro.setOnClickListener {
                adapter.items.add(MicronutrientRecording())
                adapter.notifyDataSetChanged()
            }

            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton(R.string.save_recording_ok) { _, _ -> onSave(adapter.items, view) }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
            return builder.create()
        }
    }
}