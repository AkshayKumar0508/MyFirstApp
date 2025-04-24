package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    // Permission for requesting MSE412 permission
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, start SecondActivity
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
        }
        else
        {
            // Permission denied, then show a message
            Toast.makeText(this, "Permission denied to access SecondActivity", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val explicitButton = findViewById<Button>(R.id.explicitButton)
        val implicitButton = findViewById<Button>(R.id.implicitButton)
        val viewImageActivityBtn: Button = findViewById(R.id.ViewImageActivityButton)



        // Button to start second activity explicitly
        explicitButton.setOnClickListener {

            // Check and request permission before starting SecondActivity
            if (ContextCompat.checkSelfPermission(this,"com.example.myfirstapp.MSE412") == PackageManager.PERMISSION_GRANTED)
            {
                // Permission granted
                val explicitIntent = Intent(this, SecondActivity::class.java)
                startActivity(explicitIntent)
            }
            else
            {
                // Request permission
                permissionLauncher.launch("com.example.myfirstapp.MSE412")
            }

        }

        // Button to start second activity implicitly
        implicitButton.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this, "com.example.myfirstapp.MSE412") == PackageManager.PERMISSION_GRANTED)
                {
                    val implicitIntent = Intent("com.example.myfirstapp.ACTION_SHOW_SECOND").apply {
                        addCategory(Intent.CATEGORY_DEFAULT)
                    }
                    if (implicitIntent.resolveActivity(packageManager) != null) {
                        startActivity(implicitIntent)
                    }
                }
                else
                {
                    permissionLauncher.launch("com.example.myfirstapp.MSE412")
                }

                // Button to start third activity
                viewImageActivityBtn.setOnClickListener {
                    val intent = Intent(this, ImageActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }



