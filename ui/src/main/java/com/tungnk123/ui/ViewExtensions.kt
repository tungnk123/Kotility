package com.tungnk123.ui

import android.view.View

fun View.setOnDebouncedClickListener(debounceTime: Long = 500L, onClick: (View) -> Unit) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            onClick(it)
        }
    }
}
