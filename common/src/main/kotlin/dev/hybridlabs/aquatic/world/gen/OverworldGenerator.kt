package dev.hybridlabs.aquatic.world.gen

import dev.hybridlabs.aquatic.mixin.HolderReferenceInvoker
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import net.minecraft.world.level.levelgen.NoiseRouter
import net.minecraft.world.level.levelgen.SurfaceRules

/**
 * Access to the overworld's noise generator for the worldgen changes made before the server loads its levels.
 */
object OverworldGenerator {
    /**
     * The overworld's generator, or null when the overworld does not generate from noise.
     */
    fun find(server: MinecraftServer): NoiseBasedChunkGenerator? =
        server.registryAccess().registryOrThrow(Registries.LEVEL_STEM).get(LevelStem.OVERWORLD)?.generator() as? NoiseBasedChunkGenerator

    /**
     * The generator's registered noise settings. Inline settings have no registry holder to rebind, which only happens
     * with hand-written world presets.
     */
    fun registeredSettings(generator: NoiseBasedChunkGenerator): Holder.Reference<NoiseGeneratorSettings>? =
        generator.generatorSettings() as? Holder.Reference<NoiseGeneratorSettings>

    /**
     * Rebinds [settings] to a copy with the given noise router and surface rule.
     */
    fun rebindSettings(
        settings: Holder.Reference<NoiseGeneratorSettings>,
        noiseRouter: NoiseRouter = settings.value().noiseRouter(),
        surfaceRule: SurfaceRules.RuleSource = settings.value().surfaceRule(),
    ) {
        val current = settings.value()
        @Suppress("DEPRECATION")
        val changed = NoiseGeneratorSettings(
            current.noiseSettings(),
            current.defaultBlock(),
            current.defaultFluid(),
            noiseRouter,
            surfaceRule,
            current.spawnTarget(),
            current.seaLevel(),
            current.disableMobGeneration(),
            current.isAquifersEnabled(),
            current.oreVeinsEnabled(),
            current.useLegacyRandomSource(),
        )

        @Suppress("UNCHECKED_CAST")
        (settings as HolderReferenceInvoker<NoiseGeneratorSettings>).`hybridAquatic$bindValue`(changed)
    }
}
