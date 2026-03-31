package com.sri_sourish.fintrack.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sri_sourish.fintrack.data.Transaction
import com.sri_sourish.fintrack.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val firestore: FirebaseFirestore
) {
    private val transactionCollection = firestore.collection("transactions")

    fun getAllTransactions(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTotalIncome(): Flow<Double?> = transactionDao.getTotalIncome()

    fun getTotalExpense(): Flow<Double?> = transactionDao.getTotalExpense()

    suspend fun addTransaction(transaction: Transaction) {
        // Save locally first
        transactionDao.insertTransaction(transaction.copy(isSynced = false))
        
        // Try to sync with Firestore
        try {
            transactionCollection.document(transaction.id).set(transaction).await()
            transactionDao.insertTransaction(transaction.copy(isSynced = true))
        } catch (e: Exception) {
            // Leave as unsynced if network fails
        }
    }

    suspend fun syncUnsyncedTransactions() {
        val unsynced = transactionDao.getUnsyncedTransactions()
        unsynced.forEach { transaction ->
            try {
                transactionCollection.document(transaction.id).set(transaction).await()
                transactionDao.insertTransaction(transaction.copy(isSynced = true))
            } catch (e: Exception) {
                // Ignore for now
            }
        }
    }

    suspend fun fetchFromFirestore() {
        try {
            val snapshot = transactionCollection.get().await()
            val remoteTransactions = snapshot.toObjects(Transaction::class.java)
            transactionDao.insertTransactions(remoteTransactions.map { it.copy(isSynced = true) })
        } catch (e: Exception) {
            // Handle error
        }
    }
}