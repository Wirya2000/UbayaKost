package id.ubaya.a160419033_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*

class QuestionListFragment : Fragment() {
    private lateinit var viewModel: QuestionListViewModel
    private val faqListAdapter = QuestionListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(QuestionListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = faqListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.faqsLiveData.observe(viewLifecycleOwner) {
            faqListAdapter.updateFAQList(it)
        }
        viewModel.faqsLoadErrorLiveData.observe(viewLifecycleOwner) {
            textError.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }

}