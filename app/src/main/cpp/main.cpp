#include <jni.h>

/**
 * Закомментируйте #define, если не нужен определенный режим
 */
#define CBC 1 // NOLINT(cppcoreguidelines-macro-usage)
#define CTR 1 // NOLINT(cppcoreguidelines-macro-usage)
#define ECB 1 // NOLINT(cppcoreguidelines-macro-usage)

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_encrypt_cbc(JNIEnv *env, jobject, jstring pathObj) {

}

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_decrypt_cbc(JNIEnv *env, jobject, jstring pathObj) {

}

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_encrypt_ctr(JNIEnv *env, jobject, jstring pathObj) {

}

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_decrypt_ctr(JNIEnv *env, jobject, jstring pathObj) {

}

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_encrypt_ecb(JNIEnv *env, jobject, jstring pathObj) {

}

extern "C" JNIEXPORT jstring JNICALL

Java_defpackage_CAES_decrypt_ecb(JNIEnv *env, jobject, jstring pathObj) {

}