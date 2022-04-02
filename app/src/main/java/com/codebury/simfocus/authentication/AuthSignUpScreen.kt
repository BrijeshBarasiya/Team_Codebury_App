package com.codebury.simfocus.authentication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.codebury.simfocus.R
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.sign

class AuthSignUpScreen : AppCompatActivity() {

    var storageRef = FirebaseStorage.getInstance().getReference("profiles")
    lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_sign_up_screen)

        supportActionBar?.hide()

        val profileImage: CircleImageView = findViewById(R.id.profileImageView)
        val signUpBtn: Button = findViewById(R.id.signUpBtn)

        var launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

                if(it.resultCode == RESULT_OK) {
                    profileImage.setImageURI(it.data?.data)
                    imageUri = it.data?.data!!
                } else if(it.resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,"Image has not been selected", Toast.LENGTH_SHORT).show()
                }
            }
        )

        profileImage.setOnClickListener {
            var i = Intent()
            i.action = Intent.ACTION_GET_CONTENT
            i.type = "image/*"
            launcher.launch(i)
        }

        signUpBtn.setOnClickListener {
            uploadToFirebase(imageUri)
        }
    }

    private fun uploadToFirebase(imageUri: Uri) {
        var ref =  storageRef.child(System.currentTimeMillis().toString() +"."+ getExtension(imageUri))
        ref.putFile(imageUri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                var url = it
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
        }.addOnProgressListener {

        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getExtension(imageUri: Uri): String {
        var h = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(imageUri)).toString()
        Toast.makeText(this, h, Toast.LENGTH_SHORT).show()
        return h
    }
}