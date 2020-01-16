package hello.tiny.aes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        title = "AES/CBC/Pkcs7"
        btn_decrypt.setOnClickListener {
            val data = et_data.text.toString()
            val key = et_key.text.toString()
            val iv = et_iv.text.toString()
            launch {
                tv_decrypted.text = withContext(Dispatchers.Default) {
                    Crypto.encrypt(data, key, iv)
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