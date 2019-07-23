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
        launch {
            encrypt_cpp.text = "JAVA ENCRYPT: Подождите..."
            val result = withContext(Dispatchers.Default) {
                JAESCBC.encrypt(getString(R.string.test))
            }
            encrypt_cpp.text = "JAVA ENCRYPT: $result"
        }
        launch {
            encrypt_cpp.text = "CPP ENCRYPT: Подождите..."
            val result = withContext(Dispatchers.Default) {
                CAES.encrypt_cbc(getString(R.string.test))
            }
            encrypt_cpp.text = "CPP ENCRYPT: $result"
        }
        launch {
            decrypt_java.text = "JAVA DECRYPT: Подождите..."
            val result = withContext(Dispatchers.Default) {
                JAESCBC.decrypt(getString(R.string.test))
            }
            decrypt_java.text = "JAVA DECRYPT: $result"
        }
        launch {
            decrypt_cpp.text = "CPP DECRYPT: Подождите..."
            val result = withContext(Dispatchers.Default) {
                CAES.decrypt_cbc(getString(R.string.test))
            }
            decrypt_cpp.text = "CPP DECRYPT: $result"
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