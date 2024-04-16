package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.recipe.CampfireCookingRecipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SmeltingRecipe
import net.minecraft.recipe.SmokingRecipe
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.tag.ItemTags

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generate(exporter: RecipeExporter) {
        // misc recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE)
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .input('S', HybridAquaticItems.SPONGE_CHUNK)
            .criterion("has_sponge_chunk", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.SPONGE_CHUNK))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, HybridAquaticItems.BUOY,2)
            .pattern(" L ")
            .pattern(" S ")
            .pattern(" W ")
            .input('S', Items.STICK)
            .input('L', Items.LANTERN)
            .input('W', ItemTags.PLANKS)
            .criterion("has_lantern", InventoryChangedCriterion.Conditions.items(Items.LANTERN))
            .offerTo(exporter)

        // armor recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HybridAquaticItems.TURTLE_CHESTPLATE)
            .pattern("S S")
            .pattern("SSS")
            .pattern("SSS")
            .input('S', Items.SCUTE)
            .criterion("has_scute", InventoryChangedCriterion.Conditions.items(Items.SCUTE))
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

        // hook recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.BARBED_HOOK)
            .pattern("N  ")
            .pattern("N N")
            .pattern("NNN")
            .input('N', Items.IRON_NUGGET)
            .criterion("has_iron_nugget", InventoryChangedCriterion.Conditions.items(Items.IRON_NUGGET))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.MAGNETIC_HOOK)
            .pattern("NIN")
            .pattern("N N")
            .pattern("I I")
            .input('N', Items.IRON_NUGGET)
            .input('I', Items.IRON_INGOT)
            .criterion("has_iron_nugget", InventoryChangedCriterion.Conditions.items(Items.IRON_NUGGET))
            .offerTo(exporter)

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.FISHING_NET)
            .pattern("  S")
            .pattern(" IS")
            .pattern("I  ")
            .input('I', Items.STICK)
            .input('S', Items.STRING)
            .criterion("string", InventoryChangedCriterion.Conditions.items(Items.STRING))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, HybridAquaticItems.GLOWING_HOOK)
            .input(HybridAquaticItems.BARBED_HOOK)
            .input(HybridAquaticItems.GLOW_SLIME)
            .criterion("has_barbed_hook", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BARBED_HOOK))
            .criterion("has_glow_slime", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GLOW_SLIME))
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
            .criterion("has_barbed_hook", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BARBED_HOOK))
            .criterion("has_dungeness_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.DUNGENESS_CRAB_CLAW))
            .criterion("has_coconut_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.COCONUT_CRAB_CLAW))
            .criterion("has_spider_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.SPIDER_CRAB_CLAW))
            .criterion("has_vampire_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.VAMPIRE_CRAB_CLAW))
            .criterion("has_yeti_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.YETI_CRAB_CLAW))
            .criterion("has_ghost_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GHOST_CRAB_CLAW))
            .criterion("has_lightfoot_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW))
            .criterion("has_flower_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FLOWER_CRAB_CLAW))
            .criterion("has_fiddler_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FIDDLER_CRAB_CLAW))
            .offerTo(exporter)

        // food items
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_CRAB,1)
            .input(HybridAquaticItemTags.CRAB_CLAW)
            .criterion("has_dungeness_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.DUNGENESS_CRAB_CLAW))
            .criterion("has_coconut_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.COCONUT_CRAB_CLAW))
            .criterion("has_spider_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.SPIDER_CRAB_CLAW))
            .criterion("has_vampire_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.VAMPIRE_CRAB_CLAW))
            .criterion("has_yeti_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.YETI_CRAB_CLAW))
            .criterion("has_ghost_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GHOST_CRAB_CLAW))
            .criterion("has_lightfoot_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW))
            .criterion("has_flower_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FLOWER_CRAB_CLAW))
            .criterion("has_fiddler_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FIDDLER_CRAB_CLAW))
            .criterion("has_lobster_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LOBSTER_CLAW))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_LOBSTER,1)
            .input(HybridAquaticItems.LOBSTER_CLAW)
            .criterion("has_lobster_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LOBSTER_CLAW))
            .criterion("has_dungeness_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.DUNGENESS_CRAB_CLAW))
            .criterion("has_coconut_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.COCONUT_CRAB_CLAW))
            .criterion("has_spider_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.SPIDER_CRAB_CLAW))
            .criterion("has_vampire_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.VAMPIRE_CRAB_CLAW))
            .criterion("has_yeti_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.YETI_CRAB_CLAW))
            .criterion("has_ghost_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GHOST_CRAB_CLAW))
            .criterion("has_lightfoot_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW))
            .criterion("has_flower_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FLOWER_CRAB_CLAW))
            .criterion("has_fiddler_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.FIDDLER_CRAB_CLAW))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_MEAT, 1)
            .input(HybridAquaticItemTags.SMALL_FISH)
            .criterion("has_lionfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.LIONFISH))
            .criterion("has_rockfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.ROCKFISH))
            .criterion("has_blue_spotted_stingray", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BLUE_SPOTTED_STINGRAY))
            .criterion("has_needlefish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.NEEDLEFISH))
            .criterion("has_clownfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CLOWNFISH))
            .criterion("has_blue_tang", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BLUE_TANG))
            .criterion("has_barreleye", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BARRELEYE))
            .criterion("has_anglerfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.ANGLERFISH))
            .criterion("has_piranha", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.PIRANHA))
            .criterion("has_unicorn_fish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.UNICORN_FISH))
            .criterion("has_cowfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.COWFISH))
            .criterion("has_tiger_barb", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.TIGER_BARB))
            .criterion("has_oscar", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.OSCAR))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_FISH_STEAK, 1)
            .input(HybridAquaticItemTags.MEDIUM_FISH)
            .criterion("has_yellowfin_tuna", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.YELLOWFIN_TUNA))
            .criterion("has_mahi_mahi", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.MAHI_MAHI))
            .criterion("has_opah", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.OPAH))
            .criterion("has_moray_eel", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.MORAY_EEL))
            .criterion("has_triggerfish", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.TRIGGERFISH))
            .offerTo(exporter)

        // cooking recipes
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAB, HybridAquaticItems.COOKED_CRAB, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_SHRIMP, HybridAquaticItems.COOKED_SHRIMP, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAYFISH, HybridAquaticItems.COOKED_CRAYFISH, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_LOBSTER, HybridAquaticItems.COOKED_LOBSTER, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_FISH_STEAK, HybridAquaticItems.COOKED_FISH_STEAK, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_FISH_MEAT, HybridAquaticItems.COOKED_FISH_MEAT, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_TENTACLE, HybridAquaticItems.COOKED_TENTACLE, 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float
    ) {
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, ::SmeltingRecipe, 200, input, output, experience)
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, ::SmokingRecipe, 100, input, output, experience)
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
