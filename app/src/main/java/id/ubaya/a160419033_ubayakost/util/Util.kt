package id.ubaya.a160419033_ubayakost.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ubaya.a160419033_ubayakost.model.UbayaKostDatabase

val DB_NAME = "ubayakostdb"

fun buildDb(context: Context) =
    Room.databaseBuilder(context, UbayaKostDatabase::class.java, DB_NAME)
        .addMigrations()
        .build()

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(700, 300)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) { }
        })
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view:ImageView, url:String?, pb:ProgressBar) {
    if (url != null)
        view.loadImage(url, pb)
}
