package com.example.union.presentation.screen.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.OrderAdapter
import com.example.union.databinding.ProfileFragmentBinding
import com.example.union.presentation.APP
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: ProfileFragmentBinding
    lateinit var adapter: OrderAdapter
    lateinit var mAuth: FirebaseAuth

    companion object {
        fun clickProduct(productDomain: ProductDomain, orderDomain: OrderDomain) {
            val bundle = Bundle()
            bundle.putSerializable("product", productDomain)
            bundle.putSerializable("order", orderDomain)
            APP.navController.navigate(R.id.action_profileFragment_to_orderDetailFragment, bundle)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mAuth = FirebaseAuth.getInstance()
        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        mAuth.currentUser?.let { viewModel.initDatabase(it.uid) }
        recyclerView = binding.rvOrder
        adapter = OrderAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllOrder().observe(viewLifecycleOwner, { listOrder ->
            viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
                adapter.setList(listProduct = listProduct, listOrder = listOrder)
            })
        })
    }


}