package com.example.thequizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val name=intent.getStringExtra(InputConstantsQues.USER_NAME)
        val get=intent.getStringExtra(InputConstantsQues.GOT_MARKS)
        val max=intent.getStringExtra(InputConstantsQues.MAX_MARKS)
        val tv_name:TextView=findViewById(R.id.tv_name)
        tv_name.setText("$name")
        val tv_score:TextView=findViewById(R.id.tv_score)
        tv_score.setText("Your Score is ${get} out of ${max}")
        val btn_finish: Button =findViewById(R.id.btn_finish)
        btn_finish.setOnClickListener{
            finish()
        }

    }
}