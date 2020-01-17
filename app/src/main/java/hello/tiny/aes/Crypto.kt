package hello.tiny.aes

import android.util.Base64
import timber.log.Timber
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Crypto {

    @Throws(Exception::class)
    fun encrypt(data: String, key: String, iv: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(key.toByteArray(), "AES"),
            IvParameterSpec(iv.toByteArray())
        )
        return Base64.encode(cipher.doFinal(data.toByteArray()), Base64.NO_WRAP)
            .toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun decrypt(data: String, key: String, iv: String): String {
        Timber.e(SecretKeySpec(key.toByteArray(), "AES").toString())
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(
            Cipher.DECRYPT_MODE,
            SecretKeySpec(key.toByteArray(), "AES"),
            IvParameterSpec(iv.toByteArray())
        )
        return cipher.doFinal(Base64.decode(data, Base64.NO_WRAP))
            .toString(Charsets.UTF_8)
    }
}