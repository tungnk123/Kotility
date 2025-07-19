@file:OptIn(ExperimentalContracts::class)

package com.tungnk123.ui.alertdialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Context.createAlertDialog(config: AlertDialog.Builder.() -> Unit): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return AlertDialog.Builder(this).apply(config).create()
}

inline fun Context.createAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    @DrawableRes iconRes: Int = 0,
    cancellable: Boolean = true,
    config: AlertDialog.Builder.() -> Unit = {}
): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return createAlertDialog(
        title = title,
        message = message,
        icon = null,
        cancellable = cancellable
    ) {
        setIcon(iconRes)
        config()
    }
}

inline fun Context.createAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    icon: Drawable? = null,
    cancellable: Boolean = true,
    config: AlertDialog.Builder.() -> Unit = {}
): AlertDialog {
    contract { callsInPlace(config, InvocationKind.EXACTLY_ONCE) }
    return AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setIcon(icon)
        setCancelable(cancellable)
        config()
    }.create()
}

inline fun AlertDialog.onShown(crossinline action: AlertDialog.() -> Unit): AlertDialog = apply {
    setOnShowListener { action() }
}

inline fun AlertDialog.Builder.onDismissed(crossinline action: (DialogInterface) -> Unit) {
    setOnDismissListener { action(it) }
}

val AlertDialog.positiveButton: Button
    get() = requireNotNull(getButton(AlertDialog.BUTTON_POSITIVE)) {
        "Positive button not available or dialog not shown yet."
    }

val AlertDialog.neutralButton: Button
    get() = requireNotNull(getButton(AlertDialog.BUTTON_NEUTRAL)) {
        "Neutral button not available or dialog not shown yet."
    }

val AlertDialog.negativeButton: Button
    get() = requireNotNull(getButton(AlertDialog.BUTTON_NEGATIVE)) {
        "Negative button not available or dialog not shown yet."
    }

var AlertDialog.Builder.titleRes: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(@StringRes value) {
        setTitle(value)
    }

var AlertDialog.Builder.titleText: CharSequence?
    @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setTitle(value)
    }

var AlertDialog.Builder.messageRes: Int
    @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(@StringRes value) {
        setMessage(value)
    }

var AlertDialog.Builder.messageText: CharSequence?
    @Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter()
    set(value) {
        setMessage(value)
    }

inline fun AlertDialog.Builder.okButton(crossinline action: (DialogInterface) -> Unit = {}) {
    setPositiveButton(android.R.string.ok) { dialog, _ -> action(dialog) }
}

inline fun AlertDialog.Builder.cancelButton(crossinline action: (DialogInterface) -> Unit = {}) {
    setNegativeButton(android.R.string.cancel) { dialog, _ -> action(dialog) }
}

inline fun AlertDialog.Builder.positiveButton(
    @StringRes textRes: Int,
    crossinline action: (DialogInterface) -> Unit
) {
    setPositiveButton(textRes) { dialog, _ -> action(dialog) }
}

inline fun AlertDialog.Builder.neutralButton(
    @StringRes textRes: Int,
    crossinline action: (DialogInterface) -> Unit
) {
    setNeutralButton(textRes) { dialog, _ -> action(dialog) }
}

inline fun AlertDialog.Builder.negativeButton(
    @StringRes textRes: Int,
    crossinline action: (DialogInterface) -> Unit
) {
    setNegativeButton(textRes) { dialog, _ -> action(dialog) }
}
