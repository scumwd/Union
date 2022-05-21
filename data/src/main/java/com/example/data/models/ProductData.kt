package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
class ProductData(
    @PrimaryKey(autoGenerate = true)
    var productId: Int = 0,

    @ColumnInfo
    var productName: String = "",

    @ColumnInfo
    var productCity: String = "",

    @ColumnInfo
    var productLink: String = "",

    @ColumnInfo
    var productPrice: Int = 0,

    @ColumnInfo
    var amount: Int = 0,

    @ColumnInfo
    var totalAmount: Int = 0,

    @ColumnInfo
    var productPhoto: String = "",

    )