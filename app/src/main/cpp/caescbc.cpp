/**
 * Можно выбрать размер ключа между 128, 192 и 256 бит в файле <aes.h>, раскомментировав там соотв. строку
 * По умолчанию 128
 *     //#define AES192 1
 *     #define AES256 1
 */

#include <jni.h>
#include "aes/aes.hpp"

#define CBC 1

extern "C"

JNIEXPORT jbyteArray JNICALL
Java_defpackage_CAESCBC_encrypt(JNIEnv *env, jobject, jbyteArray data, jbyteArray key, jbyteArray iv) {
    uint8_t *dataBytes = (uint8_t *) env->GetByteArrayElements(data, 0);
    uint8_t *keyBytes = (uint8_t *) env->GetByteArrayElements(key, 0);
    uint8_t *ivBytes = (uint8_t *) env->GetByteArrayElements(iv, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, keyBytes, ivBytes);
    AES_CBC_encrypt_buffer(&ctx, dataBytes, (uint32_t) env->GetArrayLength(data));
    env->ReleaseByteArrayElements(data, (jbyte *) dataBytes, 0);
    env->ReleaseByteArrayElements(key, (jbyte *) keyBytes, 0);
    env->ReleaseByteArrayElements(iv, (jbyte *) ivBytes, 0);
    return data;
}

extern "C"

JNIEXPORT jbyteArray JNICALL
Java_defpackage_CAESCBC_decrypt(JNIEnv *env, jobject, jbyteArray data, jbyteArray key, jbyteArray iv) {
    uint8_t *dataBytes = (uint8_t *) env->GetByteArrayElements(data, 0);
    uint8_t *keyBytes = (uint8_t *) env->GetByteArrayElements(key, 0);
    uint8_t *ivBytes = (uint8_t *) env->GetByteArrayElements(iv, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, keyBytes, ivBytes);
    AES_CBC_decrypt_buffer(&ctx, dataBytes, (uint32_t) env->GetArrayLength(data));
    env->ReleaseByteArrayElements(data, (jbyte *) dataBytes, 0);
    env->ReleaseByteArrayElements(key, (jbyte *) keyBytes, 0);
    env->ReleaseByteArrayElements(iv, (jbyte *) ivBytes, 0);
    return data;
}