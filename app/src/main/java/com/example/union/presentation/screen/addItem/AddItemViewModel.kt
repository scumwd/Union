package com.example.union.presentation.screen.addItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.product.ProductDb
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetProductFromFireBase
import com.example.union.presentation.PRODUCT_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    lateinit var productInsertCloud: ProductInsertCloud
    private val getProductsFromFireBase = GetProductFromFireBase()

    fun insertInFireBase(productDomain: ProductDomain) {
        productInsertCloud = ProductInsertCloud()
        productInsertCloud.insert(productDomain)
    }

    fun chekProduct(productLink: String): ProductDomain? =
        PRODUCT_REPOSITORY.getListProduct(productLink)

    fun getProductsFromFireBase() {
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
        viewModelScope.launch(Dispatchers.IO) {
            getProductsFromFireBase.getAllProduct(PRODUCT_REPOSITORY)
        }
    }
}