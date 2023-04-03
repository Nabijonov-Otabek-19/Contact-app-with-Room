package uz.gita.contactappwithroom.daos

import androidx.room.*
import uz.gita.contactappwithroom.entities.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserData)

    @Delete
    fun delete(user: UserData)

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserData>
}