package com.sri_sourish.fintrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: String = "",
    val amount: Double = 0.0,
    val category: String = "",
    val date: Long = System.currentTimeMillis(),
    val note: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
    val lastUpdated: Long = System.currentTimeMillis(),
    val isSynced: Boolean = false
)

enum class TransactionType {
    INCOME, EXPENSE
}

enum class Category(val title: String) {
    FOOD("Food"),
    TRAVEL("Travel"),
    BILLS("Bills"),
    SHOPPING("Shopping"),
    OTHERS("Others"),
    SALARY("Salary"),
    INVESTMENT("Investment")
}