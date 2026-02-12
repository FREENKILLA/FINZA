package com.example.finza.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE type = :type")
    fun getCategoriesByType(type: String): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)
}
