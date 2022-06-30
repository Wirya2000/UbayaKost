package id.ubaya.a160419033_ubayakost.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.FragmentKostDetailBinding
import id.ubaya.a160419033_ubayakost.databinding.KostListItemBinding
import id.ubaya.a160419033_ubayakost.model.Booking
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.BookingWorker
import id.ubaya.a160419033_ubayakost.util.loadImage
import id.ubaya.a160419033_ubayakost.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class KostDetailFragment : Fragment(), KostReviewClickListener, KostBookingClickListener, MapClickListener {
    private lateinit var viewModel: KostDetailViewModel
    private lateinit var dataBinding: FragmentKostDetailBinding
    private val binding get() = dataBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentKostDetailBinding>(inflater, R.layout.fragment_kost_detail,
            container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)

        dataBinding.reviewListener = this
        dataBinding.bookingListener = this
        dataBinding.mapListener = this

        if (arguments != null) {
            val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).kostID
            viewModel.fetch(kostId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            dataBinding.kost = it
            }
        }

    override fun onBookingClick(v: View, obj: Kost) {
        dataBinding.kost = obj

        viewModel.updateKostToBooking(dataBinding.kost!!)

        val bookingWorkRequest = OneTimeWorkRequestBuilder<BookingWorker>()
            .setInputData(workDataOf(
                "title" to "Booking has been made",
                "message" to "Don't forget to complete your payment for the booking that you made"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(bookingWorkRequest)
    }

    override fun onReviewClick(v: View) {
        val kostId = v.tag.toString().toInt()
        val action = KostDetailFragmentDirections.actionToReviewFragment(kostId)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onMapClick(v: View, obj: Kost) {
        val kostId = v.tag.toString()
        val action = KostDetailFragmentDirections.actionToMapFragment(kostId)
        Navigation.findNavController(v).navigate(action)
    }
}