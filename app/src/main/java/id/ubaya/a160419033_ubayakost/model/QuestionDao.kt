package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
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