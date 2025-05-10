package com.example.marketpets.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

class IngresarActivity : AppCompatActivity() {

    private lateinit var loginButton: MaterialButton
    private lateinit var signupButton: MaterialButton
    private lateinit var restoreButton: MaterialButton
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        firebaseAuth = Firebase.auth

        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnIniciarSesion)
        signupButton = findViewById(R.id.btnRegistrate)
        restoreButton = findViewById(R.id.btnRestore)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateFields(email, password)) {
                loginUser(email, password)
            }
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        restoreButton.setOnClickListener {
            startActivity(Intent(this, RestaurarActivity::class.java))
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            emailEditText.error = getString(R.string.email_required)
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = getString(R.string.invalid_email)
            return false
        }

        if (password.isEmpty()) {
            passwordEditText.error = getString(R.string.password_required)
            return false
        }

        return true
    }

    private fun loginUser(email: String, password: String) {
        loginButton.isEnabled = false

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                loginButton.isEnabled = true

                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> getString(R.string.user_not_registered)
                        is FirebaseAuthInvalidCredentialsException -> getString(R.string.invalid_credentials)
                        else -> getString(R.string.login_error, task.exception?.message)
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}
