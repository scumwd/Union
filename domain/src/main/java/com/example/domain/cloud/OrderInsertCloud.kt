package com.example.domain.cloud

import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderInsertCloud(val orderRepository: OrderRepository) {

    fun insert(orderDomain: OrderDomain) {
        GlobalScope.launch(Dispatchers.IO){
            orderRepository.insertOrderFireBase(orderDomain)
        }
    }

}