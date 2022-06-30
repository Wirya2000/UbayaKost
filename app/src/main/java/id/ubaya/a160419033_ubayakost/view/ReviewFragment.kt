package id.ubaya.a160419033_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.fragment_review.progressLoad
import kotlinx.android.synthetic.main.fragment_review.recView
import kotlinx.android.synthetic.main.fragment_review.textError

class ReviewFragment : Fragment() {
    private lateinit var viewModel: ReviewViewModel
    private val reviewAdapter = ReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kostId = ReviewFragmentArgs.fromBundle(requireArguments()).kostID
//        var kostId = view.tag.toString().toInt()
        viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        viewModel.refresh(kostId)

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = reviewAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh(kostId)
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.reviewsLiveData.observe(viewLifecycleOwner) {
            reviewAdapter.updateReviewList(it)
        }
        viewModel.reviewsLoadErrorLiveData.observe(viewLifecycleOwner) {
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