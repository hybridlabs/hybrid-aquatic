package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.HydrothermalVentBlock
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
    val MESSAGE_IN_A_BOTTLE: BlockEntityType<MessageInABottleBlockEntity> = register("message_in_a_bottle", FabricBlockEntityTypeBuilder.create(::MessageInABottleBlockEntity, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE))
    val FISHING_PLAQUE: BlockEntityType<FishingPlaqueBlockEntity> = register("fishing_plaque", FabricBlockEntityTypeBuilder.create(::FishingPlaqueBlockEntity, HybridAquaticBlocks.FISHING_PLAQUE))
    val BUOY: BlockEntityType<BuoyBlockEntity> = register("buoy", FabricBlockEntityTypeBuilder.create(::BuoyBlockEntity, HybridAquaticBlocks.BUOY))
    val CRAB_POT: BlockEntityType<CrabPotBlockEntity> = register("crab_pot", FabricBlockEntityTypeBuilder.create(::CrabPotBlockEntity, HybridAquaticBlocks.CRAB_POT))
    val HYDROTHERMAL_VENT: BlockEntityType<HydrothermalVentBlockEntity> = register("hydrothermal_vent", FabricBlockEntityTypeBuilder.create(::HydrothermalVentBlockEntity, HybridAquaticBlocks.HYDROTHERMAL_VENT))

    private fun <T : BlockEntity> register(id: String, builder: FabricBlockEntityTypeBuilder<T>): BlockEntityType<T> {
        val identifier = Identifier(HybridAquatic.MOD_ID, id)
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, builder.build(
            Util.getChoiceType(
                TypeReferences.BLOCK_ENTITY,
                identifier.toString()
            )
        ))
    }
}
