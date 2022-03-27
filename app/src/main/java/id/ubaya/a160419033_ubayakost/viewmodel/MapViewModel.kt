package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ubaya.a160419033_ubayakost.model.Map

class MapViewModel(application: Application): AndroidViewModel(application) {
    val mapLiveData = MutableLiveData<Map>()

    fun fetch(latitude: Double, longitude: Double, placeName: String) {
        mapLiveData.value = Map(latitude, longitude, placeName)
    }
}