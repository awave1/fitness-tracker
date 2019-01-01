package com.awave.wrkout.util

import java.security.MessageDigest

object CryptoUtils {
    fun SHA256Hash(inputData: ByteArray) = MessageDigest.getInstance("SHA-256").digest(inputData)
    fun SHA256Hash(text: String) = String(SHA256Hash(text.toByteArray()))
}