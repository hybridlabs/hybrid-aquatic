package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.BaseWoodBlock
import dev.hybridlabs.aquatic.block.wood.HAWoodTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor

object HAPlatformBlocks {
    val DRIFTWOOD_LOG = HABlocks.register("driftwood_log") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_LOG
            )
        )
    }
    val STRIPPED_DRIFTWOOD_LOG = HABlocks.register("stripped_driftwood_log") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_LOG
            )
        )
    }
    val DRIFTWOOD_WOOD = HABlocks.register("driftwood_wood") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_WOOD
            )
        )
    }
    val STRIPPED_DRIFTWOOD_WOOD = HABlocks.register("stripped_driftwood_wood") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_WOOD
            )
        )
    }

    val DRIFTWOOD_PLANKS = HABlocks.register("driftwood_planks") {
        object : Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_SLAB = HABlocks.register("driftwood_slab") {
        object : SlabBlock(Properties.ofFullCopy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_STAIRS = HABlocks.register("driftwood_stairs") {
        object : StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.ofFullCopy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_FENCE = HABlocks.register("driftwood_fence") {
        object : FenceBlock(Properties.ofFullCopy(Blocks.OAK_FENCE)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }


        }
    }

    val DRIFTWOOD_FENCE_GATE = HABlocks.register(
        "driftwood_fence_gate"
    ) {
        object : FenceGateBlock(
            HAWoodTypes.DRIFTWOOD, Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
        ) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }
    val DRIFTWOOD_DOOR =
        HABlocks.register("driftwood_door") { DoorBlock(BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR)) }
    val DRIFTWOOD_TRAPDOOR =
        HABlocks.register("driftwood_trapdoor") {
            TrapDoorBlock(
                BlockSetType.OAK,
                Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            )
        }

    val DRIFTWOOD_BUTTON = HABlocks.register(
        "driftwood_button"
    )
    {
        ButtonBlock(
            BlockSetType.OAK, 25, Properties.ofFullCopy(Blocks.OAK_BUTTON)
        )

    }

    val DRIFTWOOD_PRESSURE_PLATE = HABlocks.register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
        )
    }

    val DUNEGRASS = HABlocks.register("dunegrass") { DunegrassBlock(Properties.ofFullCopy(Blocks.SHORT_GRASS)) }
    val TALL_DUNEGRASS =
        HABlocks.register("tall_dunegrass") { TallDunegrassBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }
    val CATTAIL = HABlocks.register("cattail") { CattailBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }

    val HAGSLIME_BLOCK = HABlocks.register("hagslime_block") {
        ForgeHagslimeBlock(
            Properties.of()
                .instabreak()
                .noOcclusion()
                .sound(SoundType.HONEY_BLOCK)
                .mapColor { MapColor.TERRACOTTA_WHITE }
                .isSuffocating { _, _, _ -> false }
                .speedFactor(0.4F)
                .jumpFactor(0.5F)
        )
    }

    val GLOWSLIME_BLOCK = HABlocks.register("glowslime_block") {
        ForgeGlowslimeBlock(
            Properties.of()
                .friction(0.8F)
                .instabreak()
                .noOcclusion()
                .sound(SoundType.SLIME_BLOCK)
                .mapColor { MapColor.COLOR_CYAN }
                .lightLevel { 14 }
        )
    }
}