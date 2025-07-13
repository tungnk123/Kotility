package com.tungnk123.kotility.storage

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceDelegate<T>(
    private val prefs: SharedPreferences,
    private val key: String,
    private val defaultValue: T
) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(key, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(key, value)

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Unsupported type")
        }
        res as T
    }

    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Unsupported type")
        }.apply()
    }
}
