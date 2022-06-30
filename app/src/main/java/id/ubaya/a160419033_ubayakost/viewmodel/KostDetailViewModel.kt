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
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KostDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<Kost>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(kostId: Int) {
        launch {
            val db = buildDb(getApplication())
            kostLiveData.value = db.kostDao().selectKost(kostId)
        }
    }

    fun updateKostToBooking(obj: Kost) {
        launch {
            val db = buildDb(getApplication())
            db.bookingDao().update(obj.kostId, 1)
        }
    }
}