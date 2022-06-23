package id.ubaya.a160419033_ubayakost.view

import android.view.View
import android.widget.CompoundButton

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
    fun onBookingClick(v:View)
}