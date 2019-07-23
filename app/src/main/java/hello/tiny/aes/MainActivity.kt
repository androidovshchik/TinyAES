package hello.tiny.aes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import defpackage.AES

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AES.decrypt_cbc()
    }
}