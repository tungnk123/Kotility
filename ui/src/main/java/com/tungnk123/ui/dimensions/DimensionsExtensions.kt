package com.tungnk123.ui.dimensions

import android.content.Context
import android.view.View

/**
 * Converts a value in dp (density-independent pixels) to px (pixels).
 * @param dp The value in dp.
 * @return The converted value in px as an [Int].
 */
fun Context.dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()

/**
 * Converts a value in dp (density-independent pixels) to px (pixels).
 * @param dp The value in dp.
 * @return The converted value in px as a [Float].
 */
fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density

/**
 * Converts a value in px (pixels) to dp (density-independent pixels).
 * @param px The value in px.
 * @return The converted value in dp as an [Int].
 */
fun Context.pxToDp(px: Int): Int = (px / resources.displayMetrics.density).toInt()

/**
 * Converts a value in px (pixels) to dp (density-independent pixels).
 * @param px The value in px.
 * @return The converted value in dp as a [Float].
 */
fun Context.pxToDp(px: Float): Float = px / resources.displayMetrics.density

fun View.dpToPx(dp: Int): Int = context.dpToPx(dp)
fun View.dpToPx(dp: Float): Float = context.dpToPx(dp)
fun View.pxToDp(px: Int): Int = context.pxToDp(px)
fun View.pxToDp(px: Float): Float = context.pxToDp(px)
