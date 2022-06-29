package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface FaqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg faq: FAQ)

    @Query("SELECT * FROM faq")
    suspend fun selectAllFAQ(): List<FAQ>

    @Query("SELECT * FROM faq WHERE faqId= :id")
    suspend fun selectFAQ(id:Int): FAQ

    @Query("UPDATE faq SET topic = :topic WHERE faqId = :id")
    suspend fun update(id: Int, topic: String)

    @Delete
    suspend fun deleteFAQ(faq: FAQ)
}