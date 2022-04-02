package com.codebury.simfocus.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.codebury.simfocus.R
import com.google.firebase.storage.FirebaseStorage

private lateinit var alertDialog: AlertDialog

fun loadImage(url : String?, uploadImage : ImageView){
    Glide.with(uploadImage).load(url).error(com.google.android.material.R.drawable.mtrl_ic_error).into(uploadImage)
}

fun uploadToFirebase(context: Context,imageUri: Uri, folderName: String ,completionHandler: (input: String) -> Unit) {

    val storageRef = FirebaseStorage.getInstance().getReference(folderName)
    val ref =  storageRef.child(System.currentTimeMillis().toString() +"."+ getExtension(context,imageUri))
    var url = ""
    var isShowing = false

    ref.putFile(imageUri)
        .addOnSuccessListener {
            endLoading()
            Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show()
            ref.downloadUrl.addOnSuccessListener {
                url = it.toString()
                completionHandler(url)
            }
        }.addOnProgressListener {
            if(!isShowing) {
                startLoading(context as Activity)
            }
            isShowing = true

        }.addOnFailureListener{
            endLoading()
            completionHandler("")
            Toast.makeText(context, "Image Upload Failed !!", Toast.LENGTH_SHORT).show()
    }
}

private fun getExtension(context: Context, imageUri: Uri): String {
    return MimeTypeMap.getSingleton()
        .getExtensionFromMimeType(context.contentResolver.getType(imageUri)).toString()
}

fun startLoading(activity: Activity) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    val inflater: LayoutInflater = activity.layoutInflater
    builder.setView(inflater.inflate(R.layout.loading_dialog, null))
    builder.setCancelable(false)
    alertDialog = builder.create()

    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alertDialog.show()
    alertDialog.window?.setLayout(200,200)
}

fun endLoading() {
    alertDialog.dismiss()
}

