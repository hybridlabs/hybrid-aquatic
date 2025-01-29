package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "ocellaris" to FishVariant.biomeVariant("ocellaris", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "percula" to FishVariant.biomeVariant("percula", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "whiteband" to FishVariant.biomeVariant("whiteband", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "tomato" to FishVariant.biomeVariant("tomato", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "cinnamon" to FishVariant.biomeVariant("cinnamon", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "clarkii" to FishVariant.biomeVariant("clarkii", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "pink_skunk" to FishVariant.biomeVariant("pink_skunk", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "orange_skunk" to FishVariant.biomeVariant("orange_skunk", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        ),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.CLOWNFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "ocellaris" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "percula" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "whiteband" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "tomato" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "cinnamon" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "clarkii" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "pink_skunk" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            "orange_skunk" -> Identifier.of("hybrid-aquatic", "entities/clownfish")
            else -> super.getLootTableId()
        }
    }

    private var targetAnemonePos: BlockPos? = null

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 2.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.FOLLOW_RANGE, 12.0)
                .add(EntityAttributes.STEP_HEIGHT, 1.0)
        }
    }

    override fun tick() {
        super.tick()

        if (targetAnemonePos == null || world.getBlockState(targetAnemonePos).isOf(HybridAquaticBlocks.ANEMONE)) {
            targetAnemonePos = findNearbyAnemone()
        }

        if (targetAnemonePos != null) {
            val distanceToAnemone = distanceTo(targetAnemonePos!!)

            if (distanceToAnemone > 5.0) {
                navigateToAnemone(targetAnemonePos!!)
            }
        }
    }

    private fun navigateToAnemone(ventBlockPos: BlockPos) {
        this.navigation.startMovingTo(ventBlockPos.x.toDouble(), ventBlockPos.y.toDouble(), ventBlockPos.z.toDouble(), this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED))
    }

    private fun findNearbyAnemone(): BlockPos? {
        for (i in -5..5) {
            for (j in -5..5) {
                for (k in -5..5) {
                    val blockPos = BlockPos((x + i).toInt(), (y + j).toInt(), (z + k).toInt())
                    val blockState = world.getBlockState(blockPos)

                    if (blockState.isOf(HybridAquaticBlocks.ANEMONE)) {
                        return blockPos
                    }
                }
            }
        }
        return null
    }

    private fun distanceTo(pos: BlockPos): Double {
        return this.blockPos.getSquaredDistance(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
    }
}
