package com.example.data.storage.order

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.OrderData

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(orderData: OrderData)

    @Delete
    suspend fun delete(orderData: OrderData)

    @Query("SELECT * from order_table where userId = :userid")
    fun getOrders(userid: String): LiveData<List<OrderData>>

    @Query("SELECT * from order_table where userId = :userid")
    fun getListOrder(userid: String): List<OrderData>
}