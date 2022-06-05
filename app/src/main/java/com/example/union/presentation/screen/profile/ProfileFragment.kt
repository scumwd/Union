package com.example.union.presentation.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.union.adapter.OrderAdapter
import com.example.union.databinding.ProfileFragmentBinding
import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.login.AuthenticationActivity

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels {
        (activity as MainActivity).factory
    }

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: OrderAdapter
    lateinit var binding: ProfileFragmentBinding
    lateinit var emptyRecyclerView: TextView


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

    @SuppressLint("SetTextI18n")
    private fun init() {

        emptyRecyclerView = binding.emptyRvProf

        displayUserInfo()
        viewModel.getOrder()

        recyclerView = binding.rvOrder
        adapter = OrderAdapter(requireContext())
        recyclerView.adapter = adapter

        displayOrders()

        binding.btnSignOut.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        viewModel.signOut()
        val intent = Intent(this.context, AuthenticationActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    @SuppressLint("SetTextI18n")
    private fun displayUserInfo() {
        viewModel.getUserDb().observe(viewLifecycleOwner, { user ->
            binding.tvName.text = "${user.firstName} ${user.lastName}"
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayOrders() {
        viewModel.getAllOrder().observe(viewLifecycleOwner, { localListOrder ->
            viewModel.getAllProduct().observe(viewLifecycleOwner, { localListProduct ->
                if (localListOrder.isNotEmpty()){
                    recyclerView.visibility = View.VISIBLE
                    emptyRecyclerView.visibility = View.GONE
                    adapter.setList(listProduct = localListProduct, listOrder = localListOrder)
                }
                else{
                    emptyRecyclerView.visibility = View.VISIBLE
                }

                binding.tvCountBuy.text = "Кол-во покупок: ${localListOrder.size}"
            })
        })
    }

}