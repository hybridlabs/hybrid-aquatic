package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        // glowstick recipe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.STICK, 4) // TODO stick -> glowstick
            .input(Items.STICK)
            .input(HybridAquaticItems.GLOW_SLIME)
            .criterion("has_glow_slime", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.GLOW_SLIME))
            .offerTo(exporter)

        // raw crab meat
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridAquaticItems.RAW_CRAB_MEAT)
            .input(HybridAquaticItems.CRAB_CLAW)
            .criterion("has_crab_claw", InventoryChangedCriterion.Conditions.items(HybridAquaticItems.CRAB_CLAW))
            .offerTo(exporter)

        // cooking recipes
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_CRAB_MEAT, HybridAquaticItems.COOKED_CRAB_MEAT, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_FISH_MEAT, HybridAquaticItems.COOKED_FISH_MEAT, 0.15f)
        offerCookingRecipes(exporter, HybridAquaticItems.RAW_TENTACLE, HybridAquaticItems.COOKED_TENTACLE, 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: Consumer<RecipeJsonProvider>,
        input: Item,
        output: Item,
        experience: Float
    ) {
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, input, output, experience)
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, input, output, experience)
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 600, input, output, experience)

    }
}
