package defpackage

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
        System.loadLibrary("libcaes")
    }

    fun encrypt(data: String, password: String): String {
        return encrypt(data.toByteArray(Charsets.UTF_8), generateKey(password), IV_BYTES)
    }

    fun decrypt(data: String, password: String): String {
        return decrypt(data.toByteArray(Charsets.UTF_8), generateKey(password), IV_BYTES)
    }

    @Throws(Exception::class)
    fun generateKey(password: String): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray(Charsets.UTF_8)
        digest.update(bytes, 0, bytes.size)
        return digest.digest()
    }

    private external fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): String

    private external fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): String
}