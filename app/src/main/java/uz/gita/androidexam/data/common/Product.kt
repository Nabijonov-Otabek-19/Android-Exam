package uz.gita.androidexam.data.common

import uz.gita.androidexam.R

data class Product(
    val id: Int,
    val category: String,
    val name: String,
    val price: Int,
    val img: String = R.drawable.ic_product.toString()
)
