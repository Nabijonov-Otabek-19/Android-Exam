package uz.gita.androidexam.data.common

import uz.gita.androidexam.R
import java.io.Serializable

data class Product(
    val id: Int = 0,
    val category: String = "",
    val name: String = "",
    val price: Int = 0,
    val size: Long = 0L,
    val img: Int = R.drawable.ic_product,
    val userId: String = ""
) : Serializable
