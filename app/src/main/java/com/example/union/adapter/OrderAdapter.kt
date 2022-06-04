package com.example.union.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import kotlinx.android.synthetic.main.item.view.iv_productPhoto
import kotlinx.android.synthetic.main.item.view.tvCity
import kotlinx.android.synthetic.main.item.view.tvCount
import kotlinx.android.synthetic.main.item.view.tvName
import kotlinx.android.synthetic.main.item.view.tvPrice
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter(val context: Context) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var listProduct = emptyList<ProductDomain>()
    var listOrder = emptyList<OrderDomain>()

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.itemView.tvName.text = listProduct[position].productName
        holder.itemView.tvCity.text = listProduct[position].city
        holder.itemView.tvCount.text =
            "${listProduct[position].totalAmount} из ${listProduct[position].amount}"
        holder.itemView.tvPrice.text = "${listProduct[position].productPrice}$ за шт"
        holder.itemView.tvTotalAmount.text = "Вы берете: ${listOrder[position].totalAmount}шт"
        Glide
            .with(context)
            .load(listProduct[position].productPhoto)
            .error(R.drawable.ic_empty_photo)
            .dontAnimate()
            .into(holder.itemView.iv_productPhoto)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listProduct: List<ProductDomain>, listOrder: List<OrderDomain>) {
        var list = mutableListOf<ProductDomain>()
        listOrder.forEach { order ->
            listProduct.forEach { product ->
                if (product.productID == order.productID)
                    list.add(product)
            }
        }
        this.listProduct = list
        this.listOrder = listOrder

        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: OrderViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("product", listProduct[holder.adapterPosition])
            bundle.putSerializable("order", listOrder[holder.adapterPosition])
            it.findNavController().navigate(R.id.action_profileFragment_to_orderDetailFragment, bundle)
        }
    }

    override fun onViewDetachedFromWindow(holder: OrderViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}