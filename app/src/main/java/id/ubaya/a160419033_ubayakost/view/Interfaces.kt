package id.ubaya.a160419033_ubayakost.view

import android.view.View
import id.ubaya.a160419033_ubayakost.model.Kost
import android.widget.CompoundButton
import id.ubaya.a160419033_ubayakost.model.User

// FAQ LIST
interface FaqItemClickListener {
    fun onItemClick(v: View)
}

// KOST LIST
interface KostItemClickListener {
    fun onItemClick(v:View)
}

// KOST DETAIL
interface KostReviewClickListener {
    fun onReviewClick(v:View)
}

// KOST DETAIL
interface KostBookingClickListener {
    fun onBookingClick(v:View, obj:Kost)
}

// PROFILE
interface ProfileUpdateCLickListener {
    fun onUpdateClick(v:View, obj: User)
}