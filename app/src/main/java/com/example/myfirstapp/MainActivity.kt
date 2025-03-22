package com.example.myfirstapp

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val buttonExplicit = findViewById<Button>(R.id.explicitButton)
        //val buttonImplicit = findViewById<Button>(R.id.implicitButton)
        val explicitButton = findViewById<Button>(R.id.explicitButton)
        val implicitButton = findViewById<Button>(R.id.implicitButton)
        val viewImageActivityBtn: Button = findViewById(R.id.ViewImageActivityButton)


        // Button to start second activity explicitly
        explicitButton.setOnClickListener {
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
        }

        // Button to start second activity implicitly
        implicitButton.setOnClickListener {
           // val implicitIntent = Intent("com.example.ACTION_VIEW_SECOND_ACTIVITY")
           val implicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(implicitIntent)
        }
        // Button to start third activity
        viewImageActivityBtn.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }
    }
}

