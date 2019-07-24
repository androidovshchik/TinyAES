package hello.tiny.aes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import defpackage.CAES
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        title = "AES256/CBC/NoPadding"
        start_java.setOnClickListener {
            val key = input_key.text.toString()
            val iv = input_iv.text.toString()
            launch {
                encrypt_java.text = "Подождите..."
                decrypt_java.text = "Подождите..."
                encrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.encrypt(input_data.text.toString(), key, iv)
                }
                decrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.decrypt(encrypt_java.text.toString(), key, iv)
                }
            }
        }
        start_cpp.setOnClickListener {
            val key = input_key.text.toString()
            val iv = input_iv.text.toString()
            launch {
                encrypt_cpp.text = "Подождите..."
                decrypt_cpp.text = "Подождите..."
                encrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAES.encrypt_cbc(input_data.text.toString(), key, iv)
                }
                decrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAES.decrypt_cbc(encrypt_cpp.text.toString(), key, iv)
                }
            }
        }
    }

    override fun onDestroy() {
        job.cancelChildren()
        super.onDestroy()
    }

    override val coroutineContext = Dispatchers.Main + job + CoroutineExceptionHandler { _, e ->
        Timber.e(e)
        toast(e.message.toString())
    }
}