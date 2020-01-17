package hello.tiny.aes

import android.content.Context
import com.chibatching.kotpref.KotprefModel

class Preferences(context: Context) : KotprefModel(context) {

    override val kotprefName: String = "${context.packageName}_preferences"

    var pKey by stringPref("", "key")

    var pIv by stringPref("", "iv")
}