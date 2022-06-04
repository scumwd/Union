package com.example.union.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.OrdersWithUsers
import com.example.union.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listUser = emptyList<OrdersWithUsers?>()

    class UserViewHolder (view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.tv_userName.text = "${listUser[position]?.firstName} ${listUser[position]?.lastName}: "
        holder.itemView.tv_userBuy.text = "${listUser[position]?.totalAmount}шт"
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listUser: List<OrdersWithUsers?>){
        this.listUser = listUser
        notifyDataSetChanged()
    }
}