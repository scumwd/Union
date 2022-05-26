package com.example.data.storage.product

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.ProductData
import com.example.data.storage.order.OrderDao


@Dao
interface ProductDao: OrderDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productData: ProductData)

    @Update
    suspend fun update(productData: ProductData)

    @Delete
    suspend fun delete(productData: ProductData)

    @Query("SELECT * from product_table")
    fun getAllProduct(): LiveData<List<ProductData>>

    @Query("SELECT * from product_table where productLink = :productLink")
    fun getProduct(productLink: String): LiveData<ProductData?>
}