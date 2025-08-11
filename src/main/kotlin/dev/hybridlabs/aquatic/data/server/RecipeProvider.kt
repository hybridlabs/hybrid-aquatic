package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.recipe.CampfireCookingRecipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SmeltingRecipe
import net.minecraft.recipe.SmokingRecipe
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?
) : FabricRecipeProvider(output, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        // misc recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .input('S', HybridAquaticItems.TUBE_SPONGE)
            .criterion("has_tube_sponge", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.TUBE_SPONGE))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.RAFT, 2)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .input('S', Items.STICK)
            .criterion("has_stick", InventoryChangedCriterion.Conditions.items(Items.STICK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.BUOY, 2)
            .pattern(" L ")
            .pattern(" S ")
            .pattern(" W ")
            .input('S', Items.STICK)
            .input('L', Items.LANTERN)
            .input('W', ItemTags.PLANKS)
            .criterion("has_lantern", InventoryChangedCriterion.Conditions.items(Items.LANTERN))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_BLADE, 1)
            .pattern(" C ")
            .pattern(" C ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('C', HybridAquaticItems.CORAL_CHUNK)
            .criterion("has_coral_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CORAL_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_PICKAXE, 1)
            .pattern("CCC")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('C', HybridAquaticItems.CORAL_CHUNK)
            .criterion("has_coral_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CORAL_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_AXE, 1)
            .pattern(" CC")
            .pattern(" SC")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('C', HybridAquaticItems.CORAL_CHUNK)
            .criterion("has_coral_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CORAL_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_SHOVEL, 1)
            .pattern(" C ")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('C', HybridAquaticItems.CORAL_CHUNK)
            .criterion("has_coral_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CORAL_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.CORAL_HOE, 1)
            .pattern(" CC")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('C', HybridAquaticItems.CORAL_CHUNK)
            .criterion("has_coral_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CORAL_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_SPEAR, 1)
            .pattern(" N ")
            .pattern(" N ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_PICKAXE, 1)
            .pattern("NNN")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_AXE, 1)
            .pattern(" NN")
            .pattern(" SN")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_SHOVEL, 1)
            .pattern(" N ")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.SEASHELL_HOE, 1)
            .pattern(" NN")
            .pattern(" S ")
            .pattern(" S ")
            .input('S', Items.STICK)
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.NAUTILUS_HELMET, 1)
            .pattern("NNN")
            .pattern("N N")
            .pattern("   ")
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.NAUTILUS_PAULDRONS, 1)
            .pattern("N N")
            .pattern("N N")
            .pattern("   ")
            .input('N', Items.NAUTILUS_SHELL)
            .criterion("has_shell", InventoryChangedCriterion.Conditions.items(Items.NAUTILUS_SHELL))
            .offerTo(exporter)

        offerSlabRecipe(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HybridAquaticBlocks.DRIFTWOOD_SLAB,
            HybridAquaticBlocks.DRIFTWOOD_PLANKS
        )
        offerBarkBlockRecipe(exporter, HybridAquaticBlocks.DRIFTWOOD_WOOD, HybridAquaticBlocks.DRIFTWOOD_LOG)
        offerPlanksRecipe(exporter, HybridAquaticBlocks.DRIFTWOOD_PLANKS, HybridAquaticItemTags.DRIFTWOOD_LOG_WOOD, 4)
        offerPressurePlateRecipe(
            exporter,
            HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE,
            HybridAquaticBlocks.DRIFTWOOD_PLANKS
        )

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HybridAquaticBlocks.DRIFTWOOD_BUTTON, 1)
            .input(HybridAquaticBlocks.DRIFTWOOD_PLANKS)
            .criterion(
                "has_driftwood_planks",
                InventoryChangedCriterion.Conditions.items(HybridAquaticBlocks.DRIFTWOOD_PLANKS)
            )
            .offerTo(exporter)

        // armor recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.TURTLE_CHESTPLATE)
            .pattern("S S")
            .pattern("SSS")
            .pattern("SSS")
            .input('S', Items.TURTLE_SCUTE)
            .criterion("has_scute", InventoryChangedCriterion.Conditions.items(Items.TURTLE_SCUTE))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_HELMET)
            .pattern("CCC")
            .pattern("CGC")
            .pattern("CCC")
            .input('C', Items.COPPER_INGOT)
            .input('G', Items.GLASS_PANE)
            .criterion("has_copper", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
            .criterion("has_glass_pane", InventoryChangedCriterion.Conditions.items(Items.GLASS_PANE))
            .criterion("has_glass", InventoryChangedCriterion.Conditions.items(Items.GLASS))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_SUIT)
            .pattern("C C")
            .pattern("LCL")
            .pattern("LLL")
            .input('L', Items.LEATHER)
            .input('C', Items.COPPER_INGOT)
            .criterion("has_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
            .criterion("has_copper", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_LEGGINGS)
            .pattern("CCC")
            .pattern("L L")
            .pattern("L L")
            .input('L', Items.LEATHER)
            .input('C', Items.COPPER_INGOT)
            .criterion("has_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
            .criterion("has_copper", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.DIVING_BOOTS)
            .pattern("C C")
            .pattern("C C")
            .pattern("   ")
            .input('C', Items.COPPER_INGOT)
            .criterion("has_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
            .criterion("has_copper", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
            .offerTo(exporter)

        //#region hooks
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.BARBED_HOOK)
            .pattern("N  ")
            .pattern("N N")
            .pattern("NNN")
            .input('N', Items.IRON_NUGGET)
            .criterion("has_iron_nugget", InventoryChangedCriterion.Conditions.items(Items.IRON_NUGGET))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.GLOWING_HOOK)
            .input(HybridAquaticItems.BARBED_HOOK)
            .input(HybridAquaticItems.GLOW_SLIME)
            .criterion("has_barbed_hook", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BARBED_HOOK))
            .criterion("has_glow_slime", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GLOW_SLIME))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.MAGNETIC_HOOK)
            .pattern("NIN")
            .pattern("N N")
            .pattern("I I")
            .input('N', Items.IRON_NUGGET)
            .input('I', Items.IRON_INGOT)
            .criterion("has_iron_nugget", InventoryChangedCriterion.Conditions.items(Items.IRON_NUGGET))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, HybridAquaticItems.CREEPERMAGNET_HOOK)
            .input(HybridAquaticItems.MAGNETIC_HOOK)
            .input(Items.GUNPOWDER)
            .criterion(
                "has_magnetic_hook",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.MAGNETIC_HOOK)
            )
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.OMINOUS_HOOK)
            .input(HybridAquaticItems.COCONUT_CRAB_CLAW)
            .input(HybridAquaticItems.YETI_CRAB_CLAW)
            .input(HybridAquaticItems.GHOST_CRAB_CLAW)
            .input(HybridAquaticItems.FLOWER_CRAB_CLAW)
            .input(HybridAquaticItems.SPIDER_CRAB_CLAW)
            .input(HybridAquaticItems.FIDDLER_CRAB_CLAW)
            .input(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
            .input(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
            .input(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
            .criterion(
                "has_crab_claw", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .offerTo(exporter)

        //#endregion

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.FISHING_NET)
            .pattern("  S")
            .pattern(" IS")
            .pattern("I  ")
            .input('I', Items.STICK)
            .input('S', Items.STRING)
            .criterion("string", InventoryChangedCriterion.Conditions.items(Items.STRING))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, HybridAquaticItems.GLOW_SLIME)
            .input(Items.SLIME_BALL)
            .input(Items.GLOW_INK_SAC)
            .criterion("has_slime_ball", InventoryChangedCriterion.Conditions.items(Items.SLIME_BALL))
            .criterion("has_glow_ink_sac", InventoryChangedCriterion.Conditions.items(Items.GLOW_INK_SAC))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.GLOWSTICK, 4)
            .input(Items.STICK)
            .input(HybridAquaticItems.GLOW_SLIME)
            .criterion("has_stick", InventoryChangedCriterion.Conditions.items(Items.STICK))
            .criterion("has_glow_slime", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GLOW_SLIME))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BONE_MEAL)
            .input(HybridAquaticItems.CUTTLEBONE)
            .criterion("has_cuttlebone", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CUTTLEBONE))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GUNPOWDER, 2)
            .input(HybridAquaticItems.SULFUR)
            .input(Items.COAL)
            .input(Items.BONE_MEAL)
            .input(Items.BONE_MEAL)
            .criterion("has_sulfur", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.SULFUR))
            .offerTo(exporter)

        // food items
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_CRAB, 1)
            .input(HybridAquaticItemTags.CRAB_CLAW)
            .criterion(
                "has_crab_claw", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.CRAB_CLAW).build()
                )
            )
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_LOBSTER, 1)
            .input(HybridAquaticItems.LOBSTER_CLAW)
            .criterion("has_lobster_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LOBSTER_CLAW))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_MEAT, 1)
            .input(HybridAquaticItemTags.SMALL_FISH)
            .criterion(
                "has_small_fish", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.SMALL_FISH).build()
                )
            )
            .offerTo(exporter, Identifier.of("hybrid-aquatic", "raw_fish_meat_small"))

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_MEAT, 2)
            .input(HybridAquaticItemTags.MEDIUM_FISH)
            .criterion(
                "has_medium_fish", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.MEDIUM_FISH).build()
                )
            )
            .offerTo(exporter, Identifier.of("hybrid-aquatic", "raw_fish_meat_medium"))

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_STEAK, 2)
            .input(HybridAquaticItemTags.LARGE_FISH)
            .criterion(
                "has_large_fish", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().tag(HybridAquaticItemTags.LARGE_FISH).build()
                )
            )
            .offerTo(exporter)

        // cooking recipes
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAB, HybridAquaticItems.COOKED_CRAB, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_SHRIMP, HybridAquaticItems.COOKED_SHRIMP, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAYFISH, HybridAquaticItems.COOKED_CRAYFISH, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_LOBSTER, HybridAquaticItems.COOKED_LOBSTER, 0.3f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_LOBSTER_TAIL, HybridAquaticItems.COOKED_LOBSTER_TAIL, 0.3f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_FISH_STEAK, HybridAquaticItems.COOKED_FISH_STEAK, 0.3f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_FISH_MEAT, HybridAquaticItems.COOKED_FISH_MEAT, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_TENTACLE, HybridAquaticItems.COOKED_TENTACLE, 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: RecipeExporter,
        input: ItemConvertible,
        output: ItemConvertible,
        experience: Float
    ) {
        offerFoodCookingRecipe(
            exporter,
            "smelting",
            RecipeSerializer.SMELTING,
            ::SmeltingRecipe,
            200,
            input,
            output,
            experience
        )

        offerFoodCookingRecipe(
            exporter,
            "smoking",
            RecipeSerializer.SMOKING,
            ::SmokingRecipe,
            100,
            input,
            output,
            experience
        )

        offerFoodCookingRecipe(
            exporter,
            "campfire_cooking",
            RecipeSerializer.CAMPFIRE_COOKING,
            ::CampfireCookingRecipe,
            600,
            input,
            output,
            experience
        )
    }
}