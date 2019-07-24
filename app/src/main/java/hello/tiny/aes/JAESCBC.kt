package hello.tiny.aes

import android.util.Base64
import defpackage.CAESCBC
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object JAESCBC {

    @Throws(Exception::class)
    fun encrypt(data: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(CAESCBC.generateKey(password), "AES"),
            IvParameterSpec(CAESCBC.IV_BYTES)
        )
        return Base64.encode(cipher.doFinal(data.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
            .toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun decrypt(data: String, password: String): String {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        cipher.init(
            Cipher.DECRYPT_MODE,
            SecretKeySpec(CAESCBC.generateKey(password), "AES"),
            IvParameterSpec(CAESCBC.IV_BYTES)
        )
        return cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
            .toString(Charsets.UTF_8)
    }
}