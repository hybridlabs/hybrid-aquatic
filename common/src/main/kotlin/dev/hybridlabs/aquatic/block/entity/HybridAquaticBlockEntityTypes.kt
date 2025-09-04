package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Util

object HybridAquaticBlockEntityTypes {
    val ANEMONE: BlockEntityType<AnemoneBlockEntity> = register("anemone", FabricBlockEntityTypeBuilder.create(::AnemoneBlockEntity, HybridAquaticBlocks.ANEMONE))
    val GIANT_GREEN_ANEMONE: BlockEntityType<GiantGreenAnemoneBlockEntity> = register("giant_green_anemone", FabricBlockEntityTypeBuilder.create(::GiantGreenAnemoneBlockEntity, HybridAquaticBlocks.GIANT_GREEN_ANEMONE))
    val STRAWBERRY_ANEMONE: BlockEntityType<StrawberryAnemoneBlockEntity> = register("strawberry_anemone", FabricBlockEntityTypeBuilder.create(::StrawberryAnemoneBlockEntity, HybridAquaticBlocks.STRAWBERRY_ANEMONE))
    val MESSAGE_IN_A_BOTTLE: BlockEntityType<MessageInABottleBlockEntity> = register("message_in_a_bottle", FabricBlockEntityTypeBuilder.create(::MessageInABottleBlockEntity, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE))
    val BUOY: BlockEntityType<BuoyBlockEntity> = register("buoy", FabricBlockEntityTypeBuilder.create(::BuoyBlockEntity, HybridAquaticBlocks.BUOY))

    private fun <T : BlockEntity> register(id: String, builder: FabricBlockEntityTypeBuilder<T>): BlockEntityType<T> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, builder.build(
            Util.getChoiceType(
                TypeReferences.BLOCK_ENTITY,
                identifier.toString()
            )
        ))
    }
}
