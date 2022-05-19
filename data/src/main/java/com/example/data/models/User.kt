package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User (
    @PrimaryKey
    val userId: String,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val firstName: String,

    @ColumnInfo
    val lastName: String,

    @ColumnInfo
    val password: String
)