package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ubaya.a160419033_ubayakost.model.Booking
import id.ubaya.a160419033_ubayakost.model.BookingDao
import id.ubaya.a160419033_ubayakost.model.BookingWithKost
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookingViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val bookingLiveData = MutableLiveData<BookingWithKost>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(userId: Int) {
        launch {
            val db = buildDb(getApplication())
            val fetched = db.bookingDao().selectAllBooking(userId)
            if (fetched != null) {
                bookingLiveData.value = fetched
            } else {
                bookingLiveData.value = null
            }
        }
    }
}