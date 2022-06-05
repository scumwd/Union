package com.example.union.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.ProductDomain
import com.example.union.R
import kotlinx.android.synthetic.main.item.view.*


class ProductAdapter(val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var listProduct = emptyList<ProductDomain>()

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ProductViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.tvName.text = listProduct[position].productName
        holder.itemView.tvCity.text = listProduct[position].city
        holder.itemView.tvCount.text =
            "${listProduct[position].totalAmount} из ${listProduct[position].amount}"
        holder.itemView.tvPrice.text = "${listProduct[position].productPrice}$ за шт"
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
    fun setList(list: List<ProductDomain>) {
        val localList = mutableListOf<ProductDomain>()
        list.forEach { listProduct->
            if(listProduct.amount > listProduct.totalAmount)
                localList.add(listProduct)
        }

        val diffUtilCallback = ProductsDiffUtilCallback(listProduct, localList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        listProduct = localList
        diffResult.dispatchUpdatesTo(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFiltered(list: List<ProductDomain>) {
        listProduct = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ProductViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("product", listProduct[holder.adapterPosition])
            it.findNavController().navigate(R.id.action_homeFragment_to_itemDetailFragment, bundle)
        }
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}