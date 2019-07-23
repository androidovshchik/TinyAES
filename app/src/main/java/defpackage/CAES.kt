package defpackage

@Suppress("FunctionName")
object CAES {

    init {
        System.loadLibrary("libaes")
    }

    external fun encrypt_cbc(src: String): String

    external fun decrypt_cbc(src: String): String

    external fun encrypt_ctr(src: String): String

    external fun decrypt_ctr(src: String): String

    external fun encrypt_ecb(src: String): String

    external fun decrypt_ecb(src: String): String
}