package com.example.domain.cloud

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.ByteArrayOutputStream
import java.util.*

class UploadProductImage {

    private lateinit var mStorageReference: StorageReference
    private val PRODUCT_IMAGE_KEY: String = "ProductImage"

    suspend fun execute(photo: ImageView): String {
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
}