package dev.hybridlabs.aquatic.util

import net.minecraft.util.Mth

/**
 * Trigonometry helpers. `Mth.sin`/`Mth.cos` now take a [Double], but most entity maths here is in
 * floats, so this keeps call sites readable.
 */
object HAMath {
    @JvmStatic
    fun sin(value: Number): Float = Mth.sin(value.toDouble())

    @JvmStatic
    fun cos(value: Number): Float = Mth.cos(value.toDouble())
}
