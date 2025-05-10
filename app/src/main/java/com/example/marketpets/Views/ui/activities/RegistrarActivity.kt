package com.example.marketpets.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrarActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference

    private lateinit var name: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var restoreButton: MaterialButton
    private lateinit var btnCrearCuenta: MaterialButton
    private lateinit var cuentaButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)

        firebaseAuth = Firebase.auth
        dbReference = Firebase.database.reference.child("User")

        val ralewayRegular = findViewById<TextView>(R.id.Raleway_Regular)
        val ralewayBold = findViewById<TextView>(R.id.Raleway_Bold)
        ralewayRegular.typeface = ResourcesCompat.getFont(this, R.font.raleway_regular)
        ralewayBold.typeface = ResourcesCompat.getFont(this, R.font.raleway_bold)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        name = findViewById(R.id.etNombre)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta)
        restoreButton = findViewById(R.id.btnRestore)
        cuentaButton = findViewById(R.id.tieneCuenta)

        btnCrearCuenta.setOnClickListener {
            val nameStr = name.text.toString().trim()
            val emailStr = email.text.toString().trim()
            val passwordStr = password.text.toString().trim()

            if (emailStr.isNotEmpty() && passwordStr.isNotEmpty() && nameStr.isNotEmpty()) {
                createUser(nameStr, emailStr, passwordStr)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        restoreButton.setOnClickListener {
            startActivity(Intent(this, RestaurarActivity::class.java))
        }

        cuentaButton.setOnClickListener {
            startActivity(Intent(this, IngresarActivity::class.java))
        }
    }

    private fun createUser(name: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                val userDb = dbReference.child(user?.uid.toString())
                userDb.child("name").setValue(name)

                startActivity(Intent(this, IngresarActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Este usuario no se puede registrar", Toast.LENGTH_LONG).show()
            }
        }
    }
}