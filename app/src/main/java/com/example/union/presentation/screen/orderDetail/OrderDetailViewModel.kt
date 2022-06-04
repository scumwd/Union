package com.example.union.presentation.screen.orderDetail

import androidx.lifecycle.ViewModel
import com.example.domain.getFromDb.GetOrderById
import com.example.domain.models.OrdersWithUsers
import javax.inject.Inject

class OrderDetailViewModel@Inject constructor(
    private val getOrderById: GetOrderById
) : ViewModel() {
    suspend fun getOrderByProductLink(productLink: String): List<OrdersWithUsers?> {
        return getOrderById.execute(productLink)
    }
}