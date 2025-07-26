package com.tungnk123.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Lazily retrieves a required argument of type [T] from Fragment arguments.
 * Throws if the value is missing.
 */
fun <T : Any> Fragment.arg(): ReadWriteProperty<Fragment, T> =
    RequiredArgDelegate()

/**
 * Lazily retrieves an optional argument of type [T] from Fragment arguments.
 * Returns null if not set.
 */
fun <T> Fragment.argOrNull(): ReadWriteProperty<Fragment, T?> =
    OptionalArgDelegate()

/**
 * Lazily retrieves an argument or returns the provided default value [defaultValue].
 */
fun <T : Any> Fragment.argOrDefault(defaultValue: T): ReadWriteProperty<Fragment, T> =
    DefaultArgDelegate { defaultValue }

/**
 * Lazily retrieves an argument or computes the default value via [defaultValue].
 */
fun <T : Any> Fragment.argOrElse(defaultValue: () -> T): ReadWriteProperty<Fragment, T> =
    DefaultArgDelegate(defaultValue)

/**
 * Lazily retrieves a required argument from a [SavedStateHandle] by key.
 */
fun <T : Any> SavedStateHandle.arg(key: String): ReadOnlyProperty<Any?, T> =
    SavedStateArgDelegate(key)

/**
 * Lazily retrieves an argument from [SavedStateHandle] or returns a default.
 */
fun <T : Any> SavedStateHandle.argOrDefault(
    key: String,
    defaultValue: () -> T,
): ReadOnlyProperty<Any?, T> =
    SavedStateDefaultArgDelegate(
        key,
        defaultValue
    )

private class RequiredArgDelegate<T : Any> : ReadWriteProperty<Fragment, T> {
    private var cached: T? = null

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>,
    ): T {
        cached?.let { return it }
        val key = property.name
        val value = thisRef.arguments?.get(key)
            ?: error("Missing required argument: $key")
        @Suppress("UNCHECKED_CAST")
        return (value as T).also { cached = it }
    }

    override fun setValue(
        thisRef: Fragment,
        property: KProperty<*>,
        value: T,
    ) {
        thisRef.putArg(
            property.name,
            value
        )
        cached = value
    }
}

private class OptionalArgDelegate<T> : ReadWriteProperty<Fragment, T?> {
    private var cached: T? = null
    private var initialized = false

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>,
    ): T? {
        if (!initialized) {
            cached = thisRef.arguments?.get(property.name) as T?
            initialized = true
        }
        return cached
    }

    override fun setValue(
        thisRef: Fragment,
        property: KProperty<*>,
        value: T?,
    ) {
        thisRef.putArg(
            property.name,
            value
        )
        cached = value
        initialized = true
    }
}

private class DefaultArgDelegate<T : Any>(
    private val defaultProvider: () -> T,
) : ReadWriteProperty<Fragment, T> {
    private var cached: T? = null

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>,
    ): T {
        cached?.let { return it }
        val key = property.name

        @Suppress("UNCHECKED_CAST")
        val result = (thisRef.arguments?.get(key) as T?) ?: defaultProvider()
        cached = result
        return result
    }

    override fun setValue(
        thisRef: Fragment,
        property: KProperty<*>,
        value: T,
    ) {
        thisRef.putArg(
            property.name,
            value
        )
        cached = value
    }
}

private class SavedStateArgDelegate<T : Any>(private val key: String) : ReadOnlyProperty<Any?, T> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T {
        val handle = thisRef as? SavedStateHandle
            ?: error("SavedStateHandle required")
        return handle[key] ?: error("Missing required argument: $key")
    }
}

private class SavedStateDefaultArgDelegate<T : Any>(
    private val key: String,
    private val defaultProvider: () -> T,
) : ReadOnlyProperty<Any?, T> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T {
        val handle = thisRef as? SavedStateHandle
            ?: error("SavedStateHandle required")
        return handle[key] ?: defaultProvider()
    }
}

internal fun Fragment.putArg(
    key: String,
    value: Any?,
) {
    val args = arguments ?: Bundle().also { arguments = it }
    args.putDynamic(key, value)
}

/** DSL to put multiple arguments into Fragment arguments safely. */
fun Fragment.putArgs(builder: Bundle.() -> Unit) {
    val args = arguments ?: Bundle().also { arguments = it }
    args.builder()
}