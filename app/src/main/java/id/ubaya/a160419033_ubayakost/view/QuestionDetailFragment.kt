package id.ubaya.a160419033_ubayakost.view

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.QuestionDetailViewModel

class QuestionDetailFragment : Fragment() {
    private lateinit var viewModel: QuestionDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(QuestionDetailViewModel::class.java)

        if (arguments != null) {
            val faqId = QuestionDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel.fetch(faqId.toString())
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.questionsLiveData.observe(viewLifecycleOwner) {
            val layout = view?.findViewById<LinearLayout>(R.id.frameLayout2)
            var arrQuestion: ArrayList<String> = ArrayList()

            for (i in it.indices) {
                arrQuestion.add(it[i].question)
                arrQuestion.add(it[i].answer)
            }
            for (i in arrQuestion.indices) {
                var textView = TextView(context)
                if (i % 2 == 0) {
                    textView.setText(arrQuestion[i])
                    textView.setTypeface(null, Typeface.BOLD)
                } else {
                    textView.setText(arrQuestion[i])
                    textView.setTypeface(null, Typeface.NORMAL)
                }
                textView.setTextSize(20f)
                textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                layout?.addView(textView)
            }
        }
    }
}