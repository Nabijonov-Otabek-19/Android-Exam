package uz.gita.androidexam.data.common

import java.io.Serializable

data class Products(
    val category: String = "",
    val productList: List<Product> = arrayListOf()
): Serializable
