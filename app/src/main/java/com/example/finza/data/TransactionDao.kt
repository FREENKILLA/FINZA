package com.example.finza.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Query("""
        SELECT SUM(amount) FROM transactions 
        INNER JOIN categories ON transactions.categoryId = categories.id 
        WHERE categories.type = :type
    """)
    fun getTotalByCategoryType(type: String): Flow<Double?>

    @Query("""
        SELECT * FROM transactions 
        WHERE date >= :startDate AND date <= :endDate 
        ORDER BY date DESC
    """)
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<Transaction>>

    @Query("""
        SELECT categories.name as categoryName, SUM(transactions.amount) as totalAmount 
        FROM transactions 
        INNER JOIN categories ON transactions.categoryId = categories.id 
        GROUP BY categoryId
    """)
    fun getTransactionSumByCategory(): Flow<List<CategorySum>>
}

data class CategorySum(
    val categoryName: String,
    val totalAmount: Double
)
