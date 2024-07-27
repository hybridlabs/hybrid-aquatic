package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.Util

object HybridAquaticBlockEntityTypes {
    val ANEMONE: BlockEntityType<AnemoneBlockEntity> = register("anemone", FabricBlockEntityTypeBuilder.create(::AnemoneBlockEntity, HybridAquaticBlocks.ANEMONE))
    val GIANT_CLAM: BlockEntityType<GiantClamBlockEntity> = register("giant_clam", FabricBlockEntityTypeBuilder.create(::GiantClamBlockEntity, HybridAquaticBlocks.GIANT_CLAM))
    val MESSAGE_IN_A_BOTTLE: BlockEntityType<MessageInABottleBlockEntity> = register("message_in_a_bottle", FabricBlockEntityTypeBuilder.create(::MessageInABottleBlockEntity, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE))
    val BUOY: BlockEntityType<BuoyBlockEntity> = register("buoy", FabricBlockEntityTypeBuilder.create(::BuoyBlockEntity, HybridAquaticBlocks.BUOY))
    val TUBE_SPONGE: BlockEntityType<TubeSpongeBlockEntity> = register("tube_sponge", FabricBlockEntityTypeBuilder.create(::TubeSpongeBlockEntity, HybridAquaticBlocks.TUBE_SPONGE))

    private fun <T : BlockEntity> register(id: String, builder: FabricBlockEntityTypeBuilder<T>): BlockEntityType<T> {
        val identifier = Identifier.of(HybridAquatic.MOD_ID, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, builder.build(
            Util.getChoiceType(
                TypeReferences.BLOCK_ENTITY,
                identifier.toString()
            )
        ))
    }
}
