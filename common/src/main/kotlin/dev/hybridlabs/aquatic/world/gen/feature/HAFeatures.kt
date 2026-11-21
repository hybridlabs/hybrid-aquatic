package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import dev.hybridlabs.aquatic.world.gen.feature.algae.RedAlgaePatchFeature
import dev.hybridlabs.aquatic.world.gen.feature.algae.SeaLettuceFeature
import dev.hybridlabs.aquatic.world.gen.feature.corals.*
import dev.hybridlabs.aquatic.world.gen.feature.kelp.*
import net.minecraft.world.level.levelgen.feature.Feature

/**
 * A registry of world generation feature types for Hybrid Aquatic.
 */
object HAFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature.CODEC)
    val BRINE_LAKE = register("brine_lake", BrineLakeFeature.CODEC)
    val ANEMONES = register("anemones", AnemoneFeature.CODEC)
    val BULL_KELP = register("bull_kelp", BullKelpFeature.CODEC)
    val DELESSERIA = register("delesseria", DelesseriaFeature.CODEC)
    val VENT_PATCH = register("vent_patch", VentPatchFeature.CODEC)
    val SARGASSUM = register("sargassum", SargassumFeature.CODEC)
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch", SeaLettuceFeature.CODEC)
    val RED_ALGAE_PATCH = register("red_algae_patch", RedAlgaePatchFeature.CODEC)
    val SUSPICIOUS_SAND_DISK = register("suspicious_sand_disk", SuspiciousSandDiskFeature.CODEC)
    val RANDOM_PATCH = register("random_patch", HARandomPatchFeature.CODEC)

    val DEEP_CORAL_CLAW = register("deep_coral_claw", DeepCoralClawFeature.CODEC)
    val DEEP_CORAL_TREE = register("deep_coral_tree", DeepCoralTreeFeature.CODEC)
    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom", DeepCoralMushroomFeature.CODEC)
    val DEEP_CORAL_TABLE = register("deep_coral_table", DeepCoralTableFeature.CODEC)

    val BLEACHED_CORAL_CLAW = register("bleached_coral_claw", BleachedCoralClawFeature.CODEC)
    val BLEACHED_CORAL_TREE = register("bleached_coral_tree", BleachedCoralTreeFeature.CODEC)
    val BLEACHED_CORAL_MUSHROOM = register("bleached_coral_mushroom", BleachedCoralMushroomFeature.CODEC)
    val BLEACHED_CORAL_TABLE = register("bleached_coral_table", BleachedCoralTableFeature.CODEC)

    val REEF_CORAL_CLAW = register("reef_coral_claw", ReefCoralClawFeature.CODEC)
    val REEF_CORAL_TREE = register("reef_coral_tree", ReefCoralTreeFeature.CODEC)
    val REEF_CORAL_MUSHROOM = register("reef_coral_mushroom", ReefCoralMushroomFeature.CODEC)
    val REEF_CORAL_TABLE = register("reef_coral_table", ReefCoralTableFeature.CODEC)

    fun register(
        id: String,
        codec: MapCodec<out Feature>,
    ): RegistryObject<MapCodec<out Feature>> {
        return CommonClass.FEATURE_TYPE.register(id) { codec }
    }
}
