package com.example.data.storage.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.UserData

@Dao
interface UserStorage {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userData: UserData)

    @Query("SELECT * from user_table where userId = :userid")
    fun getUser(userid: String): LiveData<UserData>
}