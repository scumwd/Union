package com.example.union.presentation.screen.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.cloud.UploadProductImage
import com.example.domain.getFromDb.CheckProductExists
import com.example.domain.save.GetProductFromFireBase

class AddItemViewModelFactory(
    val productInsertCloud: ProductInsertCloud,
    val getProductFromFireBase: GetProductFromFireBase,
    val uploadProductImage: UploadProductImage,
    val checkProductExists: CheckProductExists
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddItemViewModel(
            getProductFromFireBase = getProductFromFireBase,
            productInsertCloud = productInsertCloud,
            uploadProductImage = uploadProductImage,
            checkProductExists = checkProductExists
        ) as T
    }

}