package com.example.domain.getFromDb

import com.example.domain.models.OrderDomain
import com.example.domain.models.OrdersWithUsers
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.UserRepository

class GetOrderById(val orderRepository: OrderRepository, val userRepository: UserRepository) {

    suspend fun execute(productLink: String): List<OrdersWithUsers?> {
        val listOrdersWithUsers: MutableList<OrdersWithUsers> = ArrayList()
        val listOrder: List<OrderDomain?> = orderRepository.getOrderById(productLink)
        if (listOrder.isEmpty()){
            return emptyList()
        }
        else{
            listOrder.forEach {
                val user = it?.userId?.let { it1 -> userRepository.getUsersById(it1) }
                val orders = OrdersWithUsers(
                    firstName = user?.firstName,
                    lastName = user?.lastName,
                    totalAmount = it?.totalAmount
                )
                listOrdersWithUsers.add(orders)
            }
        }
        return listOrdersWithUsers
    }
}