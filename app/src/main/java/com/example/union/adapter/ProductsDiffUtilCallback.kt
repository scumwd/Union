package com.example.union.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.ProductDomain

class ProductsDiffUtilCallback(
    private val oldList: List<ProductDomain>,
    private val newList: List<ProductDomain>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.productID == newProduct.productID

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct == newProduct
    }

}