package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ubaya.a160419033_ubayakost.model.User
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val profileLiveData = MutableLiveData<User>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(id: Int) {
        launch {
            val db = buildDb(getApplication())
            profileLiveData.value = db.profileDao().selectUser(id)
        }
    }

    fun update(id: Int, name: String, homeTown: String, phoneNumber: String) {
        launch {
            val db = buildDb(getApplication())
            db.profileDao().update(id, name, homeTown, phoneNumber)
        }
    }
}