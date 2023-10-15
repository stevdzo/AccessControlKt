package com.stevdzo.accesscontrolapp.common.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StrictMode
import android.os.StrictMode.VmPolicy

class EmailManager(
    private val context: Context
) {

    fun composeEmail(addresses: Array<String>, subject: String, text: String, attachment: Uri) {

        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_STREAM, attachment)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
       context.startActivity(Intent.createChooser(intent, "Choose an Email client: "))
    }
}