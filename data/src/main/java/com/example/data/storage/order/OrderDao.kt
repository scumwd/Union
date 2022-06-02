package com.example.data.storage.order

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.OrderRoom

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(orderRoom: OrderRoom)

    @Delete
    suspend fun delete(orderRoom: OrderRoom)

    @Query("SELECT * from order_table where userId = :userid")
    fun getOrders(userid: String): LiveData<List<OrderRoom>>

    @Query("SELECT * from order_table where userId = :userid and productId = :productId")
    fun getListOrder(userid: String, productId: String): OrderRoom?
}