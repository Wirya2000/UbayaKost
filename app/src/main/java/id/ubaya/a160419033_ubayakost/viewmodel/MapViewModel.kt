package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MapViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val mapLiveData = MutableLiveData<Kost>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(kostId: String?) {
        if (kostId != null) {
            launch {
                val db = buildDb(getApplication())
                mapLiveData.value = db.kostDao().selectKost(kostId.toInt())
            }
        } else {
            mapLiveData.value = Kost("UBAYA", "", "", "", "", "", "", "", "", "-7.3198136612490625", "112.76811956063706")
        }
    }
}