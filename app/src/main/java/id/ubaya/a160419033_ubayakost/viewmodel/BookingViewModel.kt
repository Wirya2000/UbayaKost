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
import id.ubaya.a160419033_ubayakost.model.Kost

class BookingViewModel(application: Application): AndroidViewModel(application) {
    val bookingLiveData = MutableLiveData<Kost?>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(bookingId: String?) {
        if (bookingId == null) {
            bookingLiveData.value = null
        } else {
            queue = Volley.newRequestQueue(getApplication())
            val url = "https://my-json-server.typicode.com/joshualbertus/advnative160419033_uts/kosts?id=${bookingId}"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                {
                    val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                    val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                    bookingLiveData.value = result[0]
                }, {
                    Log.e("lderror", "Live data error")
                }
            ).apply{
                tag = "TAG"
            }
            queue?.add(stringRequest)
        }
    }
}