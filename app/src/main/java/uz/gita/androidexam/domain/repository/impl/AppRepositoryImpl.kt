package uz.gita.androidexam.domain.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.androidexam.data.common.CategoryData
import uz.gita.androidexam.data.common.Product
import uz.gita.androidexam.data.common.Products
import uz.gita.androidexam.domain.repository.AppRepository
import uz.gita.androidexam.utils.Constants
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val firestore = Firebase.firestore

    override fun fetchAllProducts(): Flow<Result<List<Products>>> = callbackFlow {
        val productListener = firestore.collection("Products")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }

                if (value != null) {
                    val productList = arrayListOf<Products>()
                    value.forEach { products ->
                        val category = products.get("category").toString()
                        val itemList = arrayListOf<Product>()
                        products.reference.collection("productList")
                            .addSnapshotListener { value, error ->
                                if (error != null) {
                                    trySend(Result.failure(error))
                                }

                                value?.forEach { itemProduct ->
                                    itemList.add(itemProduct.toObject())
                                }
                            }
                        productList.add(Products(category, itemList))
                    }
                    trySend(Result.success(productList))
                }
            }
        awaitClose { productListener.remove() }
    }

    override fun fetchProductbyUserId(userId: String): Flow<Result<List<Products>>> = callbackFlow {
        val productListener = firestore.collection("Products")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }

                if (value != null) {
                    val productList = arrayListOf<Products>()
                    value.forEach { products ->
                        val category = products.get("category").toString()

                        products.reference.collection("productList")
                            .whereEqualTo("userId", Constants.user?.email)
                            .addSnapshotListener { value, error ->
                                if (error != null) {
                                    trySend(Result.failure(error))
                                }

                                if (value != null) {
                                    val itemList = arrayListOf<Product>()
                                    value.forEach { itemProduct ->
                                        itemList.add(itemProduct.toObject())
                                    }
                                    productList.add(Products(category, itemList))
                                }
                            }
                    }
                    trySend(Result.success(productList))
                }
            }
        awaitClose { productListener.remove() }
    }

    override fun addProduct(product: Product): Flow<Result<Unit>> = callbackFlow {
        firestore.collection("Products")
            .whereEqualTo("category", product.category).get()
            .addOnSuccessListener { query ->
                query.forEach { products ->
                    products.reference.collection("productList").add(product)
                        .addOnSuccessListener { trySend(Result.success(Unit)) }
                        .addOnFailureListener { trySend(Result.failure(it)) }
                }
            }.addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun addCategory(category: String): Flow<Result<Unit>> = callbackFlow {
        firestore.collection("Products").add(CategoryData(category))
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { trySend(Result.failure(it)) }
        awaitClose()
    }

    override fun fetchCategories(): Flow<Result<List<String>>> = callbackFlow {
        val listener = firestore.collection("Products")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }

                if (value != null) {
                    val categoryList = arrayListOf<String>()
                    value.forEach { query ->
                        val category = query.get("category").toString()
                        categoryList.add(category)
                    }
                    trySend(Result.success(categoryList))
                }
            }
        awaitClose { listener.remove() }
    }
}