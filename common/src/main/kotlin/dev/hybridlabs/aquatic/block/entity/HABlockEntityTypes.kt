package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object HABlockEntityTypes {
    val ANEMONE =
        register("anemone", ::AnemoneBlockEntity, HABlocks.ANEMONE)
    val GIANT_GREEN_ANEMONE =
        register("giant_green_anemone", ::GiantGreenAnemoneBlockEntity, HABlocks.GIANT_GREEN_ANEMONE)
    val STRAWBERRY_ANEMONE =
        register("strawberry_anemone", ::StrawberryAnemoneBlockEntity, HABlocks.STRAWBERRY_ANEMONE)
    val GIANT_CLAM =
        register("giant_clam", ::GiantClamBlockEntity, HABlocks.GIANT_CLAM)
    val OYSTER =
        register("oyster", ::OysterBlockEntity, HABlocks.OYSTER)
    val MESSAGE_IN_A_BOTTLE =
        register("message_in_a_bottle", ::MessageInABottleBlockEntity, HABlocks.MESSAGE_IN_A_BOTTLE)
    val BUOY =
        register("buoy", ::BuoyBlockEntity, HABlocks.BUOY)
    val BELL_BUOY =
        register("bell_buoy", ::BellBuoyBlockEntity, HABlocks.BELL_BUOY)
    val THERMAL_VENT =
        register("thermal_vent", ::ThermalVentBlockEntity, HABlocks.THERMAL_VENT)

    fun <T : BlockEntity?> register(
        id: String,
        factory: BlockEntityType.BlockEntitySupplier<T>, vararg validBlocks: RegistryObject<Block>?,
    ): RegistryObject<BlockEntityType<T?>> {

        return CommonClass.BLOCK_ENTITY_TYPES.register(id) {
            val blocks = validBlocks.map { block -> block?.get() }.toTypedArray()
            val builder = BlockEntityType.Builder.of(factory, *blocks)
            builder.build(null)
        }
    }
}