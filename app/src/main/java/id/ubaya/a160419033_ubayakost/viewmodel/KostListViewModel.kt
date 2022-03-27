package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ubaya.a160419033_ubayakost.model.Kost

class KostListViewModel(application: Application): AndroidViewModel(application) {
    val kostsLiveData = MutableLiveData<ArrayList<Kost>>()
    val kostsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        kostsLoadErrorLiveData.value = false
        loadingLiveData.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/joshualbertus/advnative160419033_uts/kosts"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                kostsLiveData.value = result
                loadingLiveData.value = false;
            }, {
                loadingLiveData.value = false;
                kostsLoadErrorLiveData.value = true
            }
        ).apply{
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}