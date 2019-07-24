package hello.tiny.aes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import defpackage.CAESCBC
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
        input_data.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }
        start_java.setOnClickListener {
            val password = input_password.text.toString()
            launch {
                encrypt_java.text = "Подождите..."
                decrypt_java.text = "Подождите..."
                var time = System.currentTimeMillis()
                encrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.encrypt(input_data.text.toString(), password)
                }
                Timber.d("JAVA1 ${System.currentTimeMillis() - time} мс")
                Timber.d(encrypt_java.text.toString())
                time = System.currentTimeMillis()
                decrypt_java.text = withContext(Dispatchers.Default) {
                    JAESCBC.decrypt(encrypt_java.text.toString(), password)
                }
                Timber.d("JAVA2 ${System.currentTimeMillis() - time} мс")
                Timber.d("\"${decrypt_java.text}\"")
            }
        }
        start_cpp.setOnClickListener {
            val password = input_password.text.toString()
            launch {
                encrypt_cpp.text = "Подождите..."
                decrypt_cpp.text = "Подождите..."
                var time = System.currentTimeMillis()
                encrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAESCBC.encrypt(input_data.text.toString(), password)
                }
                Timber.d("CPP1 ${System.currentTimeMillis() - time} мс")
                Timber.d(encrypt_cpp.text.toString())
                time = System.currentTimeMillis()
                decrypt_cpp.text = withContext(Dispatchers.Default) {
                    CAESCBC.decrypt(encrypt_cpp.text.toString(), password)
                }
                Timber.d("CPP2 ${System.currentTimeMillis() - time} мс")
                Timber.d("\"${decrypt_cpp.text}\"")
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