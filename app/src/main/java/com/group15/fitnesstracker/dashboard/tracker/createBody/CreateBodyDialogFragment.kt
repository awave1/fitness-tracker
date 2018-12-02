package com.group15.fitnesstracker.dashboard.tracker.createBody

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.group15.fitnesstracker.R

class CreateBodyDialogFragment : DialogFragment() {
    // Use this instance of the interface to deliver action events
    private lateinit var mListener: NoticeDialogListener

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(layoutInflater.inflate(R.layout.fragment_create_body, null))
                    // Add action buttons
                    .setPositiveButton(R.string.create_text,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the positive button event back to the host activity
                                mListener.onDialogPositiveClick(this)
                            })
                    .setNegativeButton(R.string.cancel_text,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the negative button event back to the host activity
                                mListener.onDialogNegativeClick(this)
                            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }


}