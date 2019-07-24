/**
 * Можно выбрать размер ключа между 128, 192 и 256 бит в файле <aes.h>, раскомментировав определенную строку.
 * По умолчанию 128
 *     #define AES128 1
 *     //#define AES192 1
 *     //#define AES256 1
 */

#include <jni.h>
#include "aes/aes.hpp"

/**
 * Закомментируйте #define, если не нужен определенный режим
 */
#define CBC 1 // NOLINT(cppcoreguidelines-macro-usage)
#define CTR 1 // NOLINT(cppcoreguidelines-macro-usage)
#define ECB 1 // NOLINT(cppcoreguidelines-macro-usage)

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_encrypt_cbc(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

    const jclass stringClass = env->GetObjectClass(src);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");

    const jstring charsetName = env->NewStringUTF("UTF-8");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(_s, getBytes, charsetName);
    env->DeleteLocalRef(charsetName);
}

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_decrypt_cbc(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

}

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_encrypt_ctr(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

}

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_decrypt_ctr(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

}

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_encrypt_ecb(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

}

extern "C"

JNIEXPORT jstring JNICALL Java_defpackage_CAES_decrypt_ecb(JNIEnv *env, jobject, jstring src, jstring key, jstring iv) {

}

const uint8_t *convertBytes(JNIEnv *env, jbyteArray array) {
    const jsize length = env->GetArrayLength(array);
    const jbyte *bytes = env->GetByteArrayElements(array, nullptr);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, (uint8_t *) bytes, (uint8_t *) bytes);
    AES_ECB_encrypt(&ctx, (uint8_t *) bytes);
    return (const uint8_t *) bytes;
}