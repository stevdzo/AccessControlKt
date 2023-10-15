package com.stevdzo.accesscontrolapp.common.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.stevdzo.accesscontrolapp.constants.Constants
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QRCodeBitmap(
    private val code: String
) {
    fun getBitmapFromCode(): Bitmap {
        val matrix = MultiFormatWriter().encode(
            code,
            BarcodeFormat.QR_CODE,
            500,
            500
        )
        return BarcodeEncoder().createBitmap(matrix)
    }

    fun saveBitmapToFileAndGetUri(context: Context, bitmap: Bitmap): Uri {
        val timeStamp =
            SimpleDateFormat(Constants.TIMESTAMP_FORMAT, Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_$timeStamp.jpg"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val imageFile = File(storageDir, imageFileName)
        val fos = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.close()

        return FileProvider.getUriForFile(
            context,
            Constants.AUTHORITY_PROVIDER,
            imageFile
        )
    }
}