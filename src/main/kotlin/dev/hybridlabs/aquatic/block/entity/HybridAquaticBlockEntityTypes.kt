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
