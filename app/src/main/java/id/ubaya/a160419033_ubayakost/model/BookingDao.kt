package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg kost: Kost)

    @Query("SELECT * FROM booking " +
            "INNER JOIN user ON booking.userId = user.userId " +
            "INNER JOIN kost ON booking.kostId = kost.kostId")
    suspend fun selectAllBooking(): List<Booking>

    @Query("SELECT * FROM booking " +
            "INNER JOIN user ON booking.userId = user.userId " +
            "INNER JOIN kost ON booking.kostId = kost.kostId " +
            "WHERE bookingId= :id")
    suspend fun selectBooking(id:Int): Booking

    @Query("UPDATE booking SET kostId = :kostId, userId = :userId WHERE bookingId = :id")
    suspend fun update(id: Int, kostId: Int, userId: Int)

    @Delete
    suspend fun deleteBooking(booking: Booking)
}