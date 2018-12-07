package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.MicronutrientRecording

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
    }
}