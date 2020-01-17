package hello.tiny.aes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibatching.kotpref.bulk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    private lateinit var preferences: Preferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        preferences = Preferences(applicationContext)
        setContentView(R.layout.activity_main)
        title = "AES/CBC/Pkcs7"
        et_key.setText(preferences.pKey)
        et_iv.setText(preferences.pIv)
        btn_decrypt.setOnClickListener {
            val data = et_data.text.toString()
            val key = et_key.text.toString()
            val iv = et_iv.text.toString()
            preferences.bulk {
                pKey = key
                pIv = iv
            }
            launch {
                tv_decrypted.text = withContext(Dispatchers.Default) {
                    Crypto.decrypt(data, key, iv)
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