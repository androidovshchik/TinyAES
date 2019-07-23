package defpackage

@Suppress("FunctionName")
object AES {

    init {
        System.loadLibrary("libaes")
    }

    external fun encrypt_cbc(): String

    external fun decrypt_cbc(): String

    external fun encrypt_ctr(): String

    external fun decrypt_ctr(): String

    external fun encrypt_ecb(): String

    external fun decrypt_ecb(): String
}