package com.example.marketpets.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.marketpets.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_app)

        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@MainActivity, BienvenidaActivity::class.java))
            finish()
        }
    }
}