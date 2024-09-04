package com.example.belykhni_pr_31_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var log: EditText
    private lateinit var pass: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        log = findViewById<EditText>(R.id.usernameEditText)
        pass = findViewById(R.id.userpasswordEditText)
        loginButton = findViewById<Button>(R.id.loginButton)
        getUserInfo()
        loginButton.setOnClickListener(View.OnClickListener {
            if (log.text.toString() == "ekts" && pass.text.toString() == "ekts2024") {
                val username = log.getText().toString()
                if (username.isEmpty()) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Введите логин",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    saveUserInfo(username)
                    startActivity(Intent(this@LoginActivity, CountPriceActivity::class.java))
                }
            }
            else
                Toast.makeText(this, "Не верный логин или пароль", Toast.LENGTH_SHORT).show()
        })
    }

    private fun saveUserInfo(username: String) {
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    private fun getUserInfo(){
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        log.setText(sharedPreferences.getString("username", ""))
    }
}