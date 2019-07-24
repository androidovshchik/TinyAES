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
                time_java1.text = "0 мс"
                time_java2.text = "0 мс"
                var time = System.currentTimeMillis()
                encrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.encrypt(input_data.text.toString(), key, iv)
                }
                time_java1.text = "${System.currentTimeMillis() - time} мс"
                time = System.currentTimeMillis()
                decrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.decrypt(encrypt_java.text.toString(), key, iv)
                }
                time_java2.text = "${System.currentTimeMillis() - time} мс"
            }
        }
        start_cpp.setOnClickListener {
            val key = input_key.text.toString()
            val iv = input_iv.text.toString()
            launch {
                encrypt_cpp.text = "Подождите..."
                decrypt_cpp.text = "Подождите..."
                time_cpp1.text = "0 мс"
                time_cpp2.text = "0 мс"
                var time = System.currentTimeMillis()
                encrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAES.encrypt_cbc(input_data.text.toString(), key, iv)
                }
                time_cpp1.text = "${System.currentTimeMillis() - time} мс"
                time = System.currentTimeMillis()
                decrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAES.decrypt_cbc(encrypt_cpp.text.toString(), key, iv)
                }
                time_cpp2.text = "${System.currentTimeMillis() - time} мс"
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