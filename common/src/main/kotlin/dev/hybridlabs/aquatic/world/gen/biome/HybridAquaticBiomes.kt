package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Climate.Parameter
import net.minecraft.world.level.biome.Climate.ParameterPoint
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.*

object HybridAquaticBiomes {
    val TIDE_POOLS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val TIDE_POOL_SURFACE_RULE: RuleSource = ifTrue(isBiome(TIDE_POOLS), state(Blocks.SAND.defaultBlockState()))

    fun addBiomes() {
        BiomePlacement.addOverworld(
            TIDE_POOLS, ParameterPoint(
                Parameter.span(-1f, 1f),
                Parameter.span(-0.45f, -0.15f),
                Parameter.span(-0.195f, -0.110f),
                Parameter.span(-0.25f, 0.05f),
                Parameter.point(0f),
                Parameter.span(-0.267f, 0.05f),
                0
            )
        )
        SurfaceGeneration.addOverworldSurfaceRules(
            ResourceLocation("minecraft", "rules/overworld"),
            ifTrue(abovePreliminarySurface(), sequence(TIDE_POOL_SURFACE_RULE))
        )
    }
}