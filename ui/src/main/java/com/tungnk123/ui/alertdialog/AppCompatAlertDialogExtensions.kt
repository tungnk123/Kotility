package com.tungnk123.ui.alertdialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CompletableDeferred

class DialogChoice<T>(val text: CharSequence, val result: T) {
    companion object {
        fun <T> ok(context: Context, value: T): DialogChoice<T> =
            DialogChoice(context.getText(android.R.string.ok), value)

        fun <T> cancel(context: Context, value: T): DialogChoice<T> =
            DialogChoice(context.getText(android.R.string.cancel), value)
    }
}

suspend fun AlertDialog.awaitOkOrDismiss(context: Context): Unit = awaitDialogResult(
    context = context,
    positive = DialogChoice.ok(context, Unit),
    dismissResult = Unit
)

suspend fun <R> AlertDialog.awaitDialogResult(
    context: Context,
    okResult: R,
    cancelResult: R,
    dismissResult: R
): R = awaitDialogResult(
    context = context,
    positive = DialogChoice.ok(context, okResult),
    negative = DialogChoice.cancel(context, cancelResult),
    dismissResult = dismissResult
)

@JvmName("awaitDialogResultOptionalCancel")
suspend fun <R : Any> AlertDialog.awaitDialogResult(
    context: Context,
    okResult: R,
    cancelResult: R? = null,
    dismissResult: R
): R = awaitDialogResult(
    context = context,
    positive = DialogChoice.ok(context, okResult),
    negative = cancelResult?.let { DialogChoice.cancel(context, it) },
    dismissResult = dismissResult
)

suspend fun <R> AlertDialog.awaitDialogResult(
    context: Context,
    okResult: R,
    negative: DialogChoice<R>,
    dismissResult: R
): R = awaitDialogResult(
    context = context,
    positive = DialogChoice.ok(context, okResult),
    negative = negative,
    dismissResult = dismissResult
)

suspend fun <R> AlertDialog.awaitDialogResult(
    context: Context,
    positive: DialogChoice<R>? = null,
    negative: DialogChoice<R>? = null,
    neutral: DialogChoice<R>? = null,
    dismissResult: R
): R {
    val resultDeferred = CompletableDeferred<R>()

    val clickListener = DialogInterface.OnClickListener { _, which ->
        val selectedChoice = when (which) {
            DialogInterface.BUTTON_POSITIVE -> positive
            DialogInterface.BUTTON_NEGATIVE -> negative
            DialogInterface.BUTTON_NEUTRAL -> neutral
            else -> null
        }
        selectedChoice?.let { resultDeferred.complete(it.result) }
    }

    positive?.let { setButton(DialogInterface.BUTTON_POSITIVE, it.text, clickListener) }
    negative?.let { setButton(DialogInterface.BUTTON_NEGATIVE, it.text, clickListener) }
    neutral?.let { setButton(DialogInterface.BUTTON_NEUTRAL, it.text, clickListener) }

    setOnDismissListener {
        resultDeferred.complete(dismissResult)
    }

    return try {
        show()
        resultDeferred.await()
    } finally {
        dismiss()
    }
}
