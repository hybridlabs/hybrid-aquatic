package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.item.HAPIItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.critereon.*
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Blocks.WATER
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementProvider(output: FabricDataOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) : FabricAdvancementProvider(output,registryLookup) {
    override fun generateAdvancement(registryLookup: HolderLookup.Provider, consumer: Consumer<AdvancementHolder>) {
        val rootAdvancement = Advancement.Builder.advancement()
            .display(
                HAItems.TUNA.get(),
                Component.translatable("advancements.hybrid_aquatic.enter_water.title"),
                Component.translatable("advancements.hybrid_aquatic.enter_water.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "enter_water",
                EnterBlockTrigger.TriggerInstance.entersBlock(WATER)
            )
            .build(CommonClass.locate("root"))
        consumer.accept(rootAdvancement)

        val divingWeightAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.DIVING_WEIGHT.get(),
                Component.translatable("advancements.hybrid_aquatic.diving_weight.title"),
                Component.translatable("advancements.hybrid_aquatic.diving_weight.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "diving_weight",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.DIVING_WEIGHT.get())
            )
            .build(CommonClass.locate("diving_weight"))
        consumer.accept(divingWeightAdvancement)

        val fishingHookAdvancement = Advancement.Builder.advancement()
            .parent(divingWeightAdvancement)
            .display(
                HAItems.BARBED_HOOK.get(),
                Component.translatable("advancements.hybrid_aquatic.hook.title"),
                Component.translatable("advancements.hybrid_aquatic.hook.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.LURE_ITEMS).build()
                )

            ).build(CommonClass.locate("hook"))
        consumer.accept(fishingHookAdvancement)

        val creeperHookAdvancement = Advancement.Builder.advancement()
            .parent(fishingHookAdvancement)
            .display(
                HAItems.CREEPERMAGNET_HOOK.get(),
                Component.translatable("advancements.hybrid_aquatic.creeper_hook.title"),
                Component.translatable("advancements.hybrid_aquatic.creeper_hook.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_creeper_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CREEPERMAGNET_HOOK.get())
            )
            .build(CommonClass.locate("creeper_hook"))
        consumer.accept(creeperHookAdvancement)

        //#region Depth Charge Advancement Tree
        val glowstickAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.GLOWSTICK.get(),
                Component.translatable("advancements.hybrid_aquatic.glowstick.title"),
                Component.translatable("advancements.hybrid_aquatic.glowstick.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_glowstick",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSTICK.get())
            )
            .build(CommonClass.locate("glowstick"))
        consumer.accept(glowstickAdvancement)

        val buoyAdvancement = Advancement.Builder.advancement()
            .parent(glowstickAdvancement)
            .display(
                HAItems.BUOY.get(),
                Component.translatable("advancements.hybrid_aquatic.buoy.title"),
                Component.translatable("advancements.hybrid_aquatic.buoy.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_buoy",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BUOY.get())
            )
            .build(CommonClass.locate("buoy"))
        consumer.accept(buoyAdvancement)

        val sulfurAdvancement = Advancement.Builder.advancement()
            .parent(glowstickAdvancement)
            .display(
                HAItems.SULFUR.get(),
                Component.translatable("advancements.hybrid_aquatic.sulfur.title"),
                Component.translatable("advancements.hybrid_aquatic.sulfur.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .build(CommonClass.locate("sulfur"))
        consumer.accept(sulfurAdvancement)

        val depthChargeAdvancement = Advancement.Builder.advancement()
            .parent(sulfurAdvancement)
            .display(
                HAItems.DEPTH_CHARGE.get(),
                Component.translatable("advancements.hybrid_aquatic.depth_charge.title"),
                Component.translatable("advancements.hybrid_aquatic.depth_charge.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_depth_charge",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.DEPTH_CHARGE.get())
            )
            .build(CommonClass.locate("depth_charge"))
        consumer.accept(depthChargeAdvancement)
        //#endregion

        //#region Seashell Set Advancement Tree
        val seashellAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                Items.NAUTILUS_SHELL,
                Component.translatable("advancements.hybrid_aquatic.nautilus_shell.title"),
                Component.translatable("advancements.hybrid_aquatic.nautilus_shell.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
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
            .build(CommonClass.locate("nautilus_shell"))
        consumer.accept(seashellAdvancement)

        val seashellToolsAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                HAItems.SEASHELL_SPEAR.get(),
                Component.translatable("advancements.hybrid_aquatic.seashell_tools.title"),
                Component.translatable("advancements.hybrid_aquatic.seashell_tools.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("seashell_tools"))
        consumer.accept(seashellToolsAdvancement)

        val obtainConduitAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                Items.CONDUIT,
                Component.translatable("advancements.hybrid_aquatic.conduit.title"),
                Component.translatable("advancements.hybrid_aquatic.conduit.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_conduit",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.CONDUIT)
            )
            .build(CommonClass.locate("conduit"))
        consumer.accept(obtainConduitAdvancement)
        //#endregion

        //#region Coral Set Advancement Tree
        val coralChunkAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.CORAL_CHUNK.get(),
                Component.translatable("advancements.hybrid_aquatic.coral_chunk.title"),
                Component.translatable("advancements.hybrid_aquatic.coral_chunk.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
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
            .build(CommonClass.locate("coral_chunk"))
        consumer.accept(coralChunkAdvancement)

        val coralToolsAdvancement = Advancement.Builder.advancement()
            .parent(coralChunkAdvancement)
            .display(
                HAItems.CORAL_BLADE.get(),
                Component.translatable("advancements.hybrid_aquatic.coral_tools.title"),
                Component.translatable("advancements.hybrid_aquatic.coral_tools.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("coral_tools"))
        consumer.accept(coralToolsAdvancement)
        //#endregion

        //#region Turtle Set Advancement Tree
        val turtleScuteAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                Items.TURTLE_SCUTE,
                Component.translatable("advancements.hybrid_aquatic.turtle_scute.title"),
                Component.translatable("advancements.hybrid_aquatic.turtle_scute.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_turtle_scute",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    Items.TURTLE_SCUTE
                )
            )
            .build(CommonClass.locate("turtle_scute"))
        consumer.accept(turtleScuteAdvancement)

        val turtleSetAdvancement = Advancement.Builder.advancement()
            .parent(turtleScuteAdvancement)
            .display(
                HAItems.TURTLE_CHESTPLATE.get(),
                Component.translatable("advancements.hybrid_aquatic.turtle_set.title"),
                Component.translatable("advancements.hybrid_aquatic.turtle_set.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("turtle_set"))
        consumer.accept(turtleSetAdvancement)
        //#endregion

        //#region Diving Set Advancement Tree
        val divingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingWeightAdvancement)
            .display(
                HAItems.DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid_aquatic.diving_suit.title"),
                Component.translatable("advancements.hybrid_aquatic.diving_suit.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("diving_suit"))
        consumer.accept(divingSuitAdvancement)

        val divingUpgradeAdvancement = Advancement.Builder.advancement()
            .parent(divingSuitAdvancement)
            .display(
                HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
                Component.translatable("advancements.hybrid_aquatic.diving_upgrade.title"),
                Component.translatable("advancements.hybrid_aquatic.diving_upgrade.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("diving_upgrade"))
        consumer.accept(divingUpgradeAdvancement)

        val reinforcedDivingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingUpgradeAdvancement)
            .display(
                HAItems.REINFORCED_DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid_aquatic.reinforced_diving_suit.title"),
                Component.translatable("advancements.hybrid_aquatic.reinforced_diving_suit.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("reinforced_diving_suit"))
        consumer.accept(reinforcedDivingSuitAdvancement)

        val glowingDivingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingUpgradeAdvancement)
            .display(
                HAItems.GLOWING_DIVING_HELMET.get(),
                Component.translatable("advancements.hybrid_aquatic.glowing_diving_suit.title"),
                Component.translatable("advancements.hybrid_aquatic.glowing_diving_suit.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
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
            .build(CommonClass.locate("glowing_diving_suit"))
        consumer.accept(glowingDivingSuitAdvancement)
        //#endregion

        val getClamAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.CLAM.get(),
                Component.translatable("advancements.hybrid_aquatic.get_clam.title"),
                Component.translatable("advancements.hybrid_aquatic.get_clam.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
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
            .build(CommonClass.locate("get_clam"))
        consumer.accept(getClamAdvancement)

        val plantClamAdvancement = Advancement.Builder.advancement()
            .parent(getClamAdvancement)
            .display(
                HAItems.COOKED_CLAM.get(),
                Component.translatable("advancements.hybrid_aquatic.plant_clam.title"),
                Component.translatable("advancements.hybrid_aquatic.plant_clam.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
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
                        ),
                    ItemPredicate.Builder.item()
                        .of(HAItems.CLAM.get())
                )
            )
            .build(CommonClass.locate("plant_clam"))
        consumer.accept(plantClamAdvancement)

        val killSirenianAdvancement = Advancement.Builder.advancement()
            .parent(getClamAdvancement)
            .display(
                HAItems.SIRENIAN_STEAK.get(),
                Component.translatable("advancements.hybrid_aquatic.kill_sirenian.title"),
                Component.translatable("advancements.hybrid_aquatic.kill_sirenian.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                true
            )
            .addCriterion(
                "kill_manatee",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.MANATEE.get())
                )
            )
            .addCriterion(
                "kill_dugong",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.DUGONG.get())
                )
            )
            .build(CommonClass.locate("kill_sirenian"))
        consumer.accept(killSirenianAdvancement)

        //#region Pearl Advancement Tree
        val obtainPearlAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.PEARL.get(),
                Component.translatable("advancements.hybrid_aquatic.pearl.title"),
                Component.translatable("advancements.hybrid_aquatic.pearl.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .build(CommonClass.locate("pearl"))
        consumer.accept(obtainPearlAdvancement)

        val obtainBlackPearlAdvancement = Advancement.Builder.advancement()
            .parent(obtainPearlAdvancement)
            .display(
                HAItems.BLACK_PEARL.get(),
                Component.translatable("advancements.hybrid_aquatic.black_pearl.title"),
                Component.translatable("advancements.hybrid_aquatic.black_pearl.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_black_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL.get())
            )
            .build(CommonClass.locate("black_pearl"))
        consumer.accept(obtainBlackPearlAdvancement)
        //#endregion

        //#region Karkinos Advancement Tree
        val crabClawAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.DUNGENESS_CRAB_CLAW.get(),
                Component.translatable("advancements.hybrid_aquatic.crab_claw.title"),
                Component.translatable("advancements.hybrid_aquatic.crab_claw.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.TASK,
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
            .build(CommonClass.locate("crab_claw"))
        consumer.accept(crabClawAdvancement)

        val ominousHookAdvancement = Advancement.Builder.advancement()
            .parent(crabClawAdvancement)
            .display(
                HAItems.OMINOUS_HOOK.get(),
                Component.translatable("advancements.hybrid_aquatic.ominous_hook.title"),
                Component.translatable("advancements.hybrid_aquatic.ominous_hook.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_ominous_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.OMINOUS_HOOK.get())
            )
            .build(CommonClass.locate("ominous_hook"))
        consumer.accept(ominousHookAdvancement)

        val killKarkinosAdvancement = Advancement.Builder.advancement()
            .parent(ominousHookAdvancement)
            .display(
                HAItems.KARKINOS_CLAW.get(),
                Component.translatable("advancements.hybrid_aquatic.kill_karkinos.title"),
                Component.translatable("advancements.hybrid_aquatic.kill_karkinos.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "kill_karkinos",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.KARKINOS.get())
                )
            )
            .build(CommonClass.locate("kill_karkinos"))
        consumer.accept(killKarkinosAdvancement)
        //#endregion

        //#region Argonaut Advancement Tree
        val ominousConchAdvancement = Advancement.Builder.advancement()
            .parent(seashellAdvancement)
            .display(
                HAItems.OMINOUS_CONCH.get(),
                Component.translatable("advancements.hybrid_aquatic.ominous_conch.title"),
                Component.translatable("advancements.hybrid_aquatic.ominous_conch.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_ominous_conch",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.OMINOUS_CONCH.get())
            )
            .build(CommonClass.locate("ominous_conch"))
        consumer.accept(ominousConchAdvancement)

        val killShellBeastAdvancement = Advancement.Builder.advancement()
            .parent(ominousConchAdvancement)
            .display(
                HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get(),
                Component.translatable("advancements.hybrid_aquatic.shell_beast.title"),
                Component.translatable("advancements.hybrid_aquatic.shell_beast.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "kill_shell_beast",
                KilledTrigger.TriggerInstance.playerKilledEntity(
                    EntityPredicate.Builder.entity().of(HAEntityTypes.SHELL_BEAST.get())
                )
            )
            .build(CommonClass.locate("shell_beast"))
        consumer.accept(killShellBeastAdvancement)

        val argonautAdvancement = Advancement.Builder.advancement()
            .parent(killShellBeastAdvancement)
            .display(
                HAItems.ARGONAUT.get(),
                Component.translatable("advancements.hybrid_aquatic.argonaut.title"),
                Component.translatable("advancements.hybrid_aquatic.argonaut.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_argonaut",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.ARGONAUT.get())
            )
            .build(CommonClass.locate("argonaut"))
        consumer.accept(argonautAdvancement)

        //#endregion

        //#region Trident Advancement Tree
        val obtainSharkToothAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HAItems.SHARK_TOOTH.get(),
                Component.translatable("advancements.hybrid_aquatic.bigger_boat.title"),
                Component.translatable("advancements.hybrid_aquatic.bigger_boat.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_shark_tooth",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SHARK_TOOTH.get())
            )
            .build(CommonClass.locate("bigger_boat"))
        consumer.accept(obtainSharkToothAdvancement)

        val obtainTridentAdvancement = Advancement.Builder.advancement()
            .parent(obtainSharkToothAdvancement)
            .display(
                Items.TRIDENT,
                Component.translatable("advancements.hybrid_aquatic.trident.title"),
                Component.translatable("advancements.hybrid_aquatic.trident.description"),
                ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/block/coralstone.png"),
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion(
                "obtain_trident",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.TRIDENT)
            )
            .build(CommonClass.locate("trident"))
        consumer.accept(obtainTridentAdvancement)
        //#endregion
    }
}
