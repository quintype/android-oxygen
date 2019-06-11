package com.quintype.oxygen.utils.widgets

import android.util.Log
import java.util.*

/**
 * print the debug logs
 */
fun logdExt(message: String) {
    val stackTraceElement = Thread.currentThread().stackTrace
    val tag =
        String.format(Locale.getDefault(), "%s, %s :", stackTraceElement[3].fileName, stackTraceElement[3].methodName)
    Log.d(tag, message)
}

/**
 * print the error logs
 */
fun logeExt(message: String) {

    val stackTraceElement = Thread.currentThread().stackTrace
    val tag =
        String.format(Locale.getDefault(), "%s, %s :", stackTraceElement[3].fileName, stackTraceElement[3].methodName)
    Log.e(tag, message)
}