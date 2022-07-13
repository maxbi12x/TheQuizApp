package com.example.thequizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.thequizapp.databinding.ActivityQuizQuestionBinding
import org.w3c.dom.Text

class quizQuestionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuizQuestionBinding
    private var CurrentPosition: Int = 1
    private var Questions: ArrayList<QuestPgDataFrmtCls>? = null
    private var SelectedOption = 0
    private var score : Int =0
    private var bts =true
    private var username:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username=intent.getStringExtra(InputConstantsQues.USER_NAME)
        Questions = InputConstantsQues.getQuestion()
        setQuestion()
        binding.SubmitB.setOnClickListener{
            val ques: QuestPgDataFrmtCls = Questions!![CurrentPosition - 1]
            if(SelectedOption==0)
                Toast.makeText(this,"Please select a option",Toast.LENGTH_SHORT).show()
            else if(bts)
            {

                if(highlighter())
                    score++


                bts=false
                if(CurrentPosition!=Questions!!.size)
                binding.SubmitB.setText("Click To Proceed")
                else
                    binding.SubmitB.setText("FINISH")

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
                binding.SubmitB.setText("SUBMIT")
            }


        }

    }

    private fun setQuestion() {
        val ques: QuestPgDataFrmtCls = Questions!![CurrentPosition - 1]
        binding.progressBar.progress = CurrentPosition
        binding.progressTv.text = "$CurrentPosition"
        binding.quesImage.setImageResource(ques.image)
        binding.questionTv.text = ques.question

        binding.option1.text = ques.choice1
        binding.option2.text = ques.choice2
        binding.option3.text = ques.choice3
        binding.option4.text = ques.choice4
    }
    private fun optionNormalizer()
    {
        val options= ArrayList<TextView>()
        options.add(0,binding.option1)
        options.add(1,binding.option2)
        options.add(2,binding.option3)
        options.add(3,binding.option4)
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
        options.add(0,binding.option1)
        options.add(1,binding.option2)
        options.add(2,binding.option3)
        options.add(3,binding.option4)
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
        options.add(0,binding.option1)
        options.add(1,binding.option2)
        options.add(2,binding.option3)
        options.add(3,binding.option4)
        options[selectedOption-1].background= ContextCompat.getDrawable(this,R.drawable.red)
        options[ques.answer-1].background= ContextCompat.getDrawable(this,R.drawable.green)
        return false
    }
    private fun highlightgreen(ques: QuestPgDataFrmtCls): Boolean {
        val options= ArrayList<TextView>()
        options.add(0,binding.option1)
        options.add(1,binding.option2)
        options.add(2,binding.option3)
        options.add(3,binding.option4)
        options[ques.answer-1].background= ContextCompat.getDrawable(this,R.drawable.green)
        return true
    }


}