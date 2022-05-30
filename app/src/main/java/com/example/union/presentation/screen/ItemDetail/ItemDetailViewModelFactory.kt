package com.example.union.presentation.screen.ItemDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.getFromDb.CheckOrderExists
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.update.UpdateProductInFireBase

class ItemDetailViewModelFactory(
    val getOrderFromFireBase: GetOrderFromFireBase,
    val orderInsertCloud: OrderInsertCloud,
    val updateProductInFireBase: UpdateProductInFireBase,
    val checkOrderExists: CheckOrderExists
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemDetailViewModel(
            getOrderFromFireBase = getOrderFromFireBase,
            orderInsertCloud = orderInsertCloud,
            updateProductInFireBase = updateProductInFireBase,
            checkOrderExists = checkOrderExists
        ) as T
    }

}