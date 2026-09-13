package com.example.loginactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Vector

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var btnLogin: Button
    private lateinit var tvMessage: TextView
    private lateinit var tvTitle: TextView

    companion object {
        private const val CORRECT_USERNAME = "admin"
        private const val CORRECT_PASSWORD = "password123"
        private const val KEY_USERNAME = "KEY_USERNAME"
        private const val KEY_PASSWORD = "KEY_PASSWORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        tilUsername = findViewById(R.id.tilUsername)
        tilPassword = findViewById(R.id.tilPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvMessage = findViewById(R.id.tvMessage)
        tvTitle = findViewById(R.id.tvTitle)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty()) {
                tilUsername.error = "Username is required"
                return@setOnClickListener
            } else {
                tilUsername.error = null
            }

            if (password.isEmpty()) {
                tilPassword.error = "Password is required"
                return@setOnClickListener
            } else {
                tilPassword.error = null
            }

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(KEY_USERNAME, username)
                putExtra(KEY_PASSWORD, password)
            }
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        val username = intent.getStringExtra(KEY_USERNAME) ?: return
        val password = intent.getStringExtra(KEY_PASSWORD) ?: return

        if (username == CORRECT_USERNAME && password == CORRECT_PASSWORD) {
            showWelcome(username)
        } else {
            showError()
        }
    }

    private fun showWelcome(username: String) {
        tvMessage.text = "Welcome, $username!"
        tvMessage.visibility = View.VISIBLE

        tvTitle.visibility = View.GONE
        tilUsername.visibility = View.GONE
        tilPassword.visibility = View.GONE
        etUsername.visibility = View.GONE
        etPassword.visibility = View.GONE
        btnLogin.visibility = View.GONE
    }

    private fun showError() {
        tvMessage.text = "Invalid username or password"
        tvMessage.visibility = View.VISIBLE

        etUsername.text?.clear()
        etPassword.text?.clear()

        tvTitle.visibility = View.VISIBLE
        tilUsername.visibility = View.VISIBLE
        tilPassword.visibility = View.VISIBLE
        btnLogin.visibility = View.VISIBLE
    }
}