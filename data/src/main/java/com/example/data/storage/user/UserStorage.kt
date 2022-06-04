package com.example.data.storage.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.UserRoom

@Dao
interface UserStorage {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userRoom: UserRoom)

    @Query("SELECT * from user_table where userId = :userid")
    fun getUser(userid: String): LiveData<UserRoom>

    @Query("SELECT * from user_table where userId = :userId")
    fun getUserById(userId: String): UserRoom
}