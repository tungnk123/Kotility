package com.tungnk123.bundle

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import java.io.Serializable

/**
 * Dynamically adds multiple key-value pairs to this [Bundle].
 */
fun Bundle.putExtrasCompat(extras: Map<String, Any?>) {
    extras.forEach { (key, value) -> putDynamic(key, value) }
}

/**
 * Dynamically inserts a value into this [Bundle] with the given [key].
 * Supports a variety of types, including primitives, arrays, lists,
 * [Parcelable], [Serializable], and more.
 *
 * @throws IllegalArgumentException if the type is unsupported
 */
fun Bundle.putDynamic(key: String, value: Any?) {
    when (value) {
        null -> putString(key, null) // Accepts all nullable types

        // Single primitive types
        is Int -> putInt(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is Boolean -> putBoolean(key, value)
        is Char -> putChar(key, value)
        is Byte -> putByte(key, value)
        is Short -> putShort(key, value)

        // Primitive arrays
        is IntArray -> putIntArray(key, value)
        is ShortArray -> putShortArray(key, value)
        is LongArray -> putLongArray(key, value)
        is ByteArray -> putByteArray(key, value)
        is FloatArray -> putFloatArray(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is BooleanArray -> putBooleanArray(key, value)
        is CharArray -> putCharArray(key, value)

        // Text types
        is String -> putString(key, value)
        is CharSequence -> putCharSequence(key, value)

        // Complex structures
        is Bundle -> putBundle(key, value)
        is Array<*> -> putTypedArray(key, value)
        is ArrayList<*> -> putArrayListTyped(key, value)
        is SparseArray<*> -> putSparseParcelableArrayTyped(key, value)

        // Framework objects
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)

        else -> throw IllegalArgumentException("Unsupported type for key \"$key\": ${value::class.java.name}")
    }
}

/**
 * Safely inserts a typed [Array] into this [Bundle] with automatic detection.
 */
private fun Bundle.putTypedArray(key: String, array: Array<*>) {
    @Suppress("UNCHECKED_CAST")
    when {
        array.isArrayOf<String>() -> putStringArray(key, array as Array<String>)
        array.isArrayOf<CharSequence>() -> putCharSequenceArray(key, array as Array<CharSequence>)
        array.isArrayOf<Parcelable>() -> putParcelableArray(key, array as Array<Parcelable>)
        else -> throw IllegalArgumentException("Unsupported array type for key \"$key\": ${array::class.java.componentType?.name}")
    }
}

/**
 * Safely inserts a typed [ArrayList] into this [Bundle] with automatic detection.
 */
private fun Bundle.putArrayListTyped(key: String, list: ArrayList<*>) {
    @Suppress("UNCHECKED_CAST")
    when (list.firstOrNull()) {
        null -> putIntegerArrayList(key, null) // Handle empty lists as nullable
        is String -> putStringArrayList(key, list as ArrayList<String>)
        is CharSequence -> putCharSequenceArrayList(key, list as ArrayList<CharSequence>)
        is Parcelable -> putParcelableArrayList(key, list as ArrayList<Parcelable>)
        is Int -> putIntegerArrayList(key, list as ArrayList<Int>)
        else -> throw IllegalArgumentException("Unsupported ArrayList type for key \"$key\": ${list.first()!!::class.java.name}")
    }
}

/**
 * Safely inserts a [SparseArray] of [Parcelable] into this [Bundle].
 */
private fun Bundle.putSparseParcelableArrayTyped(key: String, sparseArray: SparseArray<*>) {
    @Suppress("UNCHECKED_CAST")
    putSparseParcelableArray(key, sparseArray as SparseArray<Parcelable>)
}