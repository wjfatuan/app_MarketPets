package com.example.marketpets

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_app)

        val Laries_Regular = findViewById<TextView>(R.id.Laries_Regular)
        //val Raleway_Regular = findViewById<TextView>(R.id.Raleway_Regular)
        ///val Raleway_Bold = findViewById<TextView>(R.id.Raleway_Bold)
        //val Raleway_Italic = findViewById<TextView>(R.id.Raleway_Italic)

        Laries_Regular.typeface = ResourcesCompat.getFont(this, R.font.laries_regular)
        //Raleway_Regular.typeface = ResourcesCompat.getFont(this, R.font.raleway_regular)
        //Raleway_Bold.typeface = ResourcesCompat.getFont(this, R.font.raleway_bold)
        //Raleway_Italic.typeface = ResourcesCompat.getFont(this, R.font.raleway_italic)


    }
}