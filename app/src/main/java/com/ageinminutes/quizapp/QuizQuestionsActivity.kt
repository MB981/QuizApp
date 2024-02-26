package com.ageinminutes.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ageinminutes.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityQuizQuestionsBinding
    private var mQuestionsList: ArrayList<Question>? = null
    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        mQuestionsList = Constants.getQuestions()
        Log.e("CONSTANTS_QUESTIONS", "QUESTIONS -> ${Constants.getQuestions()}")

        mBinding.tvOptionOne.setOnClickListener(this)
        mBinding.tvOptionTwo.setOnClickListener(this)
        mBinding.tvOptionThree.setOnClickListener(this)
        mBinding.tvOptionFour.setOnClickListener(this)
        setQuestions()

    }

    private fun setQuestions() {
        mCurrentPosition = 1
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()
        mBinding.tvProgress.text = "$mCurrentPosition" + "/" + mBinding.progressBar.max
        mBinding.tvQuestion.text = question.question
        mBinding.tvOptionOne.text = question.optionOne
        mBinding.tvOptionTwo.text = question.optionTwo
        mBinding.tvOptionThree.text = question.optionThree
        mBinding.tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, mBinding.tvOptionOne)
        options.add(1, mBinding.tvOptionTwo)
        options.add(2, mBinding.tvOptionThree)
        options.add(3, mBinding.tvOptionFour)
        for (option in options) {
            option.setTextColor(getColor(R.color.grey_7A))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

        }


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(mBinding.tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(mBinding.tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(mBinding.tvOptionThree, 3)
            }
            R.id.tvOptionFour -> {
                selectedOptionView(mBinding.tvOptionFour, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(getColor(R.color.grey_36))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }
}