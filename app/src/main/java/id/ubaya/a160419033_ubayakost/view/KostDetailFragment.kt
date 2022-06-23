package id.ubaya.a160419033_ubayakost.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.util.BookingWorker
import id.ubaya.a160419033_ubayakost.util.loadImage
import id.ubaya.a160419033_ubayakost.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class KostDetailFragment : Fragment() {
    companion object {
        var SHARED_BOOKING_ID = "SHARED_BOOKING_ID"
    }
    private lateinit var viewModel: KostDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)

        if (arguments != null) {
            val kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).kostID
            viewModel.fetch(kostId.toString())
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            var kost = it
            imageViewKostDetail.loadImage(it.photoUrl, progressLoadKostDetailImage)
            textName.text = it.name
            textGender.text = "Kos ${it.gender}"
            textRegion.text = it.region
            textAlamat.text = it.alamat
            imageButtonAlamat.setOnClickListener {
                val action = KostDetailFragmentDirections.actionToMapFragment(kost.x.toFloat(), kost.y.toFloat(), kost.name)
                Navigation.findNavController(it).navigate(action)
            }
            textFasilitas.text = it.fasilitas
            textLuasKamar.text = it.luas
            textPeraturan.text = it.peraturan
            buttonReview.setOnClickListener {
                val action = KostDetailFragmentDirections.actionToReviewFragment(kost.id)
                Navigation.findNavController(it).navigate(action)
            }
            textBookingPrice.text = "Rp ${it.price} / bulan"
            buttonBooking.setOnClickListener {
//                val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//                sharedPreferences.edit {
//                    putString(SHARED_BOOKING_ID, kost.id.toString())
//                }
                val bookingWorkRequest = OneTimeWorkRequestBuilder<BookingWorker>()
                    .setInputData(workDataOf(
                                    "title" to "Booking has been made",
                                    "message" to "Don't forget to complete your payment for the booking that you made"))
                    .build()
                WorkManager.getInstance(requireContext()).enqueue(bookingWorkRequest)

                val builder = AlertDialog.Builder(context)
                builder.setMessage("Booking has been made, Please check your booking at My Booking section")
                builder.setPositiveButton("OK", null)
                builder.create().show()
            }
        }
    }


}