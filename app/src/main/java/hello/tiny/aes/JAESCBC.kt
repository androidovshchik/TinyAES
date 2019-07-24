package hello.tiny.aes

import android.util.Base64
import java.security.GeneralSecurityException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object JAESCBC {

    @Throws(GeneralSecurityException::class)
    fun encrypt(src: String, key: String, iv: String): String {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        cipher.init(
            Cipher.ENCRYPT_MODE, SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES"),
            IvParameterSpec(iv.toByteArray(Charsets.UTF_8))
        )
        return Base64.encode(cipher.doFinal(src.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
            .toString(Charsets.UTF_8)
    }

    @Throws(GeneralSecurityException::class)
    fun decrypt(src: String, key: String, iv: String): String {
        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
        cipher.init(
            Cipher.DECRYPT_MODE, SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES"),
            IvParameterSpec(iv.toByteArray(Charsets.UTF_8))
        )
        return cipher.doFinal(Base64.decode(src, Base64.DEFAULT))
            .toString(Charsets.UTF_8)
    }
}