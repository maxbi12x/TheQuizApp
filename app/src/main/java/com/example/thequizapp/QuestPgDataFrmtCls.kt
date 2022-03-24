package com.example.thequizapp

data class QuestPgDataFrmtCls(
    val id: Int,
    val question : String,
    val image : Int,
    val choice1 : String,
    val choice2 : String,
    val choice3 : String,
    val choice4 : String,
    val answer : Int
)