package com.example.data.storage.product

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.Product


@Dao
interface ProductDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * from product_table")
    fun getAllProduct(): LiveData<List<Product>>
}