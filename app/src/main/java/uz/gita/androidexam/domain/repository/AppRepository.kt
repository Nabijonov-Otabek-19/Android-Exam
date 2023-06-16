package uz.gita.androidexam.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.androidexam.data.common.Products

interface AppRepository {
    fun fetchAllProducts(): Flow<Result<List<Products>>>
    fun fetchProductbyUserId(userId: Int): Flow<Result<List<Products>>>
}