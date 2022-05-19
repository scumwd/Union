package com.example.union.presentation.screen.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.product.ProductDb
import com.example.domain.models.Product
import com.example.union.presentation.REPOSITORY

class HomeViewModel (application: Application) : AndroidViewModel(application){

    val context = application

    fun initDatabase(){
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        REPOSITORY = ProductRepositoryImpl(daoProduct)
    }

    fun getAppProduct(): LiveData<List<Product>>{
        return REPOSITORY.allProduct
    }


}