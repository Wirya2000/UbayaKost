package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg kost: Kost)

    @Query("SELECT * FROM kost")
    suspend fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kost WHERE kostId= :id")
    suspend fun selectKost(id:Int): Kost

    @Query("UPDATE kost SET name = :name, gender = :gender, region = :region, price = :price, " +
            "photoUrl = :photoUrl, alamat = :alamat, luas = :luas, fasilitas = :fasilitas," +
            "peraturan = :peraturan, x = :x, y = :y WHERE kostId = :id")
    suspend fun update(id: Int, name: String, gender: String, region: String, price: String,
                       photoUrl: String, alamat: String, luas: String, fasilitas: String,
                       peraturan: String, x: String, y: String)

    @Delete
    suspend fun deleteTodo(kost: Kost)
}

interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg review: Review)

    @Query("SELECT * FROM review " +
            "INNER JOIN user ON review.userId = user.userId " +
            "INNER JOIN kost ON review.kostId = kost.kostId")
    suspend fun selectAllReview(): List<UserWithKost>

    @Query("SELECT * FROM review " +
            "INNER JOIN user ON review.userId = user.userId " +
            "INNER JOIN kost ON review.kostId = kost.kostId " +
            "WHERE reviewId= :id")
    suspend fun selectReview(id:Int): Review

    @Query("UPDATE review SET kostId = :kostId, userId = :userId, review = :review WHERE reviewId = :id")
    suspend fun update(id: Int, kostId: Int, userId: Int, review: String)

    @Delete
    suspend fun deleteReview(review: Review)
}

interface FAQDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg faq: FAQ)

    @Query("SELECT * FROM faq")
    suspend fun selectAllFAQ(): List<Review>

    @Query("SELECT * FROM faq WHERE faqId= :id")
    suspend fun selectFAQ(id:Int): FAQ

    @Query("UPDATE faq SET topic = :topic WHERE faqId = :id")
    suspend fun update(id: Int, topic: String)

    @Delete
    suspend fun deleteFAQ(faq: FAQ)
}

interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg question: Question)

    @Query("SELECT * FROM question")
    suspend fun selectAllQuestion(): List<Question>

    @Query("SELECT * FROM question " +
            "INNER JOIN faq ON faq.faqId = question.faqQuestionId " +
            "WHERE faqQuestionId= :id")
    suspend fun selectQuestion(id:Int): Booking

    @Query("SELECT * FROM question WHERE questionId= :id")
    suspend fun selectQuestionId(id:Int): Question

    @Query("UPDATE question SET faqQuestionId = :faqQuestionId, question = :question, answer = :answer WHERE questionId = :id")
    suspend fun update(id: Int, faqQuestionId: Int, question: String, answer: String)

    @Delete
    suspend fun deleteQuestion(question: Question)
}

interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg booking: Booking)

    @Query("SELECT * FROM booking " +
            "INNER JOIN user ON booking.userId = user.userId " +
            "INNER JOIN kost ON booking.kostId = kost.kostId")
    suspend fun selectAllBooking(): List<UserWithKostBooking>

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

