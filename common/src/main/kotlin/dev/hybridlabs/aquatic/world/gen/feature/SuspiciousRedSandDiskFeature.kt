package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.loot.HALootTables
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.IntProviders
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.entity.BlockEntityTypes
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.DiskFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class SuspiciousRedSandDiskFeature(
    val stateProvider: BlockStateProvider,
    val target: BlockPredicate,
    val radius: IntProvider,
    val halfHeight: Int,
) : Feature {
    override fun codec(): MapCodec<SuspiciousRedSandDiskFeature> = CODEC

    override fun place(
        level: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        val placed = DiskFeature(Holder.direct(this.stateProvider), this.target, this.radius, this.halfHeight)
            .place(level, chunkGenerator, random, origin)
        if (!placed) return false

        val radius = this.radius.sample(random)

        BlockPos.betweenClosed(
            origin.offset(-radius, -this.halfHeight, -radius),
            origin.offset(radius, this.halfHeight, radius)
        ).forEach { pos ->
            if (level.getBlockState(pos).`is`(HABlocks.SUSPICIOUS_RED_SAND.get())) {
                level.getBlockEntity(pos, BlockEntityTypes.BRUSHABLE_BLOCK)
                    .ifPresent { brushable ->
                        brushable.setLootTable(
                            (ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HALootTables.PLACER_RIVER_ARCHAEOLOGY_ID)),
                            pos.asLong()
                        )
                    }
            }
        }

        return true
    }

    companion object {
        val CODEC: MapCodec<SuspiciousRedSandDiskFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("state_provider")
                    .forGetter(SuspiciousRedSandDiskFeature::stateProvider),
                BlockPredicate.CODEC.fieldOf("target").forGetter(SuspiciousRedSandDiskFeature::target),
                IntProviders.codec(0, 8).fieldOf("radius").forGetter(SuspiciousRedSandDiskFeature::radius),
                com.mojang.serialization.Codec.intRange(0, 4).fieldOf("half_height")
                    .forGetter(SuspiciousRedSandDiskFeature::halfHeight),
            ).apply(instance, ::SuspiciousRedSandDiskFeature)
        }
    }
}
