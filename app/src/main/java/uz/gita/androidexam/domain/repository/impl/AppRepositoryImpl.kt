package uz.gita.androidexam.domain.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.androidexam.data.common.Product
import uz.gita.androidexam.data.common.Products
import uz.gita.androidexam.domain.repository.AppRepository
import uz.gita.androidexam.utils.Constants
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val firestore = Firebase.firestore

    override fun fetchAllProducts(): Flow<Result<List<Products>>> = callbackFlow {
        firestore.collection("Products").get()
            .addOnSuccessListener { query ->
                val productList = arrayListOf<Products>()
                query.forEach { products ->
                    val category = products.get("category").toString()
                    products.reference.collection("productList").get()
                        .addOnSuccessListener { query ->
                            val itemList = arrayListOf<Product>()
                            query.forEach { itemProduct -> itemList.add(itemProduct.toObject()) }
                            productList.add(Products(category, itemList))
                            trySend(Result.success(productList))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }

            }.addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun fetchProductbyUserId(userId: String): Flow<Result<List<Products>>> = callbackFlow {
        firestore.collection("Products").get()
            .addOnSuccessListener { query ->
                val productList = arrayListOf<Products>()
                query.forEach { products ->
                    val category = products.get("category").toString()
                    products.reference.collection("productList")
                        .whereEqualTo("userId", Constants.user?.email).get()
                        .addOnSuccessListener { query ->
                            val itemList = arrayListOf<Product>()
                            query.forEach { itemProduct -> itemList.add(itemProduct.toObject()) }
                            productList.add(Products(category, itemList))
                            trySend(Result.success(productList))
                        }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }

            }.addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }
}