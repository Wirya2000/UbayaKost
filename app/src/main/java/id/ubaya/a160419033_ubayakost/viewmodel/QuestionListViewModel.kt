package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ubaya.a160419033_ubayakost.model.FAQ
import id.ubaya.a160419033_ubayakost.model.Question

class QuestionListViewModel (application: Application): AndroidViewModel(application) {
    val faqsLiveData = MutableLiveData<ArrayList<FAQ>>()
    val faqsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        faqsLoadErrorLiveData.value = false
        loadingLiveData.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/joshualbertus/advnative160419033_uts/faq"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<FAQ>>() {}.type
                val result = Gson().fromJson<ArrayList<FAQ>>(it, sType)
                faqsLiveData.value = result
                loadingLiveData.value = false;
            }, {
                loadingLiveData.value = false;
                faqsLoadErrorLiveData.value = true
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