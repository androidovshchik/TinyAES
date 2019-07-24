/**
 * Можно выбрать размер ключа между 128, 192 и 256 бит в файле <aes.h>, раскомментировав там соотв. строку
 * По умолчанию 128
 *     //#define AES192 1
 *     #define AES256 1
 */

#include <jni.h>
#include "aes/aes.hpp"

/**
 * Раскомментируйте #define, если нужен определенный режим
 */
#define CBC 1 // NOLINT(cppcoreguidelines-macro-usage)
//#define CTR 1
//#define ECB 1

extern "C"

JNIEXPORT jbyteArray JNICALL Java_defpackage_CAESCBC_encrypt(JNIEnv *env, jobject, jbyteArray data, jbyteArray key, jbyteArray iv) {
    jbyteArray out = env->NewByteArray(32);
    uint8_t* publicKeyBytes  = (uint8_t*) env->GetByteArrayElements(out, 0);
    uint8_t* privateKeyBytes = (uint8_t*) env->GetByteArrayElements(privateKey, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);
    AES_CBC_encrypt_buffer(&ctx, publicKeyBytes, 64);
    env->ReleaseByteArrayElements(out, publicKeyBytes, 0);
    env->ReleaseByteArrayElements(privateKey, privateKeyBytes, 0);
    return out;
}

extern "C"

JNIEXPORT jbyteArray JNICALL Java_defpackage_CAESCBC_decrypt(JNIEnv *env, jobject, jbyteArray data, jbyteArray key, jbyteArray iv) {
    jbyteArray out = env->NewByteArray(32);
    uint8_t* publicKeyBytes  = (uint8_t*) env->GetByteArrayElements(out, 0);
    uint8_t* privateKeyBytes = (uint8_t*) env->GetByteArrayElements(privateKey, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);
    AES_CBC_decrypt_buffer(&ctx, publicKeyBytes, 64);
    env->ReleaseByteArrayElements(out, publicKeyBytes, 0);
    env->ReleaseByteArrayElements(privateKey, privateKeyBytes, 0);
    return out;
}