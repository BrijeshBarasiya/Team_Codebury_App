package AddPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codebury.simfocus.R

class AddPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        val postBtn: Button = findViewById(R.id.postNowBtn)
        postBtn.setOnClickListener {

        }
    }
}