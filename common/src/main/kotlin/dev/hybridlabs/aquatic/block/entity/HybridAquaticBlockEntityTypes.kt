package dev.hybridlabs.aquatic.block.entity

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.Util
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.datafix.fixes.References
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

object HybridAquaticBlockEntityTypes {
    val ANEMONE = register("anemone", ::AnemoneBlockEntity, HybridAquaticBlocks.ANEMONE.get())
    val GIANT_GREEN_ANEMONE =
        register("giant_green_anemone", ::GiantGreenAnemoneBlockEntity, HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get())
    val STRAWBERRY_ANEMONE =
        register("strawberry_anemone", ::StrawberryAnemoneBlockEntity, HybridAquaticBlocks.STRAWBERRY_ANEMONE.get())
    val MESSAGE_IN_A_BOTTLE =
        register("message_in_a_bottle", ::MessageInABottleBlockEntity, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get())
    val BUOY = register("buoy", ::BuoyBlockEntity, HybridAquaticBlocks.BUOY.get())

    private fun <T : BlockEntity> register(
        id: String,
        supplier: BlockEntityType.BlockEntitySupplier<T>,
        vararg validBlocks: Block
    ): RegistryObject<BlockEntityType<T>> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        val type = Util.fetchChoiceType(
            References.BLOCK_ENTITY,
            identifier.toString()
        )!!
        val blockEntityType = BlockEntityType(supplier, ImmutableSet.copyOf(validBlocks), type)
        return CommonClass.BLOCK_ENTITY_TYPES.register<BlockEntityType<T>>(id) { blockEntityType }
    }
}
