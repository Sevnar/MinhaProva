package com.example.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class SendMessageDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Gostaria de uma xícara de café?")
                .setPositiveButton("SIM",
                DialogInterface.OnClickListener{dialog, id ->
                    Toast.makeText(it.baseContext, "Ótimo.", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("NÃO",
                DialogInterface.OnClickListener{dialog, id ->
                    Toast.makeText(it.baseContext, "Fica pra próxima.", Toast.LENGTH_SHORT).show()
                })
                .setTitle("Pergunta importante")
        builder.create()
        }?: throw IllegalStateException("Não pode ser null")

    }
}