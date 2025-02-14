package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementFrame
import net.minecraft.advancement.criterion.EnterBlockCriterion
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.advancement.criterion.OnKilledCriterion
import net.minecraft.block.Blocks.WATER
import net.minecraft.item.Items
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.registry.tag.ItemTags
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class AdvancementProvider(output: FabricDataOutput) : FabricAdvancementProvider(output) {
    override fun generateAdvancement(consumer: Consumer<Advancement>?) {
        val rootAdvancement = Advancement.Builder.create()
            .display(
                HybridAquaticItems.YELLOWFIN_TUNA,
                Text.translatable("advancements.hybrid-aquatic.enter_water.title"),
                Text.translatable("advancements.hybrid-aquatic.enter_water.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "enter_water",
                EnterBlockCriterion.Conditions.block(WATER)
            )
            .build(Identifier("hybrid-aquatic", "root"))
        consumer?.accept(rootAdvancement)

        val boatAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                Items.OAK_BOAT,
                Text.translatable("advancements.hybrid-aquatic.boat.title"),
                Text.translatable("advancements.hybrid-aquatic.boat.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "has_boat",
                InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(ItemTags.BOATS).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "boat"))
        consumer?.accept(boatAdvancement)

        val fishingNetAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.FISHING_NET,
                Text.translatable("advancements.hybrid-aquatic.fishing_net.title"),
                Text.translatable("advancements.hybrid-aquatic.fishing_net.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "fishing_net",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FISHING_NET)
            )
            .build(Identifier("hybrid-aquatic", "fishing_net"))
        consumer?.accept(fishingNetAdvancement)

        val glowstickAdvancement = Advancement.Builder.create()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.GLOWSTICK,
                Text.translatable("advancements.hybrid-aquatic.glowstick.title"),
                Text.translatable("advancements.hybrid-aquatic.glowstick.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "obtain_glowstick",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GLOWSTICK)
            )
            .build(Identifier("hybrid-aquatic", "glowstick"))
        consumer?.accept(glowstickAdvancement)

        val buoyAdvancement = Advancement.Builder.create()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.BUOY,
                Text.translatable("advancements.hybrid-aquatic.buoy.title"),
                Text.translatable("advancements.hybrid-aquatic.buoy.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "obtain_buoy",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BUOY)
            )
            .build(Identifier("hybrid-aquatic", "buoy"))
        consumer?.accept(buoyAdvancement)

        val divingSuitAdvancement = Advancement.Builder.create()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.DIVING_HELMET,
                Text.translatable("advancements.hybrid-aquatic.diving_suit.title"),
                Text.translatable("advancements.hybrid-aquatic.diving_suit.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion(
                "obtain_diving_suit",
                InventoryChangedCriterion.Conditions.items(
                    HybridAquaticItems.DIVING_HELMET,
                    HybridAquaticItems.DIVING_SUIT,
                    HybridAquaticItems.DIVING_LEGGINGS,
                    HybridAquaticItems.DIVING_BOOTS
                )
            )
            .build(Identifier("hybrid-aquatic", "diving_suit"))
        consumer?.accept(divingSuitAdvancement)

        val obtainPearlAdvancement = Advancement.Builder.create()
            .parent(divingSuitAdvancement)
            .display(
                HybridAquaticItems.PEARL,
                Text.translatable("advancements.hybrid-aquatic.pearl.title"),
                Text.translatable("advancements.hybrid-aquatic.pearl.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "obtain_pearl",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.PEARL)
            )
            .build(Identifier("hybrid-aquatic", "pearl"))
        consumer?.accept(obtainPearlAdvancement)

        val obtainBlackPearlAdvancement = Advancement.Builder.create()
            .parent(obtainPearlAdvancement)
            .display(
                HybridAquaticItems.BLACK_PEARL,
                Text.translatable("advancements.hybrid-aquatic.black_pearl.title"),
                Text.translatable("advancements.hybrid-aquatic.black_pearl.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "obtain_black_pearl",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BLACK_PEARL)
            )
            .build(Identifier("hybrid-aquatic", "black_pearl"))
        consumer?.accept(obtainBlackPearlAdvancement)

        val fishingHookAdvancement = Advancement.Builder.create()
            .parent(fishingNetAdvancement)
            .display(
                HybridAquaticItems.BARBED_HOOK,
                Text.translatable("advancements.hybrid-aquatic.hook.title"),
                Text.translatable("advancements.hybrid-aquatic.hook.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "has_hook",
                InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.LURE_ITEMS).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "hook"))
        consumer?.accept(fishingHookAdvancement)

        val creeperHookAdvancement = Advancement.Builder.create()
            .parent(fishingHookAdvancement)
            .display(
                HybridAquaticItems.CREEPERMAGNET_HOOK,
                Text.translatable("advancements.hybrid-aquatic.creeper_hook.title"),
                Text.translatable("advancements.hybrid-aquatic.creeper_hook.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                true
            )
            .criterion(
                "has_creeper_hook",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CREEPERMAGNET_HOOK)
            )
            .build(Identifier("hybrid-aquatic", "creeper_hook"))
        consumer?.accept(creeperHookAdvancement)

        val crabClawAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.DUNGENESS_CRAB_CLAW,
                Text.translatable("advancements.hybrid-aquatic.crab_claw.title"),
                Text.translatable("advancements.hybrid-aquatic.crab_claw.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion(
                "has_crab_claw",
                InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "crab_claw"))
        consumer?.accept(crabClawAdvancement)

        val ominousHookAdvancement = Advancement.Builder.create()
            .parent(crabClawAdvancement)
            .display(
                HybridAquaticItems.OMINOUS_HOOK,
                Text.translatable("advancements.hybrid-aquatic.ominous_hook.title"),
                Text.translatable("advancements.hybrid-aquatic.ominous_hook.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                true
            )
            .criterion(
                "obtain_ominous_hook",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.OMINOUS_HOOK)
            )
            .build(Identifier("hybrid-aquatic", "ominous_hook"))
        consumer?.accept(ominousHookAdvancement)

        val killKarkinosAdvancement = Advancement.Builder.create()
            .parent(ominousHookAdvancement)
            .display(
                HybridAquaticItems.KARKINOS_CLAW,
                Text.translatable("advancements.hybrid-aquatic.kill_karkinos.title"),
                Text.translatable("advancements.hybrid-aquatic.kill_karkinos.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.CHALLENGE,
                true,
                true,
                true
            )
            .criterion(
                "kill_karkinos",
                OnKilledCriterion.Conditions.createPlayerKilledEntity(
                    EntityPredicate.Builder.create().type(HybridAquaticEntityTypes.KARKINOS).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "kill_karkinos"))
        consumer?.accept(killKarkinosAdvancement)

        val killSharkAdvancement = Advancement.Builder.create()
            .parent(boatAdvancement)
            .display(
                HybridAquaticItems.SHARK_TOOTH,
                Text.translatable("advancements.hybrid-aquatic.bigger_boat.title"),
                Text.translatable("advancements.hybrid-aquatic.bigger_boat.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion(
                "kill_shark",
                OnKilledCriterion.Conditions.createPlayerKilledEntity(
                    EntityPredicate.Builder.create().type(HybridAquaticEntityTags.SHARK).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "bigger_boat"))
        consumer?.accept(killSharkAdvancement)

    }
}