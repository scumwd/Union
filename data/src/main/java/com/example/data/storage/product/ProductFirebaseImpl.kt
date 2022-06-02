package com.example.data.storage.product

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.example.data.PRODUCT_IMAGE_KEY
import com.example.data.PRODUCT_KEY
import com.example.data.models.ProductCloud
import com.example.data.models.ProductCloudData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.ByteArrayOutputStream
import java.util.*

class ProductFirebaseImpl: ProductFirebase {

    private lateinit var mStorageReference: StorageReference

    override suspend fun uploadImage(photo: ImageView): String {
        mStorageReference = FirebaseStorage.getInstance().getReference(PRODUCT_IMAGE_KEY)
        val bitMap =
            (photo.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArray = baos.toByteArray()
        val mRef = mStorageReference.child(
            UUID.randomUUID().toString()
        )
        val up = mRef.putBytes(byteArray)
        val productPhoto: String = suspendCancellableCoroutine {
            val task = up.continueWithTask { mRef.downloadUrl }
                .addOnCompleteListener { task ->
                    it.resume(task.result.toString()) {}
                }
        }
        return productPhoto
    }

    override suspend fun update(productCloud: ProductCloud) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(PRODUCT_KEY)
        messageRef.child(productCloud.productID).child("totalAmount")
            .setValue(productCloud.totalAmount)
    }

    override suspend fun insert(productCloud: ProductCloud) {
        val productUID = UUID.randomUUID()
        val database = Firebase.database
        productCloud.productID = productUID.toString()
        val myRef = database.getReference(PRODUCT_KEY)
        myRef.child(productUID.toString()).setValue(productCloud)
    }

    override suspend fun getProducts(): List<ProductCloudData?> {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(PRODUCT_KEY)
        val listProducts: List<ProductCloudData?> = suspendCancellableCoroutine {
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val productsList: MutableList<ProductCloudData?> = mutableListOf<ProductCloudData?>()
                    for (ds in dataSnapshot.children) {
                        val messages: ProductCloudData? = ds.getValue(ProductCloudData::class.java)
                        productsList.add(messages)
                    }
                    it.resume(productsList) {}
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("firebaseErrorProduct", error.message)
                }
            }
            messageRef.addListenerForSingleValueEvent(valueEventListener)
        }
        listProducts.forEach {
            Log.e("dsaaa", it?.getProductCity().toString())
        }

        return listProducts

    }
}