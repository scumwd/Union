package com.example.data.storage.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderRoom
import com.example.data.models.ProductRoom
import com.example.data.models.UserRoom
import com.example.data.storage.product.ProductDao

@Database(entities = [ProductRoom::class, OrderRoom::class, UserRoom::class], version = 9)
abstract class OrderDb : RoomDatabase() {
    abstract fun getOrderDao(): ProductDao

    companion object {
        private var database: OrderDb? = null

        @Synchronized
        fun getInstance(context: Context): OrderDb {
            return if (database == null) {
                database = Room.databaseBuilder(context, OrderDb::class.java, "dbOrd")
                    .allowMainThreadQueries().build()
                database as OrderDb
            } else {
                database as OrderDb
            }
        }
    }
}