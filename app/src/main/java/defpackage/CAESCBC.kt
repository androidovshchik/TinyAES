package defpackage

import android.util.Base64
import java.security.MessageDigest

/**
 * Важны имена пакета и класса
 */
@Suppress("FunctionName")
object CAESCBC {

    /**
     * Пример IV
     */
    val IV_BYTES = byteArrayOf(
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
    )

    init {
        System.loadLibrary("libcaescbc")
    }

    @Throws(Exception::class)
    fun encrypt(data: String, password: String): String {
        return Base64.encode(encrypt(getPaddedData(data), get256BitKey(password), IV_BYTES), Base64.NO_WRAP)
            .toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun decrypt(data: String, password: String): String {
        return decrypt(Base64.decode(data, Base64.NO_WRAP), get256BitKey(password), IV_BYTES)
            .toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun getPaddedData(data: String): ByteArray {
        val plainBytes = data.toByteArray(Charsets.UTF_8)
        return if (plainBytes.size % 16 != 0) {
            val paddedBytes = ByteArray((plainBytes.size / 16 + 1) * 16)
            System.arraycopy(plainBytes, 0, paddedBytes, 0, plainBytes.size)
            paddedBytes
        } else {
            plainBytes
        }
    }

    /**
     * Получение 256 битного (32 байта) ключа из пароля
     * Можно обойтись без этого, но тогда придется как-то по-другому задавать ключ
     * например, как [IV_BYTES]
     */
    @Throws(Exception::class)
    fun get256BitKey(password: String): ByteArray {
        MessageDigest.getInstance("SHA-256").apply {
            return digest(password.toByteArray(Charsets.UTF_8))
        }
    }

    private external fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray

    private external fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray
}