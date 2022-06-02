package com.example.data.storage.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderRoom
import com.example.data.models.ProductRoom
import com.example.data.models.UserRoom


@Database(entities = [ProductRoom::class, OrderRoom::class, UserRoom::class], version = 9)
abstract class UserStorageImpl : RoomDatabase() {
    abstract fun getUserDao(): UserStorage

    companion object {
        private var database: UserStorageImpl? = null

        @Synchronized
        fun getInstance(context: Context): UserStorageImpl {
            return if (database == null) {
                database = Room.databaseBuilder(context, UserStorageImpl::class.java, "dbUser")
                    .allowMainThreadQueries().build()
                database as UserStorageImpl
            } else {
                database as UserStorageImpl
            }
        }
    }
}