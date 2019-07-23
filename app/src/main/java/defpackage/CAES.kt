package defpackage

@Suppress("FunctionName")
object CAES {

    init {
        System.loadLibrary("libmain")
    }

    external fun encrypt_cbc(): String

    external fun decrypt_cbc(): String

    external fun encrypt_ctr(): String

    external fun decrypt_ctr(): String

    external fun encrypt_ecb(): String

    external fun decrypt_ecb(): String
}