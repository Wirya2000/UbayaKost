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
import id.ubaya.a160419033_ubayakost.model.Question

class QuestionDetailViewModel(application: Application): AndroidViewModel(application) {
    val questionsLiveData = MutableLiveData<ArrayList<Question>>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(faqId: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/joshualbertus/advnative160419033_uts/questions?faqId=${faqId}"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Question>>() {}.type
                val result = Gson().fromJson<ArrayList<Question>>(it, sType)
                questionsLiveData.value = result
                Log.d("showvolley", it)
            }, {
                Log.d("errorvolley", it.toString())
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