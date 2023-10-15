package com.stevdzo.accesscontrolapp.common.util

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class CameraFactory(
    private val context: Context,
    private val owner: LifecycleOwner) {

    private lateinit var cameraProviderFeature: ProcessCameraProvider

    private val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

   fun setupCameraFactory(onCodeRead: (ScanResult) -> Unit): PreviewView {
       cameraProviderFeature = ProcessCameraProvider.getInstance(context).get()

       val previewView = PreviewView(context)
       previewView.scaleType = PreviewView.ScaleType.FILL_START
       val preview = Preview.Builder()
           .build()
       val selector = CameraSelector.Builder()
           .requireLensFacing(CameraSelector.LENS_FACING_BACK)
           .build()
       preview.setSurfaceProvider(previewView.surfaceProvider)

       imageAnalysis.setAnalyzer(
           ContextCompat.getMainExecutor(context),
           ScanManager {
               onCodeRead(ScanResult(true, it))
           }
       )
       try {
           cameraProviderFeature.bindToLifecycle(
               owner,
               selector,
               preview,
               imageAnalysis
           )
       } catch (e: Exception) {
           e.printStackTrace()
       }
       return previewView
   }

    fun clearAnalyzer() {
        imageAnalysis.clearAnalyzer()
    }
}