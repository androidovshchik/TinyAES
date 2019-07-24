#include <jni.h>

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

    const jsize length = env->GetArrayLength(stringJbytes);
    const jbyte *pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);
    env->DeleteLocalRef(stringJbytes);


    jbyte *dataPtr = (*env)->GetByteArrayElements(env, data, NULL);
    real_T res = detection((const uint8_t *) dataPtr);
    (*env)->ReleaseByteArrayElements(env, data, dataPtr, JNI_ABORT);
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

uint8_t toBytes(jstring param) {
    const jclass stringClass = env->GetObjectClass(src);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");

    const jstring charsetName = env->NewStringUTF("UTF-8");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(_s, getBytes, charsetName);
    env->DeleteLocalRef(charsetName);

    const jsize length = env->GetArrayLength(stringJbytes);
    const jbyte *pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);
    env->DeleteLocalRef(stringJbytes);


    jbyte *dataPtr = (*env)->GetByteArrayElements(env, data, NULL);
    real_T res = detection((const uint8_t *) dataPtr);
    (*env)->ReleaseByteArrayElements(env, data, dataPtr, JNI_ABORT);
}