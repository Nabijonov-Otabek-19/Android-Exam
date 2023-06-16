package uz.gita.androidexam.domain.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.androidexam.data.common.Product
import uz.gita.androidexam.data.common.Products
import uz.gita.androidexam.domain.repository.AppRepository
import uz.gita.androidexam.utils.logger
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val firestore = Firebase.firestore

    override fun fetchAllProducts(): Flow<Result<List<Products>>> = callbackFlow {
        firestore.collection("Products").get()
            .addOnSuccessListener { query ->
                val productData = query.toObjects(Products::class.java)
                logger("Repository = $productData")
                trySend(Result.success(productData))

            }.addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun fetchProductbyUserId(userId: Int): Flow<Result<List<Products>>> = callbackFlow {

    }
}