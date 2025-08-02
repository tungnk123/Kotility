package com.tungnk123.bitflags

import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

/**
 * Returns `true` if [flag] is set in the receiver [Int].
 */
fun Int.hasFlag(flag: Int): Boolean = (this and flag) == flag

/**
 * Returns a new [Int] with [flag] set.
 */
fun Int.withFlag(flag: Int): Int = this or flag

/**
 * Returns a new [Int] with [flag] cleared.
 */
fun Int.withoutFlag(flag: Int): Int = this and flag.inv()

/**
 * Returns `true` if [flag] is set in the receiver [Long].
 */
fun Long.hasFlag(flag: Long): Boolean = (this and flag) == flag

/**
 * Returns a new [Long] with [flag] set.
 */
fun Long.withFlag(flag: Long): Long = this or flag

/**
 * Returns a new [Long] with [flag] cleared.
 */
fun Long.withoutFlag(flag: Long): Long = this and flag.inv()

/**
 * Returns `true` if [flag] is set in the receiver [Short].
 */
fun Short.hasFlag(flag: Short): Boolean = (this and flag) == flag

/**
 * Returns a new [Short] with [flag] set.
 */
fun Short.withFlag(flag: Short): Short = (this or flag).toShort()

/**
 * Returns a new [Short] with [flag] cleared.
 */
fun Short.withoutFlag(flag: Short): Short = (this and flag.inv()).toShort()

/**
 * Returns `true` if [flag] is set in the receiver [Byte].
 */
fun Byte.hasFlag(flag: Byte): Boolean = (this and flag) == flag

/**
 * Returns a new [Byte] with [flag] set.
 */
fun Byte.withFlag(flag: Byte): Byte = (this or flag).toByte()

/**
 * Returns a new [Byte] with [flag] cleared.
 */
fun Byte.withoutFlag(flag: Byte): Byte = (this and flag.inv()).toByte()

/**
 * Returns `true` if [flag] is set in the receiver [UInt].
 */
fun UInt.hasFlag(flag: UInt): Boolean = (this and flag) == flag

/**
 * Returns a new [UInt] with [flag] set.
 */
fun UInt.withFlag(flag: UInt): UInt = this or flag

/**
 * Returns a new [UInt] with [flag] cleared.
 */
fun UInt.withoutFlag(flag: UInt): UInt = this and flag.inv()

/**
 * Returns `true` if [flag] is set in the receiver [ULong].
 */
fun ULong.hasFlag(flag: ULong): Boolean = (this and flag) == flag

/**
 * Returns a new [ULong] with [flag] set.
 */
fun ULong.withFlag(flag: ULong): ULong = this or flag

/**
 * Returns a new [ULong] with [flag] cleared.
 */
fun ULong.withoutFlag(flag: ULong): ULong = this and flag.inv()

/**
 * Returns `true` if [flag] is set in the receiver [UShort].
 */
fun UShort.hasFlag(flag: UShort): Boolean = (this and flag) == flag

/**
 * Returns a new [UShort] with [flag] set.
 */
fun UShort.withFlag(flag: UShort): UShort = this or flag

/**
 * Returns a new [UShort] with [flag] cleared.
 */
fun UShort.withoutFlag(flag: UShort): UShort = this and flag.inv()

/**
 * Returns `true` if [flag] is set in the receiver [UByte].
 */
fun UByte.hasFlag(flag: UByte): Boolean = (this and flag) == flag

/**
 * Returns a new [UByte] with [flag] set.
 */
fun UByte.withFlag(flag: UByte): UByte = this or flag

/**
 * Returns a new [UByte] with [flag] cleared.
 */
fun UByte.withoutFlag(flag: UByte): UByte = this and flag.inv()
