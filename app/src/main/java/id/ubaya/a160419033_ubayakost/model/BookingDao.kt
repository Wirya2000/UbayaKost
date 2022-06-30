package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg kost: Kost)

    @Query("SELECT user.name AS user_name, user.hometown AS user_hometown, user.phone_number AS user_phone_number, user.userId AS user_userId," +
            "kost.name AS kost_name, kost.gender AS kost_gender, kost.region AS kost_region, kost.price AS kost_price, kost.photoUrl AS kost_photoUrl, " +
            "kost.alamat AS kost_alamat, kost.luas AS kost_luas, kost.fasilitas AS kost_fasilitas, kost.peraturan AS kost_peraturan, kost.x AS kost_x, kost.y AS kost_y " +
            ", kost.kostId AS kost_kostId FROM booking " +
            "INNER JOIN user ON booking.userBookingId = user.userId " +
            "INNER JOIN kost ON booking.kostBookingId = kost.kostId WHERE userId= :id")
    suspend fun selectAllBooking(id: Int): BookingWithKost

//    @Query("SELECT * FROM booking " +
//            "INNER JOIN user ON booking.userId = user.userId " +
//            "INNER JOIN kost ON booking.kostId = kost.kostId " +
//            "WHERE user.userId= :id")
//    suspend fun selectBooking(id:Int): Booking

    @Query("UPDATE booking SET kostBookingId = :kostId, userBookingId = :userId")
    suspend fun update(kostId: Int, userId: Int)

    @Delete
    suspend fun deleteBooking(booking: Booking)
}