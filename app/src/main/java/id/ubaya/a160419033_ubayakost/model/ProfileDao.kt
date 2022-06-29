package id.ubaya.a160419033_ubayakost.model

import android.provider.ContactsContract
import androidx.room.*

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE userId= :id")
    suspend fun selectUser(id:Int): User

    @Query("UPDATE user SET name = :name, hometown = :hometown, phone_number = :phone_number WHERE userId = :id")
    suspend fun update(id: Int, name: String, hometown: String, phone_number: String)

    @Delete
    suspend fun deleteUser(user: User)
}
