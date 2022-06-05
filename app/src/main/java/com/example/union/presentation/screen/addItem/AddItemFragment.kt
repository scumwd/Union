package com.example.union.presentation.screen.addItem


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.domain.models.ProductDomain
import com.example.union.databinding.AddItemFragmentBinding
import android.view.Window
import android.app.Dialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.union.R
import com.example.union.R.*
import com.example.union.presentation.MainActivity
import kotlinx.coroutines.launch


class AddItemFragment : Fragment() {


    lateinit var binding: AddItemFragmentBinding

    private lateinit var ivProductPhoto: ImageView

    private val viewModel: AddItemViewModel by viewModels {
        (activity as MainActivity).factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddItemFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        ivProductPhoto = binding.ivProductPhoto

        binding.edProductPhoto.setOnClickListener {
            getImage()
        }

        binding.btnSend.setOnClickListener {
            viewModel.getProductsFromFireBase()
            checkFields()
        }
    }

    private fun getImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null && data.data != null) {
                    Log.e("data", data.data.toString())
                    ivProductPhoto.setImageURI(data.data as Uri)
                }
            }
        }

    private fun checkFields() {
        val pd = Dialog(requireContext(), android.R.style.Theme_Black)
        val view: View = LayoutInflater.from(context).inflate(layout.progressbar, null)
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE)
        pd.window?.setBackgroundDrawableResource(color.transparent)
        pd.setContentView(view)

        val linkProduct = binding.edProductLink.text

        if (ivProductPhoto.drawable != null) {
            if (linkProduct != null && Patterns.WEB_URL.matcher(linkProduct.toString()).matches()) {
                val listProduct = viewModel.chekProduct(linkProduct.toString())
                if (listProduct != null && listProduct.city == binding.edProductCity.text.toString()) {
                    Toast.makeText(
                        requireContext(),
                        "Данный товар уже опубликован.",
                        Toast.LENGTH_LONG
                    ).show()
                    val bundle = Bundle()
                    bundle.putSerializable(
                        "product",
                        listProduct
                    )
                    findNavController().navigate(
                        R.id.action_addItemFragment_to_itemDetailFragment,
                        bundle
                    )
                } else {
                    binding.run {
                        if (edProductName.text.toString().isEmpty()
                            || edProductLink.text.toString().isEmpty()
                            || edProductAmount.text.toString().isEmpty()
                            || edProductCity.text.toString().isEmpty()
                            || edProductPrice.text.toString().isEmpty()
                        )
                            Toast.makeText(
                                requireContext(),
                                "Пожалуйста, заполните все поля.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        else {
                            lifecycleScope.launch {
                                pd.show()
                                val productPhoto = viewModel.uploadImage(ivProductPhoto)
                                val productName = edProductName.text.toString()
                                val productLink = edProductLink.text.toString()
                                val productAmount =
                                    edProductAmount.text.toString().toInt()
                                val productCity = edProductCity.text.toString()
                                val productPrice =
                                    edProductPrice.text.toString().toDouble()
                                saveProduct(
                                    productPhoto = productPhoto,
                                    productName = productName,
                                    productLink = productLink,
                                    productPrice = productPrice,
                                    productCity = productCity,
                                    productAmount = productAmount
                                )
                                viewModel.getProductsFromFireBase()
                                pd.hide()
                            }
                        }
                    }
                }

            } else
                Toast.makeText(
                    requireContext(),
                    "Введенная ссылка не корректна.",
                    Toast.LENGTH_SHORT
                )
                    .show()
        } else {
            Toast.makeText(requireContext(), "Пожалуйста, выберите изображение.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun saveProduct(
        productPhoto: String,
        productName: String,
        productLink: String,
        productAmount: Int,
        productCity: String,
        productPrice: Double
    ) {

        val productDomain = ProductDomain(
            productName = productName,
            productLink = productLink,
            productPhoto = productPhoto,
            productPrice = productPrice,
            city = productCity,
            amount = productAmount
        )
        viewModel.insertInFireBase(productDomain)
        viewModel.getProductsFromFireBase()
        Toast.makeText(
            requireContext(),
            "Товар опубликован успешно.",
            Toast.LENGTH_LONG
        ).show()
    }
}

