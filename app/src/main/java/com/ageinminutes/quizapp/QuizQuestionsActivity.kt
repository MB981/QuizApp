package com.ageinminutes.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ageinminutes.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityQuizQuestionsBinding
    private var mQuestionsList: ArrayList<Question>? = null
    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        mQuestionsList = Constants.getQuestions()
        Log.e("CONSTANTS_QUESTIONS", "QUESTIONS -> ${Constants.getQuestions()}")
        setQuestion()

        mBinding.tvOptionOne.setOnClickListener(this)
        mBinding.tvOptionTwo.setOnClickListener(this)
        mBinding.tvOptionThree.setOnClickListener(this)
        mBinding.tvOptionFour.setOnClickListener(this)
        mBinding.btnSubmit.setOnClickListener(this)

    }

    private fun setQuestion() {

        val question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            mBinding.btnSubmit.text = "FINISH"
        } else {
            mBinding.btnSubmit.text = "SUBMIT"
        }

        mBinding.progressBar.progress = mCurrentPosition
        mBinding.tvProgress.text = "$mCurrentPosition" + "/" + mBinding.progressBar.getMax()

        mBinding.tvQuestion.text = question.question
        mBinding.ivImage.setImageResource(question.image)
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

                selectedOptionView( mBinding.tvOptionTwo, 2)
            }

            R.id.tvOptionThree -> {

                selectedOptionView(mBinding.tvOptionThree, 3)
            }

            R.id.tvOptionFour -> {

                selectedOptionView(mBinding.tvOptionFour, 4)
            }

            R.id.btnSubmit -> {


                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                            // START
//                            val intent =
//                                Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
//                            intent.putExtra(Constants.USER_NAME, mUserName)
//                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
//                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
//                            startActivity(intent)
//                            finish()
                            // END
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        mBinding.btnSubmit.text = "FINISH"
                    } else {
                        mBinding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                mBinding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                mBinding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                mBinding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                mBinding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
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