package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "order_table", primaryKeys = ["productId", "userId"])
class OrderRoom(
    var productId: String = "",

    var userId: String,

    @ColumnInfo
    var totalAmount: Int = 0
)