package com.codebury.simfocus.authentication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isNotEmpty
import androidx.core.widget.doOnTextChanged
import com.codebury.simfocus.R
import com.codebury.simfocus.helper.Constants
import com.codebury.simfocus.helper.uploadToFirebase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

import java.io.File
import java.util.regex.Pattern
import kotlin.math.sign
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.etDepartment
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.etEmail
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.etFirstName
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.etJobTitle
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.etLastName
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.profileImageView
import kotlinx.android.synthetic.main.activity_auth_sign_up_screen.signUpBtn

class AuthSignUpScreen : AppCompatActivity() {

    var storageRef = FirebaseStorage.getInstance().getReference("profiles")
    lateinit var imageUri: Uri
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@simformsolutions.com" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_sign_up_screen)
        supportActionBar?.hide()
        imageUri = Uri.fromFile(File("../../drawable/default_avatar.png"))

        var launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

                if (it.resultCode == RESULT_OK) {
                    profileImageView.setImageURI(it.data?.data)
                    imageUri = it.data?.data!!
                } else if (it.resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Image has not been selected", Toast.LENGTH_SHORT).show()
                }
            }
        )

        profileImageView.setOnClickListener {
            var i = Intent()
            i.action = Intent.ACTION_GET_CONTENT
            i.type = "image/*"
            launcher.launch(i)
        }
        etFirstName.doOnTextChanged { text, start, before, count ->
            if (etFirstName.length()==0) {
                etFirstName.error = getString(R.string.enterFirstNAme)
            }
        }
        etLastName.doOnTextChanged { text, start, before, count ->
            if (etLastName.length()==0) {
                etLastName.error = getString(R.string.enterLastName)
            }
        }
        etEmail.doOnTextChanged { text, start, before, count ->
            if (etEmail.length() == 0 ) {
                etEmail.error = getString(R.string.invalidUsernameError)
            }
        }
        etDepartment.doOnTextChanged { text, start, before, count ->
            if (etDepartment.text.toString().isEmpty()) {
                etDepartment.error = getString(R.string.departmentError)
            }
        }
        etJobTitle.doOnTextChanged { text, start, before, count ->
            if (etJobTitle.text.toString().isEmpty()) {
                etJobTitle.error = getString(R.string.jobTittleText)
            }
        }
        signUpBtn.setOnClickListener {

                uploadToFirebase(this, imageUri, Constants.PROFILE_PICTURE_FOLDER)

                if(etEmail.text.toString() == "" || etFirstName.text.toString() == "" || etLastName.text.toString() == "" ) {
                etEmail.error = "Required*"
                etFirstName.error = "Required*"
                etLastName.error = "Required*"
            } else if(emailPattern.matcher(etEmail.text!!).matches()) {
                etEmail.error = getString(R.string.invalidUsernameError)
            } else {
                Toast.makeText(this, "Welcome To SimFocus", Toast.LENGTH_SHORT).show()
            }


        }

    }
}