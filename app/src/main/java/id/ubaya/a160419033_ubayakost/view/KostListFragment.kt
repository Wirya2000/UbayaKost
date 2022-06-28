package id.ubaya.a160419033_ubayakost.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.kost_list_item.*

class KostListFragment : Fragment() {
    private lateinit var viewModel: KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = kostListAdapter

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        cardKost.setOnClickListener {
            val action = KostListFragmentDirections.actionItemExploreToKostDetailFragment(view.tag.toString().toInt())
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.kostsLiveData.observe(viewLifecycleOwner) {
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostsLoadErrorLiveData.observe(viewLifecycleOwner) {
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