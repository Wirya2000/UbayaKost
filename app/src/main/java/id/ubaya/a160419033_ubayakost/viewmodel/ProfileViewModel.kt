package id.ubaya.a160419033_ubayakost.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ubaya.a160419033_ubayakost.model.Profile

class ProfileViewModel (application: Application): AndroidViewModel(application) {
    val profileLiveData = MutableLiveData<Profile>()

    fun fetch(name: String?, hometown: String?, phoneNumber: String? ) {
        profileLiveData.value = Profile(name, hometown, phoneNumber)
    }
}