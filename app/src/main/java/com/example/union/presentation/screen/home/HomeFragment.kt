package com.example.union.presentation.screen.home

import android.R
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.union.adapter.ProductAdapter
import com.example.union.databinding.HomeFragmentBinding
import com.example.union.presentation.MainActivity

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        (activity as MainActivity).factory
    }

    lateinit var recyclerView: RecyclerView
    lateinit var binding: HomeFragmentBinding
    lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val pd = Dialog(requireContext(), R.style.Theme_Black)
        val view: View = LayoutInflater.from(context).inflate(com.example.union.R.layout.progressbar, null)
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE)
        pd.window?.setBackgroundDrawableResource(com.example.union.R.color.transparent)
        pd.setContentView(view)

        viewModel.getUser()
        viewModel.getProductsFromFireBase()
        recyclerView = binding.rvProduct
        adapter = ProductAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
            adapter.setList(listProduct.asReversed())
        })
    }
}