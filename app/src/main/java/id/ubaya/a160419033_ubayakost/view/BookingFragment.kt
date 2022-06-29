package id.ubaya.a160419033_ubayakost.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.FragmentBookingBinding
import id.ubaya.a160419033_ubayakost.databinding.FragmentKostDetailBinding
import id.ubaya.a160419033_ubayakost.util.loadImage
import id.ubaya.a160419033_ubayakost.viewmodel.BookingViewModel
import kotlinx.android.synthetic.main.fragment_booking.*

class BookingFragment : Fragment() {
    companion object {
        var SHARED_BOOKING_ID = "SHARED_BOOKING_ID"
    }
    private lateinit var viewModel: BookingViewModel
    private lateinit var dataBinding: FragmentBookingBinding
    private val binding get() = dataBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BookingViewModel::class.java)
        viewModel.fetch(1)

        observeViewModel(view.tag.toString().toInt())
    }

    private fun observeViewModel(kostId: Int) {
        viewModel.bookingLiveData.observe(viewLifecycleOwner) {
            val bookingKost = it
            if (bookingKost == null) {
                textInfoBooking.visibility = View.VISIBLE
                cardKost.visibility = View.GONE
            } else {
                bookingKost?.let {
                    textInfoBooking.visibility = View.GONE
                    cardKost.visibility = View.VISIBLE

                    dataBinding.booking = it
                    cardKost.setOnClickListener{
                        val action = BookingFragmentDirections.actionItemBookingToKostDetailFragment(kostId)
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }
    }

//    fun loadData() {
//        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//        val bookingID = sharedPreferences.getString(SHARED_BOOKING_ID, null)
//        if (bookingID == null) {
//            textInfoBooking.visibility = View.VISIBLE
//            cardKost.visibility = View.GONE
//        } else {
//            textInfoBooking.visibility = View.GONE
//            cardKost.visibility = View.VISIBLE
//            cardKost.setOnClickListener{
//                val action = BookingFragmentDirections.actionItemBookingToKostDetailFragment()
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
//    }


}