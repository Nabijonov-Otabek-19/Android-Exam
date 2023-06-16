package uz.gita.androidexam.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.androidexam.data.common.ProductData

interface AppRepository {
    fun fetchAllProducts(): Flow<Result<ProductData>>
    fun fetchPRbyUserId(userId: Int): Flow<Result<ProductData>>
}