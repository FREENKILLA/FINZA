package com.example.finza.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val description: String,
    val date: Long, // Timestamp
    val categoryId: Int
)
