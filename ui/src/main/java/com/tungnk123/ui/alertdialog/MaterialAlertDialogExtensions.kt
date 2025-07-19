@file:OptIn(ExperimentalContracts::class)

package com.tungnk123.ui.alertdialog

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Context.materialAlertDialog(
    config: MaterialAlertDialogBuilder.() -> Unit
): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return MaterialAlertDialogBuilder(this)
        .apply(config)
        .create()
}

inline fun Context.materialAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    @DrawableRes iconRes: Int = 0,
    isCancellable: Boolean = true,
    config: MaterialAlertDialogBuilder.() -> Unit = {}
): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return materialAlertDialog(
        title = title,
        message = message,
        icon = null,
        isCancellable = isCancellable,
    ) {
        setIcon(iconRes)
        config()
    }
}

inline fun Context.materialAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    icon: Drawable? = null,
    isCancellable: Boolean = true,
    config: MaterialAlertDialogBuilder.() -> Unit = {}
): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return MaterialAlertDialogBuilder(this).apply {
        setTitle(title)
        setMessage(message)
        setIcon(icon)
        setCancelable(isCancellable)
        config()
    }.create()
}

inline var MaterialAlertDialogBuilder.backgroundInsetStart: Int
    @Deprecated("Write-only property", level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setBackgroundInsetStart(value)
    }

inline var MaterialAlertDialogBuilder.backgroundInsetTop: Int
    @Deprecated("Write-only property", level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setBackgroundInsetTop(value)
    }

inline var MaterialAlertDialogBuilder.backgroundInsetEnd: Int
    @Deprecated("Write-only property", level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setBackgroundInsetEnd(value)
    }

inline var MaterialAlertDialogBuilder.backgroundInsetBottom: Int
    @Deprecated("Write-only property", level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setBackgroundInsetBottom(value)
    }
