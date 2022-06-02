package com.example.union.presentation.screen.addItem

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.cloud.UploadProductImage
import com.example.domain.getFromDb.CheckProductExists
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetProductFromFireBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddItemViewModel@Inject constructor (
    private val getProductFromFireBase: GetProductFromFireBase,
    private val productInsertCloud: ProductInsertCloud,
    private val uploadProductImage: UploadProductImage,
    private val checkProductExists: CheckProductExists
) : ViewModel() {

    fun insertInFireBase(productDomain: ProductDomain) {
        productInsertCloud.insert(productDomain)
    }

    suspend fun uploadImage(photo: ImageView): String{
        return uploadProductImage.execute(photo)
    }

    fun chekProduct(productLink: String): ProductDomain? =
        checkProductExists.execute(productLink)

    fun getProductsFromFireBase() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductFromFireBase.getAllProduct()
        }
    }
}