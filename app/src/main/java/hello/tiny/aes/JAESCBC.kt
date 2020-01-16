package hello.tiny.aes

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object JAESCBC {

    @Throws(Exception::class)
    fun encrypt(data: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/Pkcs7")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(CAESCBC.get256BitKey(password), "AES"),
            IvParameterSpec(CAESCBC.IV_BYTES)
        )
        return Base64.encode(cipher.doFinal(CAESCBC.addZeroBytes(data)), Base64.NO_WRAP)
            .toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun decrypt(data: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/Pkcs7")
        cipher.init(
            Cipher.DECRYPT_MODE,
            SecretKeySpec(CAESCBC.get256BitKey(password), "AES"),
            IvParameterSpec(CAESCBC.IV_BYTES)
        )
        return cipher.doFinal(Base64.decode(data, Base64.NO_WRAP))
            .toString(Charsets.UTF_8)
            .replace(0.toChar().toString(), "")
    }
}