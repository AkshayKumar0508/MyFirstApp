package com.example.myfirstapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ImageActivity : AppCompatActivity() {

    private  lateinit var captureImageBtn: Button
    private  lateinit var imageView: ImageView
    private val CAMERA_REQUEST_CODE = 200

    // ActivityResultLauncher for capturing images
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val photo: Bitmap = result.data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        captureImageBtn = findViewById(R.id.btnCaptureImage)
        imageView = findViewById(R.id.imageView)

        // Use cameraLauncher.launch(intent)
        captureImageBtn.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(cameraIntent)  // New way to launch camera
        }
    }

}