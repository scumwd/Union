package com.example.union.presentation.screen.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.order.OrderDb
import com.example.data.storage.product.ProductDb
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetProductFromFireBase
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val context = application
    lateinit var mAuth: FirebaseAuth
    private val getProductsFromFireBase = GetProductFromFireBase()

    fun initDatabase() {
        val userId: String
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser?.uid.toString().let { userId = it }
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        val daoOrder = OrderDb.getInstance(context).getOrderDao()
        ORDER_REPOSITORY = OrderRepositoryImpl(daoOrder, userId)
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
    }

    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return PRODUCT_REPOSITORY.allProductDomain
    }

    fun getProductsFromFireBase() {
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
        viewModelScope.launch(Dispatchers.IO) {
            getProductsFromFireBase.getAllProduct(PRODUCT_REPOSITORY)
        }
    }

}