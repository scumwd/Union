package com.example.data.storage.product

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.OrderRoom
import com.example.data.models.ProductRoom
import com.example.data.models.UserRoom

@Database(entities = [ProductRoom::class, OrderRoom::class, UserRoom::class], version = 10)
abstract class ProductDb : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {
        private var database: ProductDb? = null

        @Synchronized
        fun getInstance(context: Context): ProductDb {
            return if (database == null) {
                database = Room.databaseBuilder(context, ProductDb::class.java, "dbProd").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                database as ProductDb
            } else {
                database as ProductDb
            }
        }
    }
}