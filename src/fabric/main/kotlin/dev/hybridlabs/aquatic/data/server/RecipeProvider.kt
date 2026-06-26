package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.AbstractCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun buildRecipes(exporter: Consumer<FinishedRecipe>) {
        // misc recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.PRISMARINE_ROD.get())
            .pattern("P  ")
            .pattern("P  ")
            .pattern("   ")
            .define('P', Items.PRISMARINE_SHARD)
            .unlockedBy(
                "has_prismarine",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.PRISMARINE_SHARD)
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', HybridAquaticItems.TUBE_SPONGE.get())
            .unlockedBy(
                "has_tube_sponge",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.TUBE_SPONGE.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HybridAquaticItems.PEARL.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.PEARL.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HybridAquaticItems.PEARL.get(),
            9
        )
            .requires(HybridAquaticItems.PEARL_BLOCK.get())
            .unlockedBy(
                "has_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.PEARL_BLOCK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.BLACK_PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HybridAquaticItems.BLACK_PEARL.get())
            .unlockedBy(
                "has_black_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.BLACK_PEARL.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HybridAquaticItems.BLACK_PEARL.get(),
            9
        )
            .requires(HybridAquaticItems.BLACK_PEARL_BLOCK.get())
            .unlockedBy(
                "has_black_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.BLACK_PEARL_BLOCK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.GLOWSLIME_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', HybridAquaticItems.GLOWSLIME.get())
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.GLOWSLIME.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HybridAquaticItems.GLOWSLIME.get(),
            9
        )
            .requires(HybridAquaticItems.GLOWSLIME_BLOCK.get())
            .unlockedBy(
                "has_glowslime_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.GLOWSLIME_BLOCK.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowslime_from_block"))

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.RAFT.get(), 2)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', Items.STICK)
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.BUOY.get(), 2)
            .pattern(" L ")
            .pattern(" S ")
            .pattern(" W ")
            .define('S', Items.STICK)
            .define('L', Items.LANTERN)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_lantern", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LANTERN))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_BLADE.get(), 1)
            .pattern(" C ")
            .pattern(" C ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HybridAquaticItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.TRIDENT, 1)
            .pattern("SSS")
            .pattern(" R ")
            .pattern(" R ")
            .define('S', HybridAquaticItems.SHARK_TOOTH.get())
            .define('R', HybridAquaticItems.PRISMARINE_ROD.get())
            .unlockedBy(
                "has_shark_tooth",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.SHARK_TOOTH.get())
            )
            .unlockedBy(
                "has_prismarine_rod",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.PRISMARINE_ROD.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_PICKAXE.get(), 1)
            .pattern("CCC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HybridAquaticItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_AXE.get(), 1)
            .pattern(" CC")
            .pattern(" SC")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HybridAquaticItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_SHOVEL.get(), 1)
            .pattern(" C ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HybridAquaticItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_HOE.get(), 1)
            .pattern(" CC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HybridAquaticItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_SPEAR.get(), 1)
            .pattern(" N ")
            .pattern(" N ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_PICKAXE.get(), 1)
            .pattern("NNN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_AXE.get(), 1)
            .pattern(" NN")
            .pattern(" SN")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_SHOVEL.get(), 1)
            .pattern(" N ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_HOE.get(), 1)
            .pattern(" NN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.NAUTILUS_HELMET.get(), 1)
            .pattern("NNN")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.NAUTILUS_PAULDRONS.get(), 1)
            .pattern("N N")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)


        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get(),
            HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )
        woodFromLogs(
            exporter,
            HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get(),
            HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get()
        )
        planksFromLog(
            exporter,
            HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get(),
            HybridAquaticItemTags.DRIFTWOOD_LOG_WOOD,
            4
        )
        pressurePlate(
            exporter,
            HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get(),
            HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get(),
            1
        )
            .requires(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())
            .unlockedBy(
                "has_driftwood_planks",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())
            )
            .save(exporter)

        // armor recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HybridAquaticItems.TURTLE_CHESTPLATE.get())
            .pattern("S S")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', Items.SCUTE)
            .unlockedBy("has_scute", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SCUTE))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_HELMET.get())
            .pattern("CCC")
            .pattern("CGC")
            .pattern("CCC")
            .define('C', Items.COPPER_INGOT)
            .define('G', Items.GLASS_PANE)
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .unlockedBy("has_glass_pane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS_PANE))
            .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_SUIT.get())
            .pattern("C C")
            .pattern("LCL")
            .pattern("LLL")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_LEGGINGS.get())
            .pattern("CCC")
            .pattern("L L")
            .pattern("L L")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_BOOTS.get())
            .pattern("C C")
            .pattern("C C")
            .pattern("   ")
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

        //#region hooks
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.BARBED_HOOK.get())
            .pattern("N  ")
            .pattern("N N")
            .pattern("NNN")
            .define('N', Items.IRON_NUGGET)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, HybridAquaticItems.GLOWING_HOOK.get())
            .requires(HybridAquaticItems.BARBED_HOOK.get())
            .requires(HybridAquaticItems.GLOWSLIME.get())
            .unlockedBy(
                "has_barbed_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.BARBED_HOOK.get())
            )
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.GLOWSLIME.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.MAGNETIC_HOOK.get())
            .pattern("NIN")
            .pattern("N N")
            .pattern("I I")
            .define('N', Items.IRON_NUGGET)
            .define('I', Items.IRON_INGOT)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HybridAquaticItems.CREEPERMAGNET_HOOK.get())
            .requires(HybridAquaticItems.MAGNETIC_HOOK.get())
            .requires(Items.GUNPOWDER)
            .unlockedBy(
                "has_magnetic_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.MAGNETIC_HOOK.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, HybridAquaticItems.OMINOUS_HOOK.get())
            .requires(HybridAquaticItems.COCONUT_CRAB_CLAW.get())
            .requires(HybridAquaticItems.YETI_CRAB_CLAW.get())
            .requires(HybridAquaticItems.GHOST_CRAB_CLAW.get())
            .requires(HybridAquaticItems.FLOWER_CRAB_CLAW.get())
            .requires(HybridAquaticItems.SPIDER_CRAB_CLAW.get())
            .requires(HybridAquaticItems.FIDDLER_CRAB_CLAW.get())
            .requires(HybridAquaticItems.VAMPIRE_CRAB_CLAW.get())
            .requires(HybridAquaticItems.DUNGENESS_CRAB_CLAW.get())
            .requires(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get())
            .unlockedBy(
                "has_crab_claw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .save(exporter)

        //#endregion

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HybridAquaticItems.FISHING_NET.get())
            .pattern("  S")
            .pattern(" IS")
            .pattern("I  ")
            .define('I', Items.STICK)
            .define('S', Items.STRING)
            .unlockedBy("string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HybridAquaticItems.GLOWSLIME.get())
            .requires(Items.SLIME_BALL)
            .requires(Items.GLOW_INK_SAC)
            .unlockedBy("has_slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
            .unlockedBy("has_glow_ink_sac", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOW_INK_SAC))
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowslime_from_slime"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.GLOWSTICK.get(), 4)
            .requires(Items.STICK)
            .requires(HybridAquaticItems.GLOWSLIME.get())
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.GLOWSLIME.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL)
            .requires(HybridAquaticItems.CUTTLEBONE.get())
            .unlockedBy(
                "has_cuttlebone",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.CUTTLEBONE.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2)
            .requires(HybridAquaticItems.SULFUR.get())
            .requires(Items.COAL)
            .requires(Items.BONE_MEAL)
            .requires(Items.BONE_MEAL)
            .unlockedBy("has_sulfur", InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.SULFUR.get()))
            .save(exporter)

        // food items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridAquaticItems.RAW_CRAB.get(), 1)
            .requires(HybridAquaticItemTags.CRAB_CLAW)
            .unlockedBy(
                "has_crab_claw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridAquaticItems.RAW_LOBSTER.get(), 1)
            .requires(HybridAquaticItems.LOBSTER_CLAW.get())
            .unlockedBy(
                "has_lobster_claw",
                InventoryChangeTrigger.TriggerInstance.hasItems(HybridAquaticItems.LOBSTER_CLAW.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_MEAT.get(), 1)
            .requires(HybridAquaticItemTags.SMALL_FISH)
            .unlockedBy(
                "has_small_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.SMALL_FISH).build()
                )
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "raw_fish_meat_small"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_MEAT.get(), 2)
            .requires(HybridAquaticItemTags.MEDIUM_FISH)
            .unlockedBy(
                "has_medium_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.MEDIUM_FISH).build()
                )
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "raw_fish_meat_medium"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_STEAK.get(), 2)
            .requires(HybridAquaticItemTags.LARGE_FISH)
            .unlockedBy(
                "has_large_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HybridAquaticItemTags.LARGE_FISH).build()
                )
            )
            .save(exporter)

        // cooking recipes
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAB.get(), HybridAquaticItems.COOKED_CRAB.get(), 0.15f)
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_SHRIMP.get(),
            HybridAquaticItems.COOKED_SHRIMP.get(),
            0.15f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_CRAYFISH.get(),
            HybridAquaticItems.COOKED_CRAYFISH.get(),
            0.15f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_LOBSTER.get(),
            HybridAquaticItems.COOKED_LOBSTER.get(),
            0.3f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_LOBSTER_TAIL.get(),
            HybridAquaticItems.COOKED_LOBSTER_TAIL.get(),
            0.3f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_FISH_STEAK.get(),
            HybridAquaticItems.COOKED_FISH_STEAK.get(),
            0.3f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_FISH_MEAT.get(),
            HybridAquaticItems.COOKED_FISH_MEAT.get(),
            0.15f
        )
        offerCookingRecipes(
            exporter,
            HybridAquaticItems.RAW_TENTACLE.get(),
            HybridAquaticItems.COOKED_TENTACLE.get(),
            0.15f
        )

        offerKelpCookingRecipes(exporter, HybridAquaticItemTags.KELPS, Items.DRIED_KELP, 0.15f)

    }

    private fun offerCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        input: Item,
        output: Item,
        experience: Float
    ) {
        simpleCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, input, output, experience)
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, input, output, experience)
        simpleCookingRecipe(
            exporter,
            "campfire_cooking",
            RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
            600,
            input,
            output,
            experience
        )
    }

    private fun offerKelpCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        offerKelpCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, inputTag, output, experience)
        offerKelpCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, inputTag, output, experience)
        offerKelpCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600, inputTag, output, experience)
    }

    private fun offerKelpCookingRecipe(
        exporter: Consumer<FinishedRecipe>,
        cooker: String,
        serializer: RecipeSerializer<out AbstractCookingRecipe>,
        cookingTime: Int,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        val builder = SimpleCookingRecipeBuilder
            .generic(Ingredient.of(inputTag), RecipeCategory.FOOD, output, experience, cookingTime, serializer)
            .unlockedBy("has_kelp", has(inputTag))

        val recipeId = getItemName(output) + "_from_" + cooker
        builder.save(exporter, recipeId)
    }
}
