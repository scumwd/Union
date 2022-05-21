package com.example.data.storage.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderData
import com.example.data.models.ProductData
import com.example.data.models.UserData


@Database(entities = [ProductData::class, OrderData::class, UserData::class], version = 5)
abstract class UserStorageImpl : RoomDatabase() {
    abstract fun getUserDao(): UserStorage

    companion object {
        private var database: UserStorageImpl? = null

        @Synchronized
        fun getInstance(context: Context): UserStorageImpl {
            return if (database == null) {
                database = Room.databaseBuilder(context, UserStorageImpl::class.java, "dbUser").build()
                database as UserStorageImpl
            } else {
                database as UserStorageImpl
            }
        }
    }
}