package com.example.marketpets.Views.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marketpets.R

class CuentaInicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val Raleway_Regular = findViewById<TextView>(R.id.Raleway_Regular)
        val Raleway_Bold = findViewById<TextView>(R.id.Raleway_Bold)
        val Raleway_Italic = findViewById<TextView>(R.id.Raleway_Italic)

        Raleway_Regular.typeface = ResourcesCompat.getFont(this, R.font.raleway_regular)
        Raleway_Bold.typeface = ResourcesCompat.getFont(this, R.font.raleway_bold)
        Raleway_Italic.typeface = ResourcesCompat.getFont(this, R.font.raleway_italic)

        enableEdgeToEdge()
        setContentView(R.layout.activity_cuenta_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}