package com.example.belykhni_pr_31_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private lateinit var restext: TextView
    private lateinit var countm: TextView
    private lateinit var regb: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        restext = findViewById(R.id.resText)
        countm = findViewById(R.id.countM)
        regb = findViewById(R.id.regButton)
        val result = intent.getStringExtra("Result")
        restext.text = "$result руб."
        val count = intent.getStringExtra("CountM")
        countm.text = "$count кв. м."
        regb.setOnClickListener {
            startActivity(Intent(this@ResultActivity, LoginActivity::class.java))
        }
    }
}