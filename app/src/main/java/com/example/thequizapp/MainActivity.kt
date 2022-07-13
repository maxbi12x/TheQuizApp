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
import com.example.thequizapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener{

            if(binding.inputName.text.toString().isEmpty())
                Toast.makeText(this,"Enter your name",Toast.LENGTH_SHORT).show()
            else
            {
                val intent = Intent(this,quizQuestionActivity::class.java)
                intent.putExtra(InputConstantsQues.USER_NAME,binding.inputName.text.toString())
                startActivity(intent)
                finish()
            }

        }
    }
}