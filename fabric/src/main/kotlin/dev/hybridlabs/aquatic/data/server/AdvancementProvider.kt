package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.FrameType
import net.minecraft.advancements.critereon.EnterBlockTrigger
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.KilledTrigger
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks.WATER
import java.util.function.Consumer

class AdvancementProvider(output: FabricDataOutput) : FabricAdvancementProvider(output) {
    override fun generateAdvancement(consumer: Consumer<Advancement>?) {
        val rootAdvancement = Advancement.Builder.advancement()
            .display(
                HybridAquaticItems.TUNA.get(),
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

        val boatAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                Items.OAK_BOAT,
                Component.translatable("advancements.hybrid-aquatic.boat.title"),
                Component.translatable("advancements.hybrid-aquatic.boat.description"),
                ResourceLocation("hybrid-aquatic", "textures/block/coralstone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "has_boat",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(ItemTags.BOATS).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "boat"))
        consumer?.accept(boatAdvancement)

        val fishingNetAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.FISHING_NET.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.FISHING_NET.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "fishing_net"))
        consumer?.accept(fishingNetAdvancement)

        val glowstickAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.GLOWSTICK.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.GLOWSTICK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "glowstick"))
        consumer?.accept(glowstickAdvancement)

        val buoyAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.BUOY.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.BUOY.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "buoy"))
        consumer?.accept(buoyAdvancement)

        //#region Seashell Set Advancement Tree
        val seashellAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
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
                HybridAquaticItems.SEASHELL_SPEAR.get(),
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
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.SEASHELL_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "seashell_tools"))
        consumer?.accept(seashellToolsAdvancement)
        //#endregion

        //#region Coral Set Advancement Tree
        val coralChunkAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.CORAL_CHUNK.get(),
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
                    HybridAquaticItems.CORAL_CHUNK.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "coral_chunk"))
        consumer?.accept(coralChunkAdvancement)

        val coralToolsAdvancement = Advancement.Builder.advancement()
            .parent(coralChunkAdvancement)
            .display(
                HybridAquaticItems.CORAL_BLADE.get(),
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
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.CORAL_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "coral_tools"))
        consumer?.accept(coralToolsAdvancement)
        //#endregion

        //#region Turtle Set Advancement Tree
        val turtleScuteAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
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
                HybridAquaticItems.TURTLE_CHESTPLATE.get(),
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
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.TURTLE_SET).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "turtle_set"))
        consumer?.accept(turtleSetAdvancement)
        //#endregion

        //#region Diving Set Advancement Tree
        val divingSuitAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.DIVING_HELMET.get(),
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
                    HybridAquaticItems.DIVING_HELMET.get(),
                    HybridAquaticItems.DIVING_SUIT.get(),
                    HybridAquaticItems.DIVING_LEGGINGS.get(),
                    HybridAquaticItems.DIVING_BOOTS.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "diving_suit"))
        consumer?.accept(divingSuitAdvancement)

        val divingUpgradeAdvancement = Advancement.Builder.advancement()
            .parent(divingSuitAdvancement)
            .display(
                HybridAquaticItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
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
                    HybridAquaticItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "diving_upgrade"))
        consumer?.accept(divingUpgradeAdvancement)

        val reinforcedDivingSuitAdvancement = Advancement.Builder.advancement()
            .parent(divingUpgradeAdvancement)
            .display(
                HybridAquaticItems.REINFORCED_DIVING_HELMET.get(),
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
                    HybridAquaticItems.REINFORCED_DIVING_HELMET.get(),
                    HybridAquaticItems.REINFORCED_DIVING_SUIT.get(),
                    HybridAquaticItems.REINFORCED_DIVING_LEGGINGS.get(),
                    HybridAquaticItems.REINFORCED_DIVING_BOOTS.get()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "reinforced_diving_suit"))
        consumer?.accept(reinforcedDivingSuitAdvancement)
        //#endregion

        //#region Pearl Advancement Tree
        val obtainPearlAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.PEARL.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.PEARL.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "pearl"))
        consumer?.accept(obtainPearlAdvancement)

        val obtainBlackPearlAdvancement = Advancement.Builder.advancement()
            .parent(obtainPearlAdvancement)
            .display(
                HybridAquaticItems.BLACK_PEARL.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.BLACK_PEARL.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "black_pearl"))
        consumer?.accept(obtainBlackPearlAdvancement)
        //#endregion

        val fishingHookAdvancement = Advancement.Builder.advancement()
            .parent(fishingNetAdvancement)
            .display(
                HybridAquaticItems.BARBED_HOOK.get(),
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
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.LURE_ITEMS).build()
                )

            ).build(ResourceLocation("hybrid-aquatic", "hook"))
        consumer?.accept(fishingHookAdvancement)

        val creeperHookAdvancement = Advancement.Builder.advancement()
            .parent(fishingHookAdvancement)
            .display(
                HybridAquaticItems.CREEPERMAGNET_HOOK.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CREEPERMAGNET_HOOK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "creeper_hook"))
        consumer?.accept(creeperHookAdvancement)

        //#region Karkinos Advancement Tree
        val crabClawAdvancement = Advancement.Builder.advancement()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.DUNGENESS_CRAB_CLAW.get(),
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
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "crab_claw"))
        consumer?.accept(crabClawAdvancement)

        val ominousHookAdvancement = Advancement.Builder.advancement()
            .parent(crabClawAdvancement)
            .display(
                HybridAquaticItems.OMINOUS_HOOK.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.OMINOUS_HOOK.get())
            )
            .build(ResourceLocation("hybrid-aquatic", "ominous_hook"))
        consumer?.accept(ominousHookAdvancement)

        val killKarkinosAdvancement = Advancement.Builder.advancement()
            .parent(ominousHookAdvancement)
            .display(
                HybridAquaticItems.KARKINOS_CLAW.get(),
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
                    EntityPredicate.Builder.entity().of(HybridAquaticEntityTypes.KARKINOS.get()).build()
                )
            )
            .build(ResourceLocation("hybrid-aquatic", "kill_karkinos"))
        consumer?.accept(killKarkinosAdvancement)
        //#endregion

        //#region Trident Advancement Tree
        val obtainSharkToothAdvancement = Advancement.Builder.advancement()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.SHARK_TOOTH.get(),
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
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.SHARK_TOOTH.get())
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
