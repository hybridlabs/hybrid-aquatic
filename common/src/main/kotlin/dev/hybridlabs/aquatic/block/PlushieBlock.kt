package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.addBlocks
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition

/**
 * Represents any Plushie block.
 */
class PlushieBlock(variant: Variant, val particleBlock: Block, settings: Properties) : SkullBlock(variant, settings) {
    init {
        BlockEntityType.SKULL.addBlocks(this)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
    }

    override fun appendHoverText(
        stack: ItemStack,
        world: BlockGetter?,
        tooltip: MutableList<Component>,
        options: TooltipFlag
    ) {
        val text = Component.translatable(this.descriptionId.plus(".description")).withStyle(ChatFormatting.GRAY)

        tooltip.add(text)
        super.appendHoverText(stack, world, tooltip, options)
    }

    enum class Variant(val id: String) : Type, StringRepresentable {
        BASKING_SHARK("basking_shark"),
        BULL_SHARK("bull_shark"),
        FRILLED_SHARK("frilled_shark"),
        GREAT_WHITE_SHARK("great_white_shark"),
        HAMMERHEAD_SHARK("hammerhead_shark"),
        THRESHER_SHARK("thresher_shark"),
        TIGER_SHARK("tiger_shark"),
        WHALE_SHARK("whale_shark");

        val textureLocation: ResourceLocation by lazy { CommonClass.locate("textures/entity/block/plushie/${id}_plushie.png") }

        override fun getSerializedName(): String = id
    }
}