package com.example.finza.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String, // "INCOME" or "EXPENSE"
    val icon: String // Icon reference name
)

object CategoryTypes {
    const val INCOME = "INCOME"
    const val EXPENSE = "EXPENSE"
}
