package com.example.socialmediaapp.android.common.util

private const val CURRENT_BASE_URL = "http://192.168.105.64:8080/"

fun String.toCurrentUrl(): String {
    // Remove the fixed prefix (if present)
    val prefixToRemove = "http://0.0.0.0:8080/"
    val cleanedString = if (this.startsWith(prefixToRemove)) {
        this.substring(prefixToRemove.length)
    } else {
        this
    }

    return "$CURRENT_BASE_URL$cleanedString"
}