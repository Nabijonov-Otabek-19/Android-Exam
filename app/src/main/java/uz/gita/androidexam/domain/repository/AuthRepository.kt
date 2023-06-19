package uz.gita.androidexam.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(email: String, password: String) : Flow<Result<Unit>>
    fun signUp(email: String, password: String): Flow<Result<Unit>>
}