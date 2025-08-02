package com.tungnk123.kotility.toast

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.showToast(@StringRes resId: Int, isLong: Boolean = false) {
    Toast.makeText(this, resId, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: CharSequence, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(@StringRes resId: Int, isLong: Boolean = false) = requireContext().showToast(resId, isLong)

fun Fragment.showToast(message: CharSequence, isLong: Boolean = false) = requireContext().showToast(message, isLong)