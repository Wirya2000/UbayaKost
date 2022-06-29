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
    Room.databaseBuilder(context, UbayaKostDatabase::class.java, "ubayakostdb")
        .build()

//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Kost
//        database.execSQL("INSERT INTO Kost VALUES('Kost Apik UBAYA Si Mbok', 'Putra', 'Rungkut', '1.377.000'," +
//            "'https://static.mamikos.com/uploads/cache/data/style/2021-12-20/66OwE1Em-360x480.jpg'," +
//            "'Jl. Rungkut Mejoyo Utara  Blok AN 38 , Kali Rungkut, Kecamatan Rungkut, Surabaya', '2.5 x 2.5'," +
//            "'K. Mandi Dalam, AC, Kloset Duduk, Kasur, WiFi', 'Akses 24 Jam, Dilarang merokok di kamar, Lawan jenis dilarang ke kamar'," +
//            "'-7.3180616', '112.7648088')")
//        database.execSQL("INSERT INTO Kost VALUES('Kost RMS 145PR', 'Putra', 'Rungkut', '1.200.000'," +
//            "'https://static.mamikos.com/uploads/cache/data/style/2021-10-08/j4c3z3SL-540x720.jpg', 'Jl. Rungkut Mejoyo Selatan 8 No 11 '," +
//            "'3 x 3', 'K. Mandi Dalam, Kasur, WiFi, Kloset Jongkok, AC','Lawan jenis dilarang ke kamar, Dilarang bawa hewan, Maks. 1 orang/kamar'," +
//            "'-7.3207882037367', '112.76493284851')")
//        database.execSQL("INSERT INTO Kost VALUES('Kost Gaia 2', 'Putri', 'Rungkut', '1.750.000', " +
//            "'https://static.mamikos.com/uploads/cache/data/style/2019-06-19/nmf8qiWt-540x720.jpg', " +
//            "'Jl. Mejoyo II no. 4b RT 04 RW 07 Rungkut kalirungkut surabaya' , " +
//            "'3 x 4', 'Kasur, WiFi, Kloset Duduk, AC, K.Mandi Dalam', 'Akses 24 Jam, Maks. 2 orang/kamar', " +
//            "'-7.3215314368301', '112.7689229697')")
//        database.execSQL("INSERT INTO Kost VALUES('Kost Singgahsini Q Sidosermo', 'Campur', 'Kecamatan Wonocolo', '2.020.000', " +
//            "'https://static.mamikos.com/uploads/cache/data/style/2021-06-24/4QUQWWFi-540x720.jpg', " +
//            "'Jl. Sidosermo Indah I No.15B, Sidosermo, Kec. Wonocolo, Kota SBY, Jawa Timur 60239, Indonesia', " +
//            "'5 x 4', 'AC, Kasur, WiFi, K.Mandi Dalam, Kloset Duduk', 'Maks. 2 orang/kamar, Menunjukkan bukti Swab saat check in', " +
//            "'-7.3098319', '112.7577472')")
//
//        // User
//        database.execSQL("INSERT INTO User VALUES('Michael', 'Rungkut', '081123123123')")
//        database.execSQL("INSERT INTO User VALUES('Bryan', 'Kecamatan Wonocolo', '089123123123')")
//        database.execSQL("INSERT INTO User VALUES('Febira Asrofin', 'Wisma Gunung Anyar', '085123123123')")
//        database.execSQL("INSERT INTO User VALUES('Raymond', 'Rungkut', '087123123123')")
//        database.execSQL("INSERT INTO User VALUES('Abdul Rahman', 'Kecamatan Wonocolo', '083123123123')")
//
//        // Review
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(1, 1, 'Lumayan bagus, ramah, dan dekat kemana-mana')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(1, 2, 'Peralatan cukup lengkap')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(2, 2, 'Kos dekat dengan kampus')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(3, 1, 'Fasilitas memuaskan')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(4, 3, 'Kos dan fasilitas bagus')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(4, 4, 'Kos bagus dan sesuai dengan harga')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(4, 5, 'Bagus, bersih, dan nyaman')")
//        database.execSQL("INSERT INTO Review(kostId, userId, review) VALUES(4, 2, 'Hunian nyaman dan terletak di perumahan tengah kota dan aman')")
//
//        // FAQ
//        database.execSQL("INSERT INTO FAQ VALUES(1, 'Booking')")
//        database.execSQL("INSERT INTO FAQ VALUES(2, 'Additional Fees')")
//        database.execSQL("INSERT INTO FAQ VALUES(3, 'Refund Policy)'")
//
//        // Question
//        database.execSQL("INSERT INTO Question(faqQuestionId, questionId, question, answer) VALUES(1, 1, 'How do I make an order via UbayaKost?', " +
//                "'Download the UbayaKost app, then Choose the kost that suits your needs, and click booking.')")
//        database.execSQL("INSERT INTO Question(faqId, id, question, answer) VALUES(1, 2, 'I am not experienced in booking accommodation online. Is there any help available to me?', " +
//                "'For questions or other information, please contact us via email.')")
//        database.execSQL("INSERT INTO Question(faqId, id, question, answer) VALUES(2, 1, 'Are there any additional fees I have to pay?', " +
//                "'You are only required to pay an additional fee if you use additional facilities or services provided by the accommodation where you are staying. In this case, additional costs are paid directly to the accommodation.')")
//        database.execSQL("INSERT INTO Question(faqId, id, question, answer) VALUES(2, 2, 'Are there any additional fees I have to pay?', " +
//                "'You are only required to pay an additional fee if you use additional facilities or services provided by the accommodation where you are staying. In this case, additional costs are paid directly to the accommodation.')")
//        database.execSQL("INSERT INTO Question(faqId, id, question, answer) VALUES(3, 1, 'When will UbayaKost refund my payment when my order is not finally confirmed?', " +
//                "'For credit card payments, we only hold it for pre-authorization and when your order is not confirmed, we will release your credit card information. While payments using bank transfer, we will make a full refund within 5 - 10 days after your order is not confirmed.')")
//        database.execSQL("INSERT INTO Question(faqId, id, question, answer) VALUES(3, 2, 'When will UbayaKost refund the deposit payment?', " +
//                "'The refund deposit process for daily orders using bank transfer or virtual account is estimated to be received again by the customer no later than 5 working days after the bank data is confirmed. The invoice will be used to calculate usage during your rental at the Travelio unit. After the invoice is issued, UbayaKost takes approximately 7 working days for detailed calculations. Please be willing to wait for the process.')")
//    }
//}


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
