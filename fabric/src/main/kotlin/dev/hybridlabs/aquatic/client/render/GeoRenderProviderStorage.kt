package dev.hybridlabs.aquatic.client.render

import software.bernie.geckolib.animatable.client.RenderProvider

/**
 * Stores Geckolib render providers for use on the common side.
 * This allows us to reference renderers in Geckolib [software.bernie.geckolib.animatable.GeoItem]
 * code while still using split sources.
 *
 * These providers must be set on initialize to prevent lateinit
 * crashes.
 */
object GeoRenderProviderStorage {
    lateinit var divingArmorRenderProvider: () -> RenderProvider
    lateinit var reinforcedDivingArmorRenderProvider: () -> RenderProvider
    lateinit var glowingDivingArmorRenderProvider: () -> RenderProvider
    lateinit var seashellArmorRenderProvider: () -> RenderProvider
    lateinit var manglerfishArmorRenderProvider: () -> RenderProvider
    lateinit var turtleArmorRenderProvider: () -> RenderProvider
    lateinit var eelArmorRenderProvider: () -> RenderProvider
    lateinit var pinkHatxolotlArmorRenderProvider: () -> RenderProvider
    lateinit var cyanHatxolotlArmorRenderProvider: () -> RenderProvider
    lateinit var goldHatxolotlArmorRenderProvider: () -> RenderProvider
    lateinit var blueHatxolotlArmorRenderProvider: () -> RenderProvider
    lateinit var brownHatxolotlArmorRenderProvider: () -> RenderProvider
    lateinit var moonjellyfishArmorRenderProvider: () -> RenderProvider
}