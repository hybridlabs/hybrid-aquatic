package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.FrameType
import net.minecraft.advancements.critereon.*
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Blocks.WATER
import java.util.function.Consumer

class AdvancementProvider(output: FabricDataOutput) : FabricAdvancementProvider(output) {
    override fun generateAdvancement(consumer: Consumer<Advancement>?) {
        val rootAdvancement = Advancement.Builder.advancement()
            .display(
                HAItems.TUNA.get(),
                Component.translatable("advancements.hybrid-aquatic.enter_water.title"),
                Component.translatable("advancements.hybrid-aquatic.enter_water.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "enter_water",
                EnterBlockTrigger.TriggerInstance.entersBlock(WATER)
            )
            .build(ResourceLocation("hybrid-aquatic", "root"))
        consumer?.accept(rootAdvancement)

        val fishingNetAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.FISHING_NET.get(),
                Component.translatable("advancements.hybrid-aquatic.fishing_net.title"),
                Component.translatable("advancements.hybrid-aquatic.fishing_net.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "fishing_net",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.FISHING_NET.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "fishing_net"))
        consumer?.accept(fishingNetAdvancement)

        val divingWeightAdvancement = Advancement.Builder.advancement()
            .parent(fishingNetAdvancement)
            .display(
                HAItems.DIVING_WEIGHT.get(),
                Component.translatable("advancements.hybrid-aquatic.diving_weight.title"),
                Component.translatable("advancements.hybrid-aquatic.diving_weight.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "diving_weight",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.DIVING_WEIGHT.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "diving_weight"))
        consumer?.accept(divingWeightAdvancement)

        val fishingHookAdvancement = Advancement.Builder.advancement()
            .parent(fishingNetAdvancement)
            .display(
                HAItems.BARBED_HOOK.get(),
                Component.translatable("advancements.hybrid-aquatic.hook.title"),
                Component.translatable("advancements.hybrid-aquatic.hook.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.LURE_ITEMS).build()
                )

            ).build(ResourceLocation("hybrid-aquatic", "hook"))
        consumer?.accept(fishingHookAdvancement)

        val creeperHookAdvancement = Advancement.Builder.advancement()
            .parent(fishingHookAdvancement)
            .display(
                HAItems.CREEPERMAGNET_HOOK.get(),
                Component.translatable("advancements.hybrid-aquatic.creeper_hook.title"),
                Component.translatable("advancements.hybrid-aquatic.creeper_hook.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_creeper_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CREEPERMAGNET_HOOK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "creeper_hook"))
        consumer?.accept(creeperHookAdvancement)

        //#region Depth Charge Advancement Tree
        val glowstickAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.GLOWSTICK.get(),
                Component.translatable("advancements.hybrid-aquatic.glowstick.title"),
                Component.translatable("advancements.hybrid-aquatic.glowstick.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_glowstick",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSTICK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "glowstick"))
        consumer?.accept(glowstickAdvancement)

        val buoyAdvancement = Advancement.Builder.advancement()
            .parent(glowstickAdvancement)
            .display(
                HAItems.BUOY.get(),
                Component.translatable("advancements.hybrid-aquatic.buoy.title"),
                Component.translatable("advancements.hybrid-aquatic.buoy.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_buoy",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BUOY.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "buoy"))
        consumer?.accept(buoyAdvancement)

        val sulfurAdvancement = Advancement.Builder.advancement()
            .parent(glowstickAdvancement)
            .display(
                HAItems.SULFUR.get(),
                Component.translatable("advancements.hybrid-aquatic.sulfur.title"),
                Component.translatable("advancements.hybrid-aquatic.sulfur.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "sulfur"))
        consumer?.accept(sulfurAdvancement)

        val depthChargeAdvancement = Advancement.Builder.advancement()
            .parent(sulfurAdvancement)
            .display(
                HAItems.DEPTH_CHARGE.get(),
                Component.translatable("advancements.hybrid-aquatic.depth_charge.title"),
                Component.translatable("advancements.hybrid-aquatic.depth_charge.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_depth_charge",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.DEPTH_CHARGE.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "depth_charge"))
        consumer?.accept(depthChargeAdvancement)
        //#endregion

        //#region Seashell Set Advancement Tree
        val seashellAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                Items.NAUTILUS_SHELL,
                Component.translatable("advancements.hybrid-aquatic.nautilus_shell.title"),
                Component.translatable("advancements.hybrid-aquatic.nautilus_shell.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_nautilus_shell",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    Items.NAUTILUS_SHELL
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "nautilus_shell"))
        consumer?.accept(seashellAdvancement)

        val seashellToolsAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                HAItems.SEASHELL_SPEAR.get(),
                Component.translatable("advancements.hybrid-aquatic.seashell_tools.title"),
                Component.translatable("advancements.hybrid-aquatic.seashell_tools.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_seashell_tools",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.SEASHELL_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "seashell_tools"))
        consumer?.accept(seashellToolsAdvancement)

        val obtainConduitAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                Items.CONDUIT,
                Component.translatable("advancements.hybrid-aquatic.conduit.title"),
                Component.translatable("advancements.hybrid-aquatic.conduit.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_conduit",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.CONDUIT)
            )
            .build(ResourceLocation("hybrid-aquatic", "conduit"))
        consumer?.accept(obtainConduitAdvancement)
        //#endregion

        //#region Coral Set Advancement Tree
        val coralChunkAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.CORAL_CHUNK.get(),
                Component.translatable("advancements.hybrid-aquatic.coral_chunk.title"),
                Component.translatable("advancements.hybrid-aquatic.coral_chunk.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.CORAL_CHUNK.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "coral_chunk"))
        consumer?.accept(coralChunkAdvancement)

        val coralToolsAdvancement = Advancement.Builder.advancement()
            .parent(coralChunkAdvancement)
            .display(
                HAItems.CORAL_BLADE.get(),
                Component.translatable("advancements.hybrid-aquatic.coral_tools.title"),
                Component.translatable("advancements.hybrid-aquatic.coral_tools.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_coral_tools",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.CORAL_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "coral_tools"))
        consumer?.accept(coralToolsAdvancement)
        //#endregion

        //#region Turtle Set Advancement Tree
        val turtleScuteAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                Items.SCUTE,
                Component.translatable("advancements.hybrid-aquatic.turtle_scute.title"),
                Component.translatable("advancements.hybrid-aquatic.turtle_scute.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_turtle_scute",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    Items.SCUTE
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "turtle_scute"))
        consumer?.accept(turtleScuteAdvancement)

        val turtleSetAdvancement = Advancement.Builder.advancement()
            .parent(turtleScuteAdvancement)
            .display(
                HAItems.TURTLE_CHESTPLATE.get(),
                Component.translatable("advancements.hybrid-aquatic.turtle_set.title"),
                Component.translatable("advancements.hybrid-aquatic.turtle_set.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_turtle_set",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.TURTLE_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "turtle_set"))
        consumer?.accept(turtleSetAdvancement)
        //#endregion

        //#region Diving Set Advancement Tree
        val divingSuitAdvancement = Advancement.Builder.advancement()
            .parent(fishingNetAdvancement)
            .display(
                HAItems.DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid-aquatic.diving_suit.title"),
                Component.translatable("advancements.hybrid-aquatic.diving_suit.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_diving_suit",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_HELMET.get(),
                    HAItems.DIVING_SUIT.get(),
                    HAItems.DIVING_LEGGINGS.get(),
                    HAItems.DIVING_BOOTS.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "diving_suit"))
        consumer?.accept(divingSuitAdvancement)

        val divingUpgradeAdvancement = Advancement.Builder.advancement()
            .parent(divingSuitAdvancement)
            .display(
                HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
                Component.translatable("advancements.hybrid-aquatic.diving_upgrade.title"),
                Component.translatable("advancements.hybrid-aquatic.diving_upgrade.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_diving_suit_upgrade_template",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "diving_upgrade"))
        consumer?.accept(divingUpgradeAdvancement)

        val reinforcedDivingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingUpgradeAdvancement)
            .display(
                HAItems.REINFORCED_DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid-aquatic.reinforced_diving_suit.title"),
                Component.translatable("advancements.hybrid-aquatic.reinforced_diving_suit.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_reinforced_diving_suit",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.REINFORCED_DIVING_HELMET.get(),
                    HAItems.REINFORCED_DIVING_SUIT.get(),
                    HAItems.REINFORCED_DIVING_LEGGINGS.get(),
                    HAItems.REINFORCED_DIVING_BOOTS.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "reinforced_diving_suit"))
        consumer?.accept(reinforcedDivingSuitAdvancement)

        val glowingDivingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingUpgradeAdvancement)
            .display(
                HAItems.GLOWING_DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid-aquatic.glowing_diving_suit.title"),
                Component.translatable("advancements.hybrid-aquatic.glowing_diving_suit.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_glowing_diving_suit",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.GLOWING_DIVING_HELMET.get(),
                    HAItems.GLOWING_DIVING_SUIT.get(),
                    HAItems.GLOWING_DIVING_LEGGINGS.get(),
                    HAItems.GLOWING_DIVING_BOOTS.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "glowing_diving_suit"))
        consumer?.accept(glowingDivingSuitAdvancement)
        //#endregion

        val getClamAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.CLAM.get(),
                Component.translatable("advancements.hybrid-aquatic.get_clam.title"),
                Component.translatable("advancements.hybrid-aquatic.get_clam.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_clam",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.CLAM.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "get_clam"))
        consumer?.accept(getClamAdvancement)

        val plantClamAdvancement = Advancement.Builder.advancement()
            .parent(getClamAdvancement)
            .display(
                HAItems.COOKED_CLAM.get(),
                Component.translatable("advancements.hybrid-aquatic.plant_clam.title"),
                Component.translatable("advancements.hybrid-aquatic.plant_clam.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "plant_clam",
                ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                    LocationPredicate.Builder.location()
                        .setBlock(
                            BlockPredicate.Builder
                                .block()
                                .of(
                                    Blocks.SAND,
                                    HABlocks.GRASSY_SAND.get()
                                )
                                .build()
                        ),
                    ItemPredicate.Builder.item()
                        .of(HAItems.CLAM.get())
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "plant_clam"))
        consumer?.accept(plantClamAdvancement)

        //#region Pearl Advancement Tree
        val obtainPearlAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.PEARL.get(),
                Component.translatable("advancements.hybrid-aquatic.pearl.title"),
                Component.translatable("advancements.hybrid-aquatic.pearl.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "pearl"))
        consumer?.accept(obtainPearlAdvancement)

        val obtainBlackPearlAdvancement = Advancement.Builder.advancement()
            .parent(obtainPearlAdvancement)
            .display(
                HAItems.BLACK_PEARL.get(),
                Component.translatable("advancements.hybrid-aquatic.black_pearl.title"),
                Component.translatable("advancements.hybrid-aquatic.black_pearl.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_black_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "black_pearl"))
        consumer?.accept(obtainBlackPearlAdvancement)
        //#endregion

        //#region Karkinos Advancement Tree
        val crabClawAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.DUNGENESS_CRAB_CLAW.get(),
                Component.translatable("advancements.hybrid-aquatic.crab_claw.title"),
                Component.translatable("advancements.hybrid-aquatic.crab_claw.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "has_crab_claw",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.CRAB_CLAW).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "crab_claw"))
        consumer?.accept(crabClawAdvancement)

        val ominousHookAdvancement = Advancement.Builder.advancement()
            .parent(crabClawAdvancement)
            .display(
                HAItems.OMINOUS_HOOK.get(),
                Component.translatable("advancements.hybrid-aquatic.ominous_hook.title"),
                Component.translatable("advancements.hybrid-aquatic.ominous_hook.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_ominous_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.OMINOUS_HOOK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "ominous_hook"))
        consumer?.accept(ominousHookAdvancement)

        val killKarkinosAdvancement = Advancement.Builder.advancement()
            .parent(ominousHookAdvancement)
            .display(
                HAItems.KARKINOS_CLAW.get(),
                Component.translatable("advancements.hybrid-aquatic.kill_karkinos.title"),
                Component.translatable("advancements.hybrid-aquatic.kill_karkinos.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "kill_karkinos",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.KARKINOS.get()).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "kill_karkinos"))
        consumer?.accept(killKarkinosAdvancement)
        //#endregion

        //#region Argonaut Advancement Tree
        val ominousConchAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                HAItems.OMINOUS_CONCH.get(),
                Component.translatable("advancements.hybrid-aquatic.ominous_conch.title"),
                Component.translatable("advancements.hybrid-aquatic.ominous_conch.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_ominous_conch",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.OMINOUS_CONCH.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "ominous_conch"))
        consumer?.accept(ominousConchAdvancement)

        val killShellBeastAdvancement = Advancement.Builder.advancement()
            .parent(ominousConchAdvancement)
            .display(
                HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get(),
                Component.translatable("advancements.hybrid-aquatic.shell_beast.title"),
                Component.translatable("advancements.hybrid-aquatic.shell_beast.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "kill_shell_beast",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.SHELL_BEAST.get()).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "shell_beast"))
        consumer?.accept(killShellBeastAdvancement)

        val argonautAdvancement = Advancement.Builder.advancement()
            .parent(killShellBeastAdvancement)
            .display(
                HAItems.ARGONAUT.get(),
                Component.translatable("advancements.hybrid-aquatic.argonaut.title"),
                Component.translatable("advancements.hybrid-aquatic.argonaut.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_argonaut",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.ARGONAUT.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "argonaut"))
        consumer?.accept(argonautAdvancement)

        //#endregion

        //#region Trident Advancement Tree
        val obtainSharkToothAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.SHARK_TOOTH.get(),
                Component.translatable("advancements.hybrid-aquatic.bigger_boat.title"),
                Component.translatable("advancements.hybrid-aquatic.bigger_boat.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_shark_tooth",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SHARK_TOOTH.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "bigger_boat"))
        consumer?.accept(obtainSharkToothAdvancement)

        val obtainTridentAdvancement = Advancement.Builder.advancement()
            .parent(obtainSharkToothAdvancement)
            .display(
                Items.TRIDENT,
                Component.translatable("advancements.hybrid-aquatic.trident.title"),
                Component.translatable("advancements.hybrid-aquatic.trident.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_trident",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.TRIDENT)
            )
            .build(ResourceLocation("hybrid-aquatic", "trident"))
        consumer?.accept(obtainTridentAdvancement)
        //#endregion
    }
}
