package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object HybridAquaticBlockEntityTypes {
    val ANEMONE =
        register("anemone", ::AnemoneBlockEntity, HybridAquaticBlocks.ANEMONE)
    val GIANT_GREEN_ANEMONE =
        register("giant_green_anemone", ::GiantGreenAnemoneBlockEntity, HybridAquaticBlocks.GIANT_GREEN_ANEMONE)
    val STRAWBERRY_ANEMONE =
        register("strawberry_anemone", ::StrawberryAnemoneBlockEntity, HybridAquaticBlocks.STRAWBERRY_ANEMONE)
    val GIANT_CLAM =
        register("giant_clam", ::GiantClamBlockEntity, HybridAquaticBlocks.GIANT_CLAM)
    val OYSTER =
        register("oyster", ::OysterBlockEntity, HybridAquaticBlocks.OYSTER)

    val MESSAGE_IN_A_BOTTLE =
        register("message_in_a_bottle", ::MessageInABottleBlockEntity, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
    val BUOY =
        register("buoy", ::BuoyBlockEntity, HybridAquaticBlocks.BUOY)


    fun <T : BlockEntity?> register(
        id: String,
        factory: BlockEntityType.BlockEntitySupplier<T>, vararg validBlocks: RegistryObject<Block>?
    ): RegistryObject<BlockEntityType<T?>> {

        return CommonClass.BLOCK_ENTITY_TYPES.register(id) {
            val blocks = validBlocks.map { block -> block?.get() }.toTypedArray()
            val builder = BlockEntityType.Builder.of(factory, *blocks)
            builder.build(null)
        }
    }
}