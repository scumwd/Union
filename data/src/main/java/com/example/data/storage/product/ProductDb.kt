package com.example.data.storage.product

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderData
import com.example.data.models.ProductData
import com.example.data.models.UserData

@Database(entities = [ProductData::class, OrderData::class, UserData::class], version = 8)
abstract class ProductDb : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {
        private var database: ProductDb? = null

        @Synchronized
        fun getInstance(context: Context): ProductDb {
            return if (database == null) {
                database = Room.databaseBuilder(context, ProductDb::class.java, "dbProd").fallbackToDestructiveMigration().build()
                database as ProductDb
            } else {
                database as ProductDb
            }
        }
    }
}