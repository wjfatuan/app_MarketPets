package com.example.marketpets.Views.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.marketpets.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class BienvenidaActivity : AppCompatActivity() {
    private var isActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)
        lifecycleScope.launch {
            delay(1000)
            if (isActive) {
                startActivity(Intent(this@BienvenidaActivity, IngresarActivity::class.java))
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isActive = false
    }
}

