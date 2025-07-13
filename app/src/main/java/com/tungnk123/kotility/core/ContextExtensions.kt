package com.tungnk123.kotility.core

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.net.toUri

fun Context.safeLaunchUrl(url: String) {
    try {
        val intent = Intent(
            Intent.ACTION_VIEW,
            url.toUri()
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
    catch (e: ActivityNotFoundException) {
        Log.e(
            "Kotility",
            "No activity found to handle URL: $url"
        )
    }
}
