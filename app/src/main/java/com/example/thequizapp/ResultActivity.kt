package com.example.thequizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.thequizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name=intent.getStringExtra(InputConstantsQues.USER_NAME)
        val get=intent.getStringExtra(InputConstantsQues.GOT_MARKS)
        val max=intent.getStringExtra(InputConstantsQues.MAX_MARKS)

        binding.tvName.setText("$name")

        binding.tvScore.setText("Your Score is ${get} out of ${max}")
        binding.btnFinish.setOnClickListener{
            finish()
        }

    }
}