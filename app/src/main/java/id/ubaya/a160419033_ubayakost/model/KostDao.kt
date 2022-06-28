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