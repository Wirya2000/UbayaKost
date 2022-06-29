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
import androidx.recyclerview.widget.LinearLayoutManager
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.QuestionDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*

class QuestionDetailFragment : Fragment() {
    private lateinit var viewModel: QuestionDetailViewModel
    private val questionDetailAdapter = QuestionDetailAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(QuestionDetailViewModel::class.java)
        viewModel.fetch(view.tag.toString().toInt())

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = questionDetailAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.questionsLiveData.observe(viewLifecycleOwner) {
            questionDetailAdapter.updateQuestionList(it)
        }
    }
}