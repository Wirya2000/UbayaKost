package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KostListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val kostsLiveData = MutableLiveData<List<Kost>>()
    val kostsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        kostsLoadErrorLiveData.value = false
        loadingLiveData.value = false

       launch {
           val db = buildDb(getApplication())
           kostsLiveData.value = db.kostDao().selectAllKost()
//           db.kostDao().insert1()
//           db.kostDao().insert2()
//           db.kostDao().insert3()
//           db.kostDao().insert4()
//           db.kostDao().insert5()
//           db.kostDao().insert6()
//           db.kostDao().insert7()
//           db.kostDao().insert8()
//           db.kostDao().insert9()
//           db.kostDao().insert10()
//           db.kostDao().insert11()
//           db.kostDao().insert12()
//           db.kostDao().insert13()
//           db.kostDao().insert14()
//           db.kostDao().insert15()
//           db.kostDao().insert16()
//           db.kostDao().insert17()
//           db.kostDao().insert18()
//           db.kostDao().insert19()
//           db.kostDao().insert20()
//           db.kostDao().insert21()
//           db.kostDao().insert22()
//           db.kostDao().insert23()
//           db.kostDao().insert24()
//           db.kostDao().insert25()
//           db.kostDao().insert26()
//           db.kostDao().insert27()
       }
    }
}