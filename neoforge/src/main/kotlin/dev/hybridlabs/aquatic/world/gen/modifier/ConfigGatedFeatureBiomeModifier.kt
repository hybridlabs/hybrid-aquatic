package dev.hybridlabs.aquatic.world.gen.modifier

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.config.HAConfigHolder
import dev.hybridlabs.aquatic.world.gen.feature.FeatureToggle
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryAccess
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo

/**
 * NeoForge counterpart to the Fabric biome modifications.
 *
 * Normal generated biome modifiers are static, so a runtime config edit could never remove a feature. This
 * modifier stores only the feature data and checks the config when the biome source is built.
 */
data class ConfigGatedFeatureBiomeModifier(
    val biomes: HolderSet<Biome>,
    val features: HolderSet<PlacedFeature>,
    val step: GenerationStep.Decoration,
    val toggle: FeatureToggle,
) : BiomeModifier {
    override fun modify(
        registries: RegistryAccess,
        biome: Holder<Biome>,
        phase: BiomeModifier.Phase,
        builder: ModifiableBiomeInfo.BiomeInfo.Builder,
    ) {
        if (phase != BiomeModifier.Phase.ADD || !biomes.contains(biome)) return
        if (!toggle.isEnabled(HAConfigHolder.config.featureConfig)) return

        val generationSettings = builder.generationSettings
        features.forEach { generationSettings.addFeature(step, it) }
    }

    override fun codec(): MapCodec<out BiomeModifier> = CODEC

    companion object {
        val CODEC: MapCodec<ConfigGatedFeatureBiomeModifier> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter(ConfigGatedFeatureBiomeModifier::biomes),
                PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(ConfigGatedFeatureBiomeModifier::features),
                GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(ConfigGatedFeatureBiomeModifier::step),
                FeatureToggle.CODEC.fieldOf("toggle").forGetter(ConfigGatedFeatureBiomeModifier::toggle),
            ).apply(instance, ::ConfigGatedFeatureBiomeModifier)
        }
    }
}
