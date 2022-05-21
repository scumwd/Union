package com.example.union.presentation.screen.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.ProductAdapter
import com.example.union.databinding.HomeFragmentBinding
import com.example.union.presentation.APP
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: HomeFragmentBinding
    lateinit var adapter: ProductAdapter
    lateinit var mAuth: FirebaseAuth

    companion object {
        fun clickProduct(productDomain: ProductDomain) {
            val bundle = Bundle()
            bundle.putSerializable("product", productDomain)
            APP.navController.navigate(R.id.action_homeFragment_to_itemDetailFragment, bundle)
        }
    }

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
        mAuth = FirebaseAuth.getInstance()
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mAuth.currentUser?.let { viewModel.initDatabase(it.uid) }
        recyclerView = binding.rvProduct
        adapter = ProductAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
            adapter.setList(listProduct.asReversed())
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}