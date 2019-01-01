package com.awave.wrkout.dashboard.profile

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.awave.wrkout.R
import com.awave.wrkout.util.Utils
import java.util.*

class CreateGoalDialogFactory {
    companion object {
        fun create(
                layout: Int,
                layoutInflater: LayoutInflater,
                context: Context,
                onSave: (date: Date, description: String) -> Unit,
                onCancel: (dialog: DialogInterface, id: Int) -> Unit
        ): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val view = View.inflate(context, layout, null)
            val date = view.findViewById<EditText>(R.id.goalDate)
            val desc = view.findViewById<TextInputLayout>(R.id.goalDescInputContainer).editText

            val calendar = Calendar.getInstance()
            var pickedDate = Date()

            date.setOnClickListener {
                DatePickerDialog(
                        it.context,
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            pickedDate = calendar.time

                            date.hint = Utils.formatDate(pickedDate, format = "dd/MM/yyyy")
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton(R.string.save_recording_ok) { dialog, id -> onSave(pickedDate, desc?.text.toString()) }
                    .setNegativeButton(R.string.cancel) { dialog, id -> onCancel(dialog, id) }
            return builder.create()
        }
    }
}