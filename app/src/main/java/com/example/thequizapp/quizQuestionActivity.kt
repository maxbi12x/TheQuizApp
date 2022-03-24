package com.example.thequizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class quizQuestionActivity : AppCompatActivity() {
    private var CurrentPosition: Int = 1
    private var Questions: ArrayList<QuestPgDataFrmtCls>? = null
    private var SelectedOption = 0
    private var score : Int =0
    private var bts =true
    private var username:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        username=intent.getStringExtra(InputConstantsQues.USER_NAME)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        Questions = InputConstantsQues.getQuestion()
        Log.i("Size Of Questions", "${Questions!!.size}")
        setQuestion()
        val button : Button =findViewById(R.id.SubmitB)
        button.setOnClickListener{
            val ques: QuestPgDataFrmtCls = Questions!![CurrentPosition - 1]
            if(SelectedOption==0)
                Toast.makeText(this,"Please select a option",Toast.LENGTH_SHORT).show()
            else if(bts)
            {

                if(highlighter())
                    score++


                bts=false
                if(CurrentPosition!=Questions!!.size)
                button.setText("Click To Proceed")
                else
                    button.setText("FINISH")

            }
            else if(CurrentPosition==Questions!!.size)
            {
                val intent = Intent(this,ResultActivity::class.java)
                intent.putExtra(InputConstantsQues.USER_NAME,username.toString())
                intent.putExtra(InputConstantsQues.GOT_MARKS,score.toString())
                intent.putExtra(InputConstantsQues.MAX_MARKS,Questions!!.size.toString())
                startActivity(intent)
                finish()
            }
            else
            {
                if(CurrentPosition< Questions!!.size) {
                    CurrentPosition++
                }
                setQuestion()
                optionNormalizer()
                bts=true
                button.setText("SUBMIT")
            }


        }

    }

    private fun setQuestion() {
        val ques: QuestPgDataFrmtCls = Questions!![CurrentPosition - 1]
        findViewById<ProgressBar>(R.id.progressBar).progress = CurrentPosition
        findViewById<TextView>(R.id.progressTv).text = "$CurrentPosition"
        findViewById<ImageView>(R.id.quesImage).setImageResource(ques.image)
        findViewById<TextView>(R.id.questionTv).text = ques.question
        val o1 = findViewById<TextView>(R.id.option1)
        val o2 = findViewById<TextView>(R.id.option2)
        val o3 = findViewById<TextView>(R.id.option3)
        val o4 = findViewById<TextView>(R.id.option4)
        o1.text = ques.choice1
        o2.text = ques.choice2
        o3.text = ques.choice3
        o4.text = ques.choice4
    }
    private fun optionNormalizer()
    {
        val options= ArrayList<TextView>()
        options.add(0,findViewById<TextView>(R.id.option1))
        options.add(1,findViewById<TextView>(R.id.option2))
        options.add(2,findViewById<TextView>(R.id.option3))
        options.add(3,findViewById<TextView>(R.id.option4))
        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(this,
                R.drawable.my_choice_b_c)
        }
        SelectedOption=0
    }

    fun selected(view: android.view.View) {
        if(!bts)
            return
        optionNormalizer()
        val options= ArrayList<TextView>()
        options.add(0,findViewById<TextView>(R.id.option1))
        options.add(1,findViewById<TextView>(R.id.option2))
        options.add(2,findViewById<TextView>(R.id.option3))
        options.add(3,findViewById<TextView>(R.id.option4))
        var i=1
        for(option in options)
        {
            if(option==view)
            {
                SelectedOption=i
                break
            }
            i++
        }

        view.background= ContextCompat.getDrawable(this,R.drawable.selected_view_bg)
    }
    private fun highlighter():Boolean{
        val ques: QuestPgDataFrmtCls = Questions!![CurrentPosition - 1]
        return if(SelectedOption==ques.answer)
        {
            highlightgreen(ques)
        }
        else
        {
            highlightred(SelectedOption,ques)
        }
    }
    private fun highlightred(selectedOption: Int, ques: QuestPgDataFrmtCls) : Boolean{
        val options= ArrayList<TextView>()
        options.add(0,findViewById<TextView>(R.id.option1))
        options.add(1,findViewById<TextView>(R.id.option2))
        options.add(2,findViewById<TextView>(R.id.option3))
        options.add(3,findViewById<TextView>(R.id.option4))
        options[selectedOption-1].background= ContextCompat.getDrawable(this,R.drawable.red)
        options[ques.answer-1].background= ContextCompat.getDrawable(this,R.drawable.green)
        return false
    }
    private fun highlightgreen(ques: QuestPgDataFrmtCls): Boolean {
        val options= ArrayList<TextView>()
        options.add(0,findViewById<TextView>(R.id.option1))
        options.add(1,findViewById<TextView>(R.id.option2))
        options.add(2,findViewById<TextView>(R.id.option3))
        options.add(3,findViewById<TextView>(R.id.option4))
        options[ques.answer-1].background= ContextCompat.getDrawable(this,R.drawable.green)
        return true
    }


}