package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserRoom(
    @PrimaryKey
    val userId: String,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val firstName: String,

    @ColumnInfo
    val lastName: String,
)