package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg review: Review)

    @Query("SELECT user.name AS user_name, user.hometown AS user_hometown, user.phone_number AS user_phone_number, user.userId AS user_userId, " +
            "review.kostReviewId AS review_kostReviewId, review.userReviewId AS review_userReviewId, review.review AS review_review " +
            "FROM review " +
            "INNER JOIN user ON review.userReviewId = user.userId " +
            "INNER JOIN kost ON review.kostReviewId = kost.kostId " +
            "WHERE kost.kostId= :id")
    suspend fun selectAllReview(id: Int): List<ReviewWithUser>

//    @Query("SELECT * FROM review " +
//            "INNER JOIN user ON review.userId = user.userId " +
//            "INNER JOIN kost ON review.kostId = kost.kostId " +
//            "WHERE reviewId= :id")
//    suspend fun selectReview(id:Int): Review

    @Query("UPDATE review SET kostReviewId = :kostId, userReviewId = :userId, review = :review")
    suspend fun update(kostId: Int, userId: Int, review: String)

    @Delete
    suspend fun deleteReview(review: Review)
}