package uz.gita.androidexam.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.androidexam.data.common.ProductData
import uz.gita.androidexam.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(): AppRepository {

    override fun fetchAllProducts(): Flow<Result<ProductData>> = callbackFlow {

    }

    override fun fetchPRbyUserId(userId: Int): Flow<Result<ProductData>> = callbackFlow {

    }
}