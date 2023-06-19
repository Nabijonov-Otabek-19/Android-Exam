package uz.gita.androidexam.domain.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.androidexam.domain.repository.AuthRepository
import uz.gita.androidexam.utils.Constants
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth

    override fun signIn(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Constants.user = auth.currentUser
                trySend(Result.success(Unit))
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
            .addOnCanceledListener { trySend(Result.failure(Exception("Cancelled Operation"))) }
        awaitClose()
    }

    override fun signUp(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Constants.user = auth.currentUser
                trySend(Result.success(Unit))
            }
            .addOnFailureListener { trySend(Result.failure(it)) }
            .addOnCanceledListener { trySend(Result.failure(Exception("Cancelled Operation"))) }
        awaitClose()
    }
}