package com.example.union.presentation.screen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.order.OrderDb
import com.example.data.storage.product.ProductDb
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.example.domain.save.GetOrderFromFireBase
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    val context = application
    lateinit var mAuth: FirebaseAuth
    lateinit var getOrderFromFireBase: GetOrderFromFireBase

    fun initDatabase() {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        val orderDao = OrderDb.getInstance(context).getOrderDao()
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
        ORDER_REPOSITORY = OrderRepositoryImpl(orderDao, userId)
    }

    fun getAllOrder(): LiveData<List<OrderDomain>> {
        return ORDER_REPOSITORY.allOrderDomain
    }

    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return PRODUCT_REPOSITORY.allProductDomain
    }

    fun getOrder() {
        getOrderFromFireBase = GetOrderFromFireBase()
        getOrderFromFireBase.getAllProduct(ORDER_REPOSITORY)
    }

    fun getUser(): LiveData<UserWithUID> {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userRepository: UserRepository = UserRepositoryImpl(daoUser)
        return userRepository.getUser(userId)
    }

    fun signOut() {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
    }
}