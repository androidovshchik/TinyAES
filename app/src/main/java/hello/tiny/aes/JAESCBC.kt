package hello.tiny.aes

import java.io.UnsupportedEncodingException
import java.security.Key
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object JAESCBC {

    fun encrypt(src: String): String {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv())
            return Base64.encodeBytes(cipher.doFinal(src.toByteArray()))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    fun decrypt(src: String): String {
        var decrypted = ""
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv())
            decrypted = String(cipher.doFinal(Base64.decode(src)))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        return decrypted
    }


    fun makeIv(): AlgorithmParameterSpec? {
        try {
            return IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return null
    }

    fun makeKey(): Key? {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            val key = md.digest(ENCRYPTION_KEY.getBytes("UTF-8"))
            return SecretKeySpec(key, "AES")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return null
    }
}