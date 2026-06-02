package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.BaseWoodBlock
import dev.hybridlabs.aquatic.block.wood.HAWoodTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor

object HAPlatformBlocks {
    val DRIFTWOOD_LOG = HABlocks.register("driftwood_log") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_LOG
            )
        )
    }
    val STRIPPED_DRIFTWOOD_LOG = HABlocks.register("stripped_driftwood_log") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_LOG
            )
        )
    }
    val DRIFTWOOD_WOOD = HABlocks.register("driftwood_wood") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_WOOD
            )
        )
    }
    val STRIPPED_DRIFTWOOD_WOOD = HABlocks.register("stripped_driftwood_wood") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_WOOD
            )
        )
    }

    val DRIFTWOOD_PLANKS = HABlocks.register("driftwood_planks") {
        object : Block(Properties.copy(Blocks.OAK_PLANKS)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_SLAB = HABlocks.register("driftwood_slab") {
        object : SlabBlock(Properties.copy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_STAIRS = HABlocks.register("driftwood_stairs") {
        object : StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.copy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_FENCE = HABlocks.register("driftwood_fence") {
        object : FenceBlock(Properties.copy(Blocks.OAK_FENCE)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }


        }
    }

    val DRIFTWOOD_FENCE_GATE = HABlocks.register(
        "driftwood_fence_gate"
    ) {
        object : FenceGateBlock(Properties.copy(Blocks.OAK_FENCE_GATE), HAWoodTypes.DRIFTWOOD) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }
    val DRIFTWOOD_DOOR = HABlocks.register("driftwood_door") {
        DoorBlock(
            Properties.copy(Blocks.OAK_DOOR),
            BlockSetType.OAK
        )
    }
    val DRIFTWOOD_TRAPDOOR =
        HABlocks.register("driftwood_trapdoor") {
            TrapDoorBlock(
                Properties.copy(Blocks.OAK_TRAPDOOR),
                BlockSetType.OAK
            )
        }
    val DRIFTWOOD_BUTTON = HABlocks.register(
        "driftwood_button"
    ) {
        ButtonBlock(Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 25, true)
    }
    val DRIFTWOOD_PRESSURE_PLATE = HABlocks.register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            Properties.copy(Blocks.OAK_PRESSURE_PLATE),
            BlockSetType.OAK
        )
    }

    val DUNEGRASS = HABlocks.register("dunegrass") { DunegrassBlock(Properties.copy(Blocks.GRASS)) }
    val TALL_DUNEGRASS =
        HABlocks.register("tall_dunegrass") { TallDunegrassBlock(Properties.copy(Blocks.TALL_GRASS)) }
    val CATTAIL = HABlocks.register("cattail") { CattailBlock(Properties.copy(Blocks.TALL_GRASS)) }

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