package com.ageinminutes.quizapp

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
         val que1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "India",
            "Austria",
            1
        )
        questionsList.add(que1)


        val que2 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Pakistan",
            "Australia",
            "India",
            "Austria",
            2
        )
        questionsList.add(que2)
        val que3 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Pakistan",
            "Australia",
            "Belgium",
            "Austria",
            3
        )
        questionsList.add(que3)
        val que4 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "India",
            "Australia",
            "Belgium",
            "Austria",
            1
        )
        questionsList.add(que4)

        return questionsList
    }

}