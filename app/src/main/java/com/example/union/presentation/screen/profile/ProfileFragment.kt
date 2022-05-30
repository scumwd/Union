package com.example.union.presentation.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.OrderAdapter
import com.example.union.app.App
import com.example.union.databinding.ProfileFragmentBinding
import com.example.union.presentation.APP
import com.example.union.presentation.screen.login.AuthenticationActivity
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory

    lateinit var viewModel: ProfileViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: OrderAdapter
    lateinit var binding: ProfileFragmentBinding

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

        (APP.applicationContext as App).appComponent.inject(this)

        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        viewModel.getOrder()
        recyclerView = binding.rvOrder
        adapter = OrderAdapter()
        recyclerView.adapter = adapter

        displayOrders()

        displayUserInfo()

        binding.btnSignOut.setOnClickListener {
            signOut()
        }
    }

    private fun signOut(){
        viewModel.signOut()
        val intent = Intent(this.context, AuthenticationActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    @SuppressLint("SetTextI18n")
    private fun displayUserInfo(){
        viewModel.getUser().observe(viewLifecycleOwner, { user ->
            binding.tvName.text = "${user.firstName} ${user.lastName}"
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayOrders(){
        viewModel.getAllOrder().observe(viewLifecycleOwner, { localListOrder ->
            viewModel.getAllProduct().observe(viewLifecycleOwner, { localListProduct ->
                adapter.setList(listProduct = localListProduct, listOrder = localListOrder)
                binding.tvCountBuy.text = "Кол-во покупок: ${localListOrder.size}"
            })
        })
    }

}