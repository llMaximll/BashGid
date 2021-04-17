package com.github.llmaximll.bashgid.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.github.llmaximll.bashgid.MainActivity
import com.github.llmaximll.bashgid.OnBackPressedFragment
import com.github.llmaximll.bashgid.ObjectsForTest
import com.github.llmaximll.bashgid.R

class ResultTestFragment : Fragment(), OnBackPressedFragment {

    interface Callbacks {
        fun onResultTest(backPressed: Boolean)
    }

    private lateinit var countTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var lottieAnimationView: LottieAnimationView

    private var resultList = mutableListOf<ResultTest>()

    private var count = 0

    private var callbacks: Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val test = ObjectsForTest(requireContext())
        when (MainActivity.test) {
            0 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    var isTrue = false
                    if (testAnswer == test.testCityList[index]) {
                        count++
                        isTrue = true
                    }
                    val correctAnswer = test.testCityAnswersAll[index][MainActivity.testAnswersList[index] - 1]
                    resultList.add(
                            ResultTest(
                                    test.testCityQuestionsList[index],
                                    correctAnswer,
                                    test.testCityCorrectAnswersList[index],
                                    isTrue
                            )
                    )
                }
            }
            1 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    var isTrue = false
                    if (testAnswer == test.testMonumentList[index]) {
                        count++
                        isTrue = true
                    }
                    val correctAnswer = test.testMonumentAnswersAll[index][MainActivity.testAnswersList[index] - 1]
                    resultList.add(
                            ResultTest(
                                    test.testMonumentQuestionsList[index],
                                    correctAnswer,
                                    test.testMonumentCorrectAnswersList[index],
                                    isTrue
                            )
                    )
                }
            }
            2 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    var isTrue = false
                    if (testAnswer == test.testMonumentList[index]) {
                        count++
                        isTrue = true
                    }
                    val correctAnswer = test.testMonumentAnswersAll[index][MainActivity.testAnswersList[index] - 1]
                    resultList.add(
                            ResultTest(
                                    test.testMonumentQuestionsList[index],
                                    correctAnswer,
                                    test.testMonumentCorrectAnswersList[index],
                                    isTrue
                            )
                    )
                }
            }
            3 -> {
                MainActivity.testAnswersList.forEachIndexed { index, testAnswer ->
                    var isTrue = false
                    if (testAnswer == test.testMonumentList[index]) {
                        count++
                        isTrue = true
                    }
                    val correctAnswer = test.testMonumentAnswersAll[index][MainActivity.testAnswersList[index] - 1]
                    resultList.add(
                            ResultTest(
                                    test.testMonumentQuestionsList[index],
                                    correctAnswer,
                                    test.testMonumentCorrectAnswersList[index],
                                    isTrue
                            )
                    )
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result_test, container, false)

        countTextView = view.findViewById(R.id.count_text_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        lottieAnimationView = view.findViewById(R.id.lottie_animation)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ResultAdapter(resultList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(count)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onBackPressed(): Boolean {
        MainActivity.testAnswersList.clear()
        MainActivity.test = -1
        MainActivity.question = 0
        callbacks?.onResultTest(true)
        return false
    }

    private fun updateUI(count: Int) {
        when {
            count <= 10 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                countTextView.text = "$count"
            }
            count in 11..17 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                countTextView.text = "$count"
            }
            count >= 18 -> {
                countTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                countTextView.text = "$count"
                lottieAnimationView.isVisible = true
                lottieAnimationView.playAnimation()
            }
        }
    }

    private inner class ResultHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

            private val questionTextView: TextView = itemView.findViewById(R.id.title_text_view)
            private val yourAnswerTextView: TextView = itemView.findViewById(R.id.your_answer_text_view)
            private val correctAnswerTextView: TextView = itemView.findViewById(R.id.correct_answer_text_view)
            private val booleanTextView: TextView = itemView.findViewById(R.id.boolean_text_view)
            private val correctAnswerHint: TextView = itemView.findViewById(R.id.correct_answer_1_text_view)
            private val correctAnswer: TextView = itemView.findViewById(R.id.correct_answer_text_view)

            fun bind(result: ResultTest) {
                questionTextView.text = result.title
                yourAnswerTextView.text = result.yourAnswer
                correctAnswerTextView.text = result.correctAnswer
                if (result.answerBoolean) {
                    booleanTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                    booleanTextView.text = "Верно"
                    correctAnswerHint.visibility = View.GONE
                    correctAnswer.visibility = View.GONE
                } else {
                    booleanTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    booleanTextView.text = "Неверно"
                    correctAnswerHint.isVisible = true
                    correctAnswer.isVisible = true
                }
            }
    }

    private inner class ResultAdapter(var resultList: List<ResultTest>)
        : RecyclerView.Adapter<ResultHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
            val view = layoutInflater.inflate(R.layout.item_result_test, parent, false)
            return ResultHolder(view)
        }

        override fun onBindViewHolder(holder: ResultHolder, position: Int) {
            val result = resultList[position]
            holder.bind(result)
        }

        override fun getItemCount(): Int = resultList.size
    }

    private data class ResultTest(var title: String = "",
                                  var yourAnswer: String,
                                  var correctAnswer: String,
                                  var answerBoolean: Boolean)

    companion object {
        fun newInstance(): ResultTestFragment {
            return ResultTestFragment()
        }
    }
}