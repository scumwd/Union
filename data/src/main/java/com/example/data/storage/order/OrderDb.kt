package com.example.data.storage.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderData
import com.example.data.models.ProductData
import com.example.data.models.UserData
import com.example.data.storage.product.ProductDao

@Database(entities = [ProductData::class, OrderData::class, UserData::class], version = 5)
abstract class OrderDb : RoomDatabase() {
    abstract fun getOrderDao(): ProductDao

    companion object {
        private var database: OrderDb? = null

        @Synchronized
        fun getInstance(context: Context): OrderDb {
            return if (database == null) {
                database = Room.databaseBuilder(context, OrderDb::class.java, "dbOrd").build()
                database as OrderDb
            } else {
                database as OrderDb
            }
        }
    }
}