package com.tungnk123.kotility.core

inline fun <T> tryOrNull(block: () -> T): T? {
    return try {
        block()
    } catch (e: Exception) {
        null
    }
}

inline fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T {
    return if (condition) this.block() else this
}

