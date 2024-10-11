package com.example.mapsaplicacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById(R.id.activity_main_maps)

        boton.setOnClickListener(){onClick()}
    }

    private fun onClick() {
        val intent: Intent?
        intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}