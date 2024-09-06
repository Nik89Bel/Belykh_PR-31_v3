package com.example.belykhni_pr_31_03

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CountPriceActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var textView: TextView
    private lateinit var countm: EditText
    private lateinit var rasb: Button
    private lateinit var gobut: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_price)
        spinner = findViewById(R.id.spinner)
        textView = findViewById(R.id.textView)
        countm = findViewById(R.id.CountM)
        rasb = findViewById(R.id.rasButton)
        gobut = findViewById(R.id.goBut)
        gobut.setOnClickListener{
            startActivity(Intent(this@CountPriceActivity, LoginActivity::class.java))
        }
        val items = arrayOf("1. 1-о комнатная квартира", "2. 2-х комнатная квартира", "3. 3-х комнатная квартира", "4. Студия")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                textView.text = when (position) {
                    0 -> "1. 1-о комнатная квартира"
                    1 -> "2. 2-х комнатная квартира"
                    2 -> "3. 3-х комнатная квартира"
                    3 -> "4. Студия"
                    else -> "Ничего не выбрано"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        rasb.setOnClickListener {
            val type = spinner.selectedItem.toString()
            val meters = countm.text.toString().toDoubleOrNull() ?: 0
            val result = calculate(type, meters as Double)
            if (type.isNullOrEmpty()) {
                Toast.makeText(this, "Выберите тип квартиры", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (countm.text.toString().isEmpty()) {
                Toast.makeText(this, "Введите количество метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (meters < 0) {
                Toast.makeText(this, "Количество метров не может быть меньше нуля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var pravda = true
            when(type)
            {
                "1. 1-о комнатная квартира" -> if (meters < 15 || meters > 30) pravda = false
                "2. 2-х комнатная квартира" -> if (meters < 25 || meters > 50) pravda = false
                "3. 3-х комнатная квартира" -> if (meters < 40 || meters > 80) pravda = false
                "4. Студия" -> if (meters < 15 || meters > 30) pravda = false
            }
            if (pravda == false){
                Toast.makeText(this, "Не приемлимое количество метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("Result", result)
                intent.putExtra("CountM", meters.toString())
                startActivity(intent)
            }
        }
    }
    private fun calculate(type: String, meters: Double): String {
        val result = when (type) {
            "1. 1-о комнатная квартира" -> 80000 * meters * 1.4
            "2. 2-х комнатная квартира" -> 100000 * meters
            "3. 3-х комнатная квартира" -> 120000 * meters * 0.8
            "4. Студия" -> 75000 * meters * 1.1
            else -> "0"
        }
        return String.format("%.1f", result)
    }
}