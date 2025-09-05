package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.Util
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.datafix.fixes.References
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

object HybridAquaticBlockEntityTypes {
    val ANEMONE: RegistryObject<BlockEntityType<AnemoneBlockEntity>> = register(
        "anemone",
        FabricBlockEntityTypeBuilder.create(::AnemoneBlockEntity, HybridAquaticBlocks.ANEMONE.get())
    )
    val STRAWBERRY_ANEMONE: RegistryObject<BlockEntityType<StrawberryAnemoneBlockEntity>> = register(
        "strawberry_anemone",
        FabricBlockEntityTypeBuilder.create(
            ::StrawberryAnemoneBlockEntity,
            HybridAquaticBlocks.STRAWBERRY_ANEMONE.get()
        )
    )
    val MESSAGE_IN_A_BOTTLE: RegistryObject<BlockEntityType<MessageInABottleBlockEntity>> = register(
        "message_in_a_bottle",
        FabricBlockEntityTypeBuilder.create(
            ::MessageInABottleBlockEntity,
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get()
        )
    )
    val BUOY: RegistryObject<BlockEntityType<BuoyBlockEntity>> =
        register("buoy", FabricBlockEntityTypeBuilder.create(::BuoyBlockEntity, HybridAquaticBlocks.BUOY.get()))

    private fun <T : BlockEntity> register(
        id: String,
        builder: FabricBlockEntityTypeBuilder<T>
    ): RegistryObject<BlockEntityType<T>> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        return CommonClass.BLOCK_ENTITY_TYPES.register(id) {
            builder.build(
                Util.fetchChoiceType(
                    References.BLOCK_ENTITY,
                    identifier.toString()
                )
            )
        }
    }
}
