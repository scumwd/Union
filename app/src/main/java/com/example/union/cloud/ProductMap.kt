package com.example.union.cloud

import android.util.Log
import com.example.data.models.ProductData
import com.example.domain.models.ProductCloudData

class ProductMap {

    fun mapProduct(listCloud: MutableList<ProductCloudData?>) {
        var listRoom: List<ProductData> = mutableListOf()
        /*listRoom.map { room->
            listCloud.map { cloud ->
                room.productId = cloud?.getProductId().toString()
                room.productName = cloud?.getProductName().toString()
                room.productLink = cloud?.getProductLink().toString()
                room.productPrice = cloud?.getProductPrice().toString().toInt()
                room.productCity = cloud?.getCity().toString()
                room.productPhoto = cloud?.getProductPhoto().toString()
                room.amount = cloud?.getAmount().toString().toInt()
                room.totalAmount = cloud?.getTotalAmount().toString().toInt()
            }
        }*/
        listCloud.forEach { cloud ->
            val productData = ProductData(
                productId = cloud?.getProductId().toString(),
                productName = cloud?.getProductName().toString(),
                productLink = cloud?.getProductLink().toString(),
                productPrice = cloud?.getProductPrice().toString().toInt(),
                productCity = cloud?.getCity().toString(),
                productPhoto = cloud?.getProductPhoto().toString(),
                amount = cloud?.getAmount().toString().toInt(),
                totalAmount = cloud?.getTotalAmount().toString().toInt()
            )
            listRoom = listRoom + productData
        }
        val productInsetRoom = ProductInsetRoom()
        productInsetRoom.insert(listRoom)

    }
}
