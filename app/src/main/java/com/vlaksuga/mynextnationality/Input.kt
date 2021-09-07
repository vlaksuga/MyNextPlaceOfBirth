package com.vlaksuga.mynextnationality

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class Input : AppCompatActivity() {

    private lateinit var inputName: EditText
    private lateinit var inputBirth: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        inputName = findViewById(R.id.user_name_input)
        inputBirth = findViewById(R.id.user_birth_input)

        val geneButton = findViewById<Button>(R.id.gene_button)
        geneButton.setOnClickListener {

            // Validate name null or blank
            val userName: String = inputName.text.toString()
            if (userName.isBlank()) {
                Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate input length
            val inputLength = inputBirth.text.length
            if (inputLength != 8) {
                Toast.makeText(this, "생년월일을 8자리로 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate birth year
            val birthYear = inputBirth.text.slice(0..3).toString()
            val birthYearInt = birthYear.toInt()
            if (birthYearInt < 1900 || birthYearInt > 2020) {
                Toast.makeText(this, "출생년도를 확인해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate birth month
            val birthMonth = inputBirth.text.slice(4..5).toString()
            val birthMonthInt = birthMonth.toInt()
            if (birthMonthInt > 12 || birthMonthInt < 1) {
                Toast.makeText(this, "출생 월을 확인해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate birth date
            val birthDate = inputBirth.text.slice(6..7).toString()
            val birthDateInt = birthDate.toInt()
            if (birthDateInt > 31 || birthMonthInt < 1) {
                Toast.makeText(this, "출생 일을 확인해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // make index number
            val userBirth = inputBirth.text.toString().toInt()
            val calcNumber = (userBirth % 254) + 1


            // intent
            Intent(this, MainActivity::class.java).also {
                it.putExtra("userName", userName)
                it.putExtra("calcNumber", calcNumber)
                startActivity(it)
            }
        }

    }

}