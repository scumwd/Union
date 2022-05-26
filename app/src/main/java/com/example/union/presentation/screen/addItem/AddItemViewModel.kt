package com.example.union.presentation.screen.addItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ProductDomain
import com.example.union.cloud.ProductInsertCloud
import com.example.union.presentation.PRODUCT_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    lateinit var productInsertCloud: ProductInsertCloud

    fun insert(productDomain: ProductDomain, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            PRODUCT_REPOSITORY.insertProduct(productDomain) {
                onSuccess
            }
        }

    fun insertInFireBase(productDomain: ProductDomain)
    {
        productInsertCloud = ProductInsertCloud()
        productInsertCloud.insert(productDomain)
    }

    fun chekProduct(productLink: String): LiveData<ProductDomain?> = PRODUCT_REPOSITORY.getListProduct(productLink)
}