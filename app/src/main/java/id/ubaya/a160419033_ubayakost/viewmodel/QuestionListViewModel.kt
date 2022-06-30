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
import id.ubaya.a160419033_ubayakost.model.FAQ
import id.ubaya.a160419033_ubayakost.model.Question
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class QuestionListViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val faqsLiveData = MutableLiveData<List<FAQ>>()
    val faqsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        faqsLoadErrorLiveData.value = false
        loadingLiveData.value = false

        launch { 
            val db = buildDb(getApplication())
            faqsLiveData.value = db.faqDao().selectAllFAQ()
        }
    }
}