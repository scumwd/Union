package com.example.union.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.domain.models.Product
import com.example.union.R
import com.example.union.presentation.APP
import com.example.union.presentation.screen.home.HomeFragment
import com.google.android.gms.fido.fido2.api.common.RequestOptions
import kotlinx.android.synthetic.main.authorization.view.*
import kotlinx.android.synthetic.main.item.view.*

class ProductAdapter(private val context: Context): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var listProduct = emptyList<Product>()


    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ProductViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.tvName.text = listProduct[position].productName
        holder.itemView.tvCity.text = listProduct[position].city
        holder.itemView.tvCount.text = "${listProduct[position].totalAmount} из ${listProduct[position].amount}"
        holder.itemView.tvPrice.text = "${listProduct[position].productPrice}$ за шт"
        val resId = R.drawable.elka
        Glide
            .with(context)
            .load(resId)
            .dontAnimate()
            .into(holder.itemView.iv_productPhoto)
        Log.e("link",listProduct[position].productPhoto )

    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Product>){
        listProduct = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ProductViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            HomeFragment.clickProduct(listProduct[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

}