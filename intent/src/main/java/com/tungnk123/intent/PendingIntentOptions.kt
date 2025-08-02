package com.tungnk123.intent

/**
 * Options used for building [PendingIntent] flags.
 *
 * This class provides a clean way to configure common flag options for [PendingIntent] creation.
 *
 * @property isMutable Whether the [PendingIntent] should be mutable.
 *                     - `true`: allows the intent to be modified after creation (required for some use cases).
 *                     - `false` (default): safer and preferred for security.
 *
 * @property isOneShot Whether this [PendingIntent] can only be used once.
 *                     - `true`: the system invalidates it after sending it once.
 *                     - `false` (default): can be reused.
 *
 * @property cancelCurrent If set to `true`, cancels any existing [PendingIntent] that matches
 *                         the new one and creates a new instance.
 *                         - This overrides any existing one with the same parameters.
 *                         - If `false` (default), the system will update extras instead.
 *
 * @property additionalFlags Any additional custom flags to be OR-ed with the generated ones.
 *                           Defaults to `0`. Example: [PendingIntent.FLAG_NO_CREATE].
 */
data class PendingIntentOptions(
    val isMutable: Boolean = false,
    val isOneShot: Boolean = false,
    val cancelCurrent: Boolean = false,
    val additionalFlags: Int = 0
)
