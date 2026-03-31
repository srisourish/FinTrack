package com.sri_sourish.fintrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sri_sourish.fintrack.data.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}