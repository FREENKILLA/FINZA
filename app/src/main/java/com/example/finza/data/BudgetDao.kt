package com.example.finza.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budgets WHERE month = :month AND year = :year")
    fun getBudgetsByMonth(month: Int, year: Int): Flow<List<Budget>>

    @Query("SELECT * FROM budgets WHERE categoryId = :categoryId AND month = :month AND year = :year LIMIT 1")
    suspend fun getBudgetForCategory(categoryId: Int, month: Int, year: Int): Budget?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)

    @Update
    suspend fun updateBudget(budget: Budget)
}
