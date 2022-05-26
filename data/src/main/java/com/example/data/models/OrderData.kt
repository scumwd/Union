package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table", primaryKeys = ["productId", "userId"])
class OrderData(
    var productId: String = "",

    var userId: String,

    @ColumnInfo
    var totalAmount: Int = 0
)