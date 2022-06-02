package com.example.domain.getFromDb

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository

class GetOrderDb(val orderRepository: OrderRepository) {

    fun execute(): LiveData<List<OrderDomain>> {
        return orderRepository.allOrderDomain()
    }
}