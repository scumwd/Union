package com.example.domain.cloud


import android.widget.ImageView
import com.example.domain.repository.ProductRepository

class UploadProductImage(val productRepository: ProductRepository) {

    suspend fun execute(photo: ImageView): String {
        return productRepository.uploadProductImage(photo)
    }
}