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
import com.codebury.simfocus.helper.Constants
import com.codebury.simfocus.helper.uploadToFirebase
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
            uploadToFirebase(this,imageUri,Constants.PROFILE_PICTURE_FOLDER)
        }
    }




}