package dev.hybridlabs.aquatic.client

import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.RenderProvider

/**
 * Stores Geckolib render providers for use on the common side.
 * This allows us to reference renderers in Geckolib [GeoItem]
 * code while still using split sources.
 *
 * These providers must be set on initialize to prevent lateinit
 * crashes.
 */
object GeoRenderProviderStorage {
    lateinit var divingArmorRenderProvider: () -> RenderProvider
    lateinit var seashellArmorRenderProvider: () -> RenderProvider
    lateinit var manglerfishArmorRenderProvider: () -> RenderProvider
    lateinit var turtleArmorRenderProvider: () -> RenderProvider
    lateinit var eelArmorRenderProvider: () -> RenderProvider
    lateinit var moonjellyfishArmorRenderProvider: () -> RenderProvider
}
