package dev.hybridlabs.aquatic.block

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.enums.Instrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridAquaticBlocks {
    val ANEMONE = register("anemone", AnemoneBlock(
        FabricBlockSettings.create()
            .mapColor(MapColor.DARK_DULL_PINK)
            .ticksRandomly()
            .strength(0.4f)
            .nonOpaque()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.SLIME).drops(Identifier(HybridAquatic.MOD_ID, "blocks/anemone"))
    ))

    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleBlock(
        FabricBlockSettings.copyOf(Blocks.GLASS)
            .breakInstantly()
            .pistonBehavior(PistonBehavior.DESTROY)
    ))

    val BASKING_SHARK_PLUSHIE = register("basking_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.BASKING_SHARK, Blocks.GRAY_WOOL))
    val BULL_SHARK_PLUSHIE = register("bull_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.BULL_SHARK, Blocks.LIGHT_GRAY_WOOL))
    val FRILLED_SHARK_PLUSHIE = register("frilled_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.FRILLED_SHARK, Blocks.GRAY_WOOL))
    val GREAT_WHITE_SHARK_PLUSHIE = register("great_white_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.GREAT_WHITE_SHARK, Blocks.WHITE_WOOL))
    val HAMMERHEAD_SHARK_PLUSHIE = register("hammerhead_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.HAMMERHEAD_SHARK, Blocks.WHITE_WOOL))
    val THRESHER_SHARK_PLUSHIE = register("thresher_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.THRESHER_SHARK, Blocks.WHITE_WOOL))
    val TIGER_SHARK_PLUSHIE = register("tiger_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.TIGER_SHARK, Blocks.BLACK_WOOL))
    val WHALE_SHARK_PLUSHIE = register("whale_shark_blahaj_plushie", createPlushieBlock(PlushieBlock.Variant.WHALE_SHARK, Blocks.WHITE_WOOL))

    val CRATE = register("crate", CrateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)))

    private fun createPlushieBlock(variant: PlushieBlock.Variant, particleBlock: Block): PlushieBlock {
        return PlushieBlock(variant, particleBlock,
            FabricBlockSettings.create()
                .breakInstantly()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(BlockSoundGroup.WOOL)
                .instrument(Instrument.CUSTOM_HEAD)
        )
    }


    val FISHING_PLAQUE = register("fishing_plaque", FishingPlaque(FabricBlockSettings.create()))


//    val FISH_TANK = register("fish_tank", FishTankBlock(
//        FabricBlockSettings.create()
//            .ticksRandomly()
//            .strength(1.0f)
//            .nonOpaque()
//            .requiresTool()
//            .sounds(BlockSoundGroup.GLASS)
//    ))

    private fun register(id: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, Identifier(HybridAquatic.MOD_ID, id), block)
    }

    /**
     * Adds [addedBlocks] to the supported blocks list of a block entity type.
     */
    fun <T : BlockEntity> BlockEntityType<T>.addBlocks(vararg addedBlocks: Block) {
        if (blocks !is ImmutableSet) {
            blocks.addAll(addedBlocks)
        } else {
            blocks = blocks.toMutableSet().apply {
                addAll(addedBlocks)
            }
        }
    }
}
