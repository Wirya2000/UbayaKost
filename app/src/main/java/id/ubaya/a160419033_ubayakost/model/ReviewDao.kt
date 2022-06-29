package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg review: Review)

    @Query("SELECT *, u.name FROM review " +
            "INNER JOIN user AS u ON review.userId = u.userId " +
            "INNER JOIN kost ON review.kostId = kost.kostId")
    suspend fun selectAllReview(): List<Review>

//    @Query("SELECT * FROM review " +
//            "INNER JOIN user ON review.userId = user.userId " +
//            "INNER JOIN kost ON review.kostId = kost.kostId " +
//            "WHERE reviewId= :id")
//    suspend fun selectReview(id:Int): Review

    @Query("UPDATE review SET kostId = :kostId, userId = :userId, review = :review")
    suspend fun update(kostId: Int, userId: Int, review: String)

    @Delete
    suspend fun deleteReview(review: Review)
}