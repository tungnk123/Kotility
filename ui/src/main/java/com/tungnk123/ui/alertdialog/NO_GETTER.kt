package com.tungnk123.ui.alertdialog

const val NO_GETTER = "Property does not have a getter"

@Suppress("NOTHING_TO_INLINE")
inline fun noGetter(): Nothing = throw UnsupportedOperationException(NO_GETTER)
