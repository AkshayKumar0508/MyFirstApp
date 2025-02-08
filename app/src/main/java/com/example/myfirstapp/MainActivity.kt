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

        val buttonExplicit = findViewById<Button>(R.id.explicitButton)
        val buttonImplicit = findViewById<Button>(R.id.implicitButton)

        // Button to start second activity explicitly
        buttonExplicit.setOnClickListener {
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
        }

        // Button to start second activity implicitly
        buttonImplicit.setOnClickListener {
            val implicitIntent = Intent("com.example.ACTION_VIEW_SECOND_ACTIVITY")
            startActivity(implicitIntent)
        }
    }
}

