package com.tungnk123.intent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tungnk123.bundle.putExtrasCompat

/**
 * Creates a new [Intent] to start the specified [T] class.
 *
 * @param extras Optional extras to attach to the intent using key-value map.
 * @return A new [Intent] configured with optional extras.
 */
inline fun <reified T> Context.newIntent(
    extras: Map<String, Any?>? = null
): Intent = Intent(this, T::class.java).apply {
    extras?.let { putExtrasCompat(it) }
}

/**
 * Adds a map of key-value pairs to this [Intent]'s extras,
 * using dynamic type inference.
 *
 * @param extras Map of key-value pairs to add.
 * @return This [Intent] instance for chaining.
 */
fun Intent.putExtrasCompat(extras: Map<String, Any?>): Intent {
    if (extras.isNotEmpty()) {
        val bundle = Bundle().apply { putExtrasCompat(extras) }
        putExtras(bundle)
    }
    return this
}

/**
 * Adds a single key-value pair as extra to this [Intent],
 * with dynamic type resolution.
 *
 * @param key The key for the extra.
 * @param value The value to store.
 * @return This [Intent] instance for chaining.
 */
fun Intent.putExtraDynamic(key: String, value: Any?): Intent {
    val bundle = Bundle().apply { putExtrasCompat(mapOf(key to value)) }
    putExtras(bundle)
    return this
}
