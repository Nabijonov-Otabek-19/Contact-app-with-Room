package uz.gita.contactappwithroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.contactappwithroom.daos.UserDao
import uz.gita.contactappwithroom.entities.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private lateinit var instance: AppDatabase

        fun init(context: Context) {
            if (!(::instance.isInitialized)) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "UserContact.db"
                ).allowMainThreadQueries().build()
            }
        }

        fun getInstance() = instance
    }
}