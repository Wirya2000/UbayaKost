package id.ubaya.a160419033_ubayakost.view

import android.app.Application
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
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.buildDb
import id.ubaya.a160419033_ubayakost.viewmodel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.kost_list_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get
import kotlin.coroutines.CoroutineContext

class KostListFragment : Fragment(), CoroutineScope {
    private lateinit var viewModel: KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

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

//

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