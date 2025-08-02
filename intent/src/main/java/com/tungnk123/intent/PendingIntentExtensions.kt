package com.tungnk123.intent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tungnk123.bitflags.withFlag

/**
 * Returns a [PendingIntent] that launches a single activity using this [Intent].
 */
fun Intent.toActivityPendingIntent(
    context: Context,
    requestCode: Int = 0,
    options: Bundle? = null,
    pendingOptions: PendingIntentOptions = PendingIntentOptions()
): PendingIntent {
    val flags = pendingOptions.toFlagBitmask()
    return PendingIntent.getActivity(context, requestCode, this, flags, options)
}

/**
 * Returns a [PendingIntent] that launches multiple activities.
 */
fun Array<Intent>.toActivitiesPendingIntent(
    context: Context,
    requestCode: Int = 0,
    options: Bundle? = null,
    pendingOptions: PendingIntentOptions = PendingIntentOptions()
): PendingIntent {
    val flags = pendingOptions.toFlagBitmask()
    return PendingIntent.getActivities(context, requestCode, this, flags, options)
}

/**
 * Returns a [PendingIntent] that starts a foreground service using this [Intent].
 */
fun Intent.toForegroundServicePendingIntent(
    context: Context,
    requestCode: Int = 0,
    pendingOptions: PendingIntentOptions = PendingIntentOptions()
): PendingIntent {
    val flags = pendingOptions.toFlagBitmask()
    return PendingIntent.getForegroundService(context, requestCode, this, flags)
}

/**
 * Returns a [PendingIntent] that starts a regular service using this [Intent].
 */
fun Intent.toServicePendingIntent(
    context: Context,
    requestCode: Int = 0,
    pendingOptions: PendingIntentOptions = PendingIntentOptions()
): PendingIntent {
    val flags = pendingOptions.toFlagBitmask()
    return PendingIntent.getService(context, requestCode, this, flags)
}

/**
 * Returns a [PendingIntent] that sends a broadcast using this [Intent].
 */
fun Intent.toBroadcastPendingIntent(
    context: Context,
    requestCode: Int = 0,
    pendingOptions: PendingIntentOptions = PendingIntentOptions()
): PendingIntent {
    val flags = pendingOptions.toFlagBitmask()
    return PendingIntent.getBroadcast(context, requestCode, this, flags)
}

/**
 * Converts the [PendingIntentOptions] to an appropriate flag bitmask.
 */
@PublishedApi
internal fun PendingIntentOptions.toFlagBitmask(): Int {
    var flags = additionalFlags

    val mutabilityFlag = if (isMutable) PendingIntent.FLAG_MUTABLE else PendingIntent.FLAG_IMMUTABLE
    flags = flags.withFlag(mutabilityFlag)

    val behaviorFlag = when {
        isOneShot -> PendingIntent.FLAG_ONE_SHOT
        cancelCurrent -> PendingIntent.FLAG_CANCEL_CURRENT
        else -> PendingIntent.FLAG_UPDATE_CURRENT
    }

    return flags.withFlag(behaviorFlag)
}
