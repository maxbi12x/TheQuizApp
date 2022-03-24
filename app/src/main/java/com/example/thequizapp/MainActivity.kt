package com.example.thequizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton : Button = findViewById(R.id.start)
        startButton.setOnClickListener{
            val name: TextInputEditText =findViewById(R.id.inputName)
            if(name.text.toString().isEmpty())
                Toast.makeText(this,"Enter your name",Toast.LENGTH_SHORT).show()
            else
            {
                val intent = Intent(this,quizQuestionActivity::class.java)
                intent.putExtra(InputConstantsQues.USER_NAME,name.text.toString())
                startActivity(intent)
                finish()
            }

        }
    }
}