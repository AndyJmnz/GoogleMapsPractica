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
    private lateinit var botonPoli: Button
    private lateinit var  botonApli: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById(R.id.activity_main_maps)
        botonPoli = findViewById(R.id.BotonPolilinea)
        botonApli = findViewById(R.id.BotonAplicacion)

        boton.setOnClickListener(){onClick()}
        botonPoli.setOnClickListener { onClickPoli() }
        botonApli.setOnClickListener { onClickApli() }
    }

    private fun onClick() {
        val intent: Intent?
        intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    private fun onClickPoli() {
        val intent: Intent?
        intent = Intent(this, MapsPoliActivity::class.java)
        startActivity(intent)
    }

    private fun onClickApli() {
        val intent: Intent?
        intent = Intent(this, GoogleMapsActivity::class.java)
        startActivity(intent)
    }
}