package defpackage

@Suppress("FunctionName")
object CAES {

    init {
        System.loadLibrary("libcaes")
    }

    external fun encrypt_cbc(src: String, key: String, iv: String): String

    external fun decrypt_cbc(src: String, key: String, iv: String): String

    external fun encrypt_ctr(src: String, key: String, iv: String): String

    external fun decrypt_ctr(src: String, key: String, iv: String): String

    external fun encrypt_ecb(src: String, key: String, iv: String): String

    external fun decrypt_ecb(src: String, key: String, iv: String): String
}