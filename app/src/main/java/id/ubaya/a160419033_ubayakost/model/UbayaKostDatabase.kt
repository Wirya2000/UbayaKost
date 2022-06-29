package id.ubaya.a160419033_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Kost::class, User::class, Review::class, Booking::class, FAQ::class, Question::class), version = 1)
abstract class UbayaKostDatabase:RoomDatabase() {
    abstract fun kostDao(): KostDao

    companion object {
        @Volatile private var instance: UbayaKostDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UbayaKostDatabase::class.java,
                "newtododb"
            )
                .addMigrations()
                .build()
        operator fun invoke(context: Context) {
            if (instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}