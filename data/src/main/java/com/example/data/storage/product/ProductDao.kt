package com.example.data.storage.product

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.ProductRoom
import com.example.data.storage.order.OrderDao

@Dao
interface ProductDao : OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(productRoom: ProductRoom)

    @Update
    fun update(productRoom: ProductRoom)

    @Delete
    suspend fun delete(productRoom: ProductRoom)

    @Query("SELECT * from product_table")
    fun getAllProduct(): LiveData<List<ProductRoom>>

    @Query("SELECT * from product_table where productLink = :productLink")
    fun getProduct(productLink: String): ProductRoom?
}