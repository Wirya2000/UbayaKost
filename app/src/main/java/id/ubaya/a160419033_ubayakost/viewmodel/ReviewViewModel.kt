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
import id.ubaya.a160419033_ubayakost.model.Review
import id.ubaya.a160419033_ubayakost.model.ReviewWithUser
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReviewViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val reviewsLiveData = MutableLiveData<List<ReviewWithUser>>()
    val reviewsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(kostId: Int) {
        reviewsLoadErrorLiveData.value = false
        loadingLiveData.value = false

        launch {
            val db = buildDb(getApplication())
            reviewsLiveData.value = db.reviewDao().selectAllReview()
        }
    }
}