package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.item.HAPIItems
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.criterion.InventoryChangeTrigger
import net.minecraft.advancements.criterion.ItemPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.RecipeProvider as VanillaRecipeProvider
import net.minecraft.resources.Identifier
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.concurrent.CompletableFuture

class RecipeProvider(output: FabricPackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, lookupProvider) {
    override fun createRecipeProvider(
        registries: HolderLookup.Provider,
        output: RecipeOutput
    ): VanillaRecipeProvider = GeneratedRecipes(registries, output)

    override fun getName(): String = "Recipes"

    private class GeneratedRecipes(
        registries: HolderLookup.Provider,
        output: RecipeOutput
    ) : VanillaRecipeProvider(registries, output) {
        private val itemLookup = registries.lookupOrThrow(Registries.ITEM)

        override fun buildRecipes() {

        //#region Sandstone Block Recipes
        stair(
            HAItems.WHITE_SANDSTONE_STAIRS.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stair(
            HAItems.SMOOTH_WHITE_SANDSTONE_STAIRS.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get()
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_SLAB.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE_SLAB.get(),
            HAItems.CUT_WHITE_SANDSTONE.get()
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_SLAB.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get()
        )

        chiseled(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_SLAB.get(),
            HAItems.WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE_SLAB.get(),
            HAItems.CUT_WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_SLAB.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_STAIRS.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_STAIRS.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_WALL.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        //red brinestone
        //#region polished red brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_RED_BRINESTONE.get(),
            HAItems.RED_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_RED_BRINESTONE.get(),
            HAItems.RED_BRINESTONE.get()
        )
        //#endregion

        //#region chiseled red brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_RED_BRINESTONE.get(),
            HAItems.POLISHED_RED_BRINESTONE.get()
        )

        chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_RED_BRINESTONE.get(),
            Ingredient.of(HAItems.POLISHED_RED_BRINESTONE.get())
        )
        //#endregion

        //#region red brinestone stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_STAIRS.get(),
            HAItems.RED_BRINESTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_RED_BRINESTONE_STAIRS.get(),
            HAItems.POLISHED_RED_BRINESTONE.get()
        )

        stair(
            HAItems.RED_BRINESTONE_STAIRS.get(),
            HABlocks.RED_BRINESTONE.get()
        )

        stair(
            HAItems.POLISHED_RED_BRINESTONE_STAIRS.get(),
            HABlocks.POLISHED_RED_BRINESTONE.get()
        )
        //#endregion

        //#region red brinestone slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_SLAB.get(),
            HAItems.RED_BRINESTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_RED_BRINESTONE_SLAB.get(),
            HAItems.POLISHED_RED_BRINESTONE.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.RED_BRINESTONE_SLAB.get(),
            HABlocks.RED_BRINESTONE.get(),
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.POLISHED_RED_BRINESTONE_SLAB.get(),
            HABlocks.POLISHED_RED_BRINESTONE.get(),
        )
        //#endregion

        //#region red brinestone walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_WALL.get(),
            HAItems.RED_BRINESTONE.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.RED_BRINESTONE_WALL.get(),
            HABlocks.RED_BRINESTONE.get(),
        )
        //#endregion
        
        //#region red brinestone bricks
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_RED_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_RED_BRINESTONE.get()
        )
        //#endregion

        //#region red brinestone brick stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_BRICK_STAIRS.get(),
            HAItems.RED_BRINESTONE_BRICKS.get()
        )

        stair(
            HAItems.RED_BRINESTONE_BRICK_STAIRS.get(),
            HABlocks.RED_BRINESTONE_BRICKS.get()
        )
        //#endregion

        //#region red brinestone brick slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_BRICK_SLAB.get(),
            HAItems.RED_BRINESTONE_BRICKS.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.RED_BRINESTONE_BRICK_SLAB.get(),
            HABlocks.RED_BRINESTONE_BRICKS.get(),
        )
        //#endregion

        //#region red brinestone brick walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.RED_BRINESTONE_BRICK_WALL.get(),
            HAItems.RED_BRINESTONE_BRICKS.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.RED_BRINESTONE_BRICK_WALL.get(),
            HABlocks.RED_BRINESTONE_BRICKS.get()
        )
        //#endregion

        //orange
        //#region polished orange brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_ORANGE_BRINESTONE.get(),
            HAItems.ORANGE_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_ORANGE_BRINESTONE.get(),
            HAItems.ORANGE_BRINESTONE.get()
        )
        //#endregion

        //#region chiseled orange brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_ORANGE_BRINESTONE.get(),
            HAItems.POLISHED_ORANGE_BRINESTONE.get()
        )

        chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_ORANGE_BRINESTONE.get(),
            Ingredient.of(HAItems.POLISHED_ORANGE_BRINESTONE.get())
        )
        //#endregion

        //#region orange brinestone stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_STAIRS.get(),
            HAItems.ORANGE_BRINESTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_ORANGE_BRINESTONE_STAIRS.get(),
            HAItems.POLISHED_ORANGE_BRINESTONE.get()
        )

        stair(
            HAItems.ORANGE_BRINESTONE_STAIRS.get(),
            HABlocks.ORANGE_BRINESTONE.get()
        )

        stair(
            HAItems.POLISHED_ORANGE_BRINESTONE_STAIRS.get(),
            HABlocks.POLISHED_ORANGE_BRINESTONE.get()
        )
        //#endregion

        //#region orange brinestone slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_SLAB.get(),
            HAItems.ORANGE_BRINESTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_ORANGE_BRINESTONE_SLAB.get(),
            HAItems.POLISHED_ORANGE_BRINESTONE.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.ORANGE_BRINESTONE_SLAB.get(),
            HABlocks.ORANGE_BRINESTONE.get(),
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get(),
            HABlocks.POLISHED_ORANGE_BRINESTONE.get(),
        )
        //#endregion

        //#region orange brinestone walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_WALL.get(),
            HAItems.ORANGE_BRINESTONE.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.ORANGE_BRINESTONE_WALL.get(),
            HABlocks.ORANGE_BRINESTONE.get()
        )
        //#endregion

        //#region orange brinestone bricks
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_ORANGE_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_ORANGE_BRINESTONE.get()
        )
        //#endregion

        //#region orange brinestone brick stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_BRICK_STAIRS.get(),
            HAItems.ORANGE_BRINESTONE_BRICKS.get()
        )

        stair(
            HAItems.ORANGE_BRINESTONE_BRICK_STAIRS.get(),
            HABlocks.ORANGE_BRINESTONE_BRICKS.get()
        )
        //#endregion

        //#region orange brinestone brick slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_BRICK_SLAB.get(),
            HAItems.ORANGE_BRINESTONE_BRICKS.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.ORANGE_BRINESTONE_BRICK_SLAB.get(),
            HABlocks.ORANGE_BRINESTONE_BRICKS.get(),
        )
        //#endregion

        //#region orange brinestone brick walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.ORANGE_BRINESTONE_BRICK_WALL.get(),
            HAItems.ORANGE_BRINESTONE_BRICKS.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.ORANGE_BRINESTONE_BRICK_WALL.get(),
            HABlocks.ORANGE_BRINESTONE_BRICKS.get()
        )
        //#endregion

        //yellow
        //#region polished yellow brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_YELLOW_BRINESTONE.get(),
            HAItems.YELLOW_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_YELLOW_BRINESTONE.get(),
            HAItems.YELLOW_BRINESTONE.get()
        )
        //#endregion
        
        //#region chiseled yellow brinestone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_YELLOW_BRINESTONE.get(),
            HAItems.POLISHED_YELLOW_BRINESTONE.get()
        )

        chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_YELLOW_BRINESTONE.get(),
            Ingredient.of(HAItems.POLISHED_YELLOW_BRINESTONE.get())
        )
        //#endregion

        //#region yellow brinestone stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_STAIRS.get(),
            HAItems.YELLOW_BRINESTONE.get()
        )
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_YELLOW_BRINESTONE_STAIRS.get(),
            HAItems.POLISHED_YELLOW_BRINESTONE.get()
        )
        
        stair(
            HAItems.YELLOW_BRINESTONE_STAIRS.get(),
            HABlocks.YELLOW_BRINESTONE.get()
        )
        
        stair(
            HAItems.POLISHED_YELLOW_BRINESTONE_STAIRS.get(),
            HABlocks.POLISHED_YELLOW_BRINESTONE.get()
        )
        //#endregion

        //#region yellow brinestone slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_SLAB.get(),
            HAItems.YELLOW_BRINESTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_YELLOW_BRINESTONE_SLAB.get(),
            HAItems.POLISHED_YELLOW_BRINESTONE.get(),
            2
        )
        
        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.YELLOW_BRINESTONE_SLAB.get(),
            HABlocks.YELLOW_BRINESTONE.get(),
        )
        
        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get(),
            HABlocks.POLISHED_YELLOW_BRINESTONE.get(),
        )
        //#endregion

        //#region yellow brinestone walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_WALL.get(),
            HAItems.YELLOW_BRINESTONE.get()
        )
        
        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.YELLOW_BRINESTONE_WALL.get(),
            HABlocks.YELLOW_BRINESTONE.get()
        )
        //#endregion

        //#region yellow brinestone bricks
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_YELLOW_BRINESTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_BRICKS.get(),
            HAItems.POLISHED_YELLOW_BRINESTONE.get()
        )
        //#endregion

        //#region yellow brinestone brick stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_BRICK_STAIRS.get(),
            HAItems.YELLOW_BRINESTONE_BRICKS.get()
        )

        stair(
            HAItems.YELLOW_BRINESTONE_BRICK_STAIRS.get(),
            HABlocks.YELLOW_BRINESTONE_BRICKS.get()
        )
        //#endregion

        //#region yellow brinestone brick slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_BRICK_SLAB.get(),
            HAItems.YELLOW_BRINESTONE_BRICKS.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.YELLOW_BRINESTONE_BRICK_SLAB.get(),
            HABlocks.YELLOW_BRINESTONE_BRICKS.get(),
        )
        //#endregion

        //#region yellow brinestone brick walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.YELLOW_BRINESTONE_BRICK_WALL.get(),
            HAItems.YELLOW_BRINESTONE_BRICKS.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.YELLOW_BRINESTONE_BRICK_WALL.get(),
            HABlocks.YELLOW_BRINESTONE_BRICKS.get()
        )
        //#endregion
        //#endregion

        //schist
        //#region polished schist
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_SCHIST.get(),
            HAItems.SCHIST.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_SCHIST.get(),
            HAItems.SCHIST.get()
        )
        //#endregion

        //#region chiseled schist
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_SCHIST.get(),
            HAItems.POLISHED_SCHIST.get()
        )

        chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_SCHIST.get(),
            Ingredient.of(HAItems.POLISHED_SCHIST.get())
        )
        //#endregion

        //#region schist stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_STAIRS.get(),
            HAItems.SCHIST.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_SCHIST_STAIRS.get(),
            HAItems.POLISHED_SCHIST.get()
        )

        stair(
            HAItems.SCHIST_STAIRS.get(),
            HABlocks.SCHIST.get()
        )

        stair(
            HAItems.POLISHED_SCHIST_STAIRS.get(),
            HABlocks.POLISHED_SCHIST.get()
        )
        //#endregion

        //#region schist slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_SLAB.get(),
            HAItems.SCHIST.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_SCHIST_SLAB.get(),
            HAItems.POLISHED_SCHIST.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.SCHIST_SLAB.get(),
            HABlocks.SCHIST.get(),
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.POLISHED_SCHIST_SLAB.get(),
            HABlocks.POLISHED_SCHIST.get(),
        )
        //#endregion

        //#region schist walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_WALL.get(),
            HAItems.SCHIST.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.SCHIST_WALL.get(),
            HABlocks.SCHIST.get()
        )
        //#endregion

        //#region schist bricks
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_BRICKS.get(),
            HAItems.POLISHED_SCHIST.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_BRICKS.get(),
            HAItems.POLISHED_SCHIST.get()
        )
        //#endregion

        //#region schist brick stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_BRICK_STAIRS.get(),
            HAItems.SCHIST_BRICKS.get()
        )

        stair(
            HAItems.SCHIST_BRICK_STAIRS.get(),
            HABlocks.SCHIST_BRICKS.get()
        )
        //#endregion

        //#region schist brick slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_BRICK_SLAB.get(),
            HAItems.SCHIST_BRICKS.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.SCHIST_BRICK_SLAB.get(),
            HABlocks.SCHIST_BRICKS.get(),
        )
        //#endregion

        //#region schist brick walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SCHIST_BRICK_WALL.get(),
            HAItems.SCHIST_BRICKS.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.SCHIST_BRICK_WALL.get(),
            HABlocks.SCHIST_BRICKS.get()
        )
        //#endregion

        //chimneystone
        //#region polished chimneystone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_CHIMNEYSTONE.get(),
            HAItems.CHIMNEYSTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_CHIMNEYSTONE.get(),
            HAItems.CHIMNEYSTONE.get()
        )
        //#endregion

        //#region chiseled chimneystone
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_CHIMNEYSTONE.get(),
            HAItems.POLISHED_CHIMNEYSTONE.get()
        )

        chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_CHIMNEYSTONE.get(),
            Ingredient.of(HAItems.POLISHED_CHIMNEYSTONE.get())
        )
        //#endregion

        //#region chimneystone stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_STAIRS.get(),
            HAItems.CHIMNEYSTONE.get()
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_CHIMNEYSTONE_STAIRS.get(),
            HAItems.POLISHED_CHIMNEYSTONE.get()
        )

        stair(
            HAItems.CHIMNEYSTONE_STAIRS.get(),
            HABlocks.CHIMNEYSTONE.get()
        )

        stair(
            HAItems.POLISHED_CHIMNEYSTONE_STAIRS.get(),
            HABlocks.POLISHED_CHIMNEYSTONE.get()
        )
        //#endregion

        //#region chimneystone slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_SLAB.get(),
            HAItems.CHIMNEYSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.POLISHED_CHIMNEYSTONE_SLAB.get(),
            HAItems.POLISHED_CHIMNEYSTONE.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.CHIMNEYSTONE_SLAB.get(),
            HABlocks.CHIMNEYSTONE.get(),
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.POLISHED_CHIMNEYSTONE_SLAB.get(),
            HABlocks.POLISHED_CHIMNEYSTONE.get(),
        )
        //#endregion

        //#region chimneystone walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_WALL.get(),
            HAItems.CHIMNEYSTONE.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.CHIMNEYSTONE_WALL.get(),
            HABlocks.CHIMNEYSTONE.get()
        )
        //#endregion

        //#region chimneystone bricks
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_BRICKS.get(),
            HAItems.POLISHED_CHIMNEYSTONE.get()
        )

        polished(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_BRICKS.get(),
            HAItems.POLISHED_CHIMNEYSTONE.get()
        )
        //#endregion

        //#region chimneystone brick stairs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_BRICK_STAIRS.get(),
            HAItems.CHIMNEYSTONE_BRICKS.get()
        )

        stair(
            HAItems.CHIMNEYSTONE_BRICK_STAIRS.get(),
            HABlocks.CHIMNEYSTONE_BRICKS.get()
        )
        //#endregion

        //#region chimneystone brick slabs
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_BRICK_SLAB.get(),
            HAItems.CHIMNEYSTONE_BRICKS.get(),
            2
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.CHIMNEYSTONE_BRICK_SLAB.get(),
            HABlocks.CHIMNEYSTONE_BRICKS.get(),
        )
        //#endregion

        //#region chimneystone brick walls
        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHIMNEYSTONE_BRICK_WALL.get(),
            HAItems.CHIMNEYSTONE_BRICKS.get()
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.CHIMNEYSTONE_BRICK_WALL.get(),
            HABlocks.CHIMNEYSTONE_BRICKS.get()
        )
        //#endregion

        // misc recipes
        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.CHIMNEYSTONE.get(), 8)
            .pattern("BBB")
            .pattern("BSB")
            .pattern("BBB")
            .define('S', HAItems.SULFUR.get())
            .define('B', Items.DRIPSTONE_BLOCK)
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.RED_BRINESTONE.get(), 8)
            .pattern("TTT")
            .pattern("TBT")
            .pattern("TTT")
            .define('B', HAPlatformItems.BRINE_BUCKET.get())
            .define('T', Items.RED_TERRACOTTA)
            .unlockedBy(
                "has_brine_bucket",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformItems.BRINE_BUCKET.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.ORANGE_BRINESTONE.get(), 8)
            .pattern("TTT")
            .pattern("TBT")
            .pattern("TTT")
            .define('B', HAPlatformItems.BRINE_BUCKET.get())
            .define('T', Items.ORANGE_TERRACOTTA)
            .unlockedBy(
                "has_brine_bucket",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformItems.BRINE_BUCKET.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.YELLOW_BRINESTONE.get(), 8)
            .pattern("TTT")
            .pattern("TBT")
            .pattern("TTT")
            .define('B', HAPlatformItems.BRINE_BUCKET.get())
            .define('T', Items.YELLOW_TERRACOTTA)
            .unlockedBy(
                "has_brine_bucket",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformItems.BRINE_BUCKET.get())
            )
            .save(output)

        shaped(RecipeCategory.TRANSPORTATION, HAItems.ARGONAUT.get())
            .pattern("IBF")
            .pattern("GSC")
            .pattern("IBW")
            .define('I', Items.IRON_INGOT)
            .define('B', Items.BARREL)
            .define('G', HAItems.GLOWSLIME.get())
            .define('S', HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get())
            .define('C', Items.CHEST)
            .define('F', Items.FURNACE)
            .define('W', ItemTags.WOOL)
            .unlockedBy(
                "has_giant_nautilus_shell",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.DIVING_WEIGHT.get())
            .pattern("III")
            .pattern("I I")
            .pattern("IBI")
            .define('I', Items.IRON_INGOT)
            .define('B', Items.IRON_BLOCK)
            .unlockedBy(
                "has_iron_ingot",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT)
            )
            .save(output)

        shaped(RecipeCategory.MISC, HAItems.PRISMARINE_ROD.get())
            .pattern("P  ")
            .pattern("P  ")
            .pattern("   ")
            .define('P', Items.PRISMARINE_SHARD)
            .unlockedBy(
                "has_prismarine",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.PRISMARINE_SHARD)
            )
            .save(output)

        shaped(RecipeCategory.MISC, HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(), 2)
            .pattern("PPP")
            .pattern("PUP")
            .pattern("PCP")
            .define('P', Items.PRISMARINE_SHARD)
            .define('U', HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get())
            .define('C', Items.PRISMARINE_CRYSTALS)
            .unlockedBy(
                "has_diving_template",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get())
            )
            .save(output)

        shaped(RecipeCategory.REDSTONE, HAItems.DEPTH_CHARGE.get())
            .pattern("SIS")
            .pattern("ISI")
            .pattern("SIS")
            .define('S', HAItems.SULFUR.get())
            .define('I', Items.IRON_NUGGET)
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.AERATED_SAND.get())
            .pattern("SSS")
            .pattern("SPS")
            .pattern("SSS")
            .define('P', HAItems.PEARL.get())
            .define('S', Items.SAND)
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "aerated_sand_from_pearl")))

        shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.AERATED_SAND.get(),
            1
        )
            .requires(HAItems.BUBBLE_GEYSER.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "aerated_sand_from_bubble_geyser")))

        shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BUBBLE_GEYSER.get(),
            1
        )
            .requires(HAItems.AERATED_SAND.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "bubble_geyser_from_aerated_sand")))

        shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', HAItems.TUBE_SPONGE.get())
            .unlockedBy(
                "has_tube_sponge",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.TUBE_SPONGE.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HAItems.PEARL.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(output)

        shapeless(
            RecipeCategory.MISC,
            HAItems.PEARL.get(),
            9
        )
            .requires(HAItems.PEARL_BLOCK.get())
            .unlockedBy(
                "has_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL_BLOCK.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BLACK_PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HAItems.BLACK_PEARL.get())
            .unlockedBy(
                "has_black_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL.get())
            )
            .save(output)

        shapeless(
            RecipeCategory.MISC,
            HAItems.BLACK_PEARL.get(),
            9
        )
            .requires(HAItems.BLACK_PEARL_BLOCK.get())
            .unlockedBy(
                "has_black_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL_BLOCK.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.CRYSTALLINE_SULFUR.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', HAItems.SULFUR.get())
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(output)

        shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SULFUR.get(),
            9
        )
            .requires(HAItems.CRYSTALLINE_SULFUR.get())
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAPlatformItems.GLOWSLIME_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', HAItems.GLOWSLIME.get())
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME.get())
            )
            .save(output)

        shapeless(
            RecipeCategory.MISC,
            HAItems.GLOWSLIME.get(),
            9
        )
            .requires(HAPlatformItems.GLOWSLIME_BLOCK.get())
            .unlockedBy(
                "has_glowslime_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformItems.GLOWSLIME_BLOCK.get())
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowslime_from_block")))

        shaped(RecipeCategory.BUILDING_BLOCKS, HAPlatformItems.HAGSLIME_BLOCK.get())
            .pattern("HHH")
            .pattern("HHH")
            .pattern("HHH")
            .define('H', HAItems.HAGSLIME.get())
            .unlockedBy(
                "has_hagslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.HAGSLIME.get())
            )
            .save(output)

        shapeless(
            RecipeCategory.MISC,
            HAItems.HAGSLIME.get(),
            9
        )
            .requires(HAPlatformItems.HAGSLIME_BLOCK.get())
            .unlockedBy(
                "has_hagslime_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformItems.HAGSLIME_BLOCK.get())
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "hagslime_from_block")))

        shaped(RecipeCategory.TOOLS, HAItems.CORAL_BLADE.get(), 1)
            .pattern(" C ")
            .pattern(" C ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, Items.TRIDENT, 1)
            .pattern("SSS")
            .pattern(" R ")
            .pattern(" R ")
            .define('S', HAItems.SHARK_TOOTH.get())
            .define('R', HAItems.PRISMARINE_ROD.get())
            .unlockedBy(
                "has_shark_tooth",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SHARK_TOOTH.get())
            )
            .unlockedBy(
                "has_prismarine_rod",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PRISMARINE_ROD.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.CORAL_PICKAXE.get(), 1)
            .pattern("CCC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.CORAL_AXE.get(), 1)
            .pattern(" CC")
            .pattern(" SC")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.CORAL_SHOVEL.get(), 1)
            .pattern(" C ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.CORAL_HOE.get(), 1)
            .pattern(" CC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_SPEAR.get(), 1)
            .pattern(" N ")
            .pattern(" N ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_PICKAXE.get(), 1)
            .pattern("NNN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_AXE.get(), 1)
            .pattern(" NN")
            .pattern(" SN")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_SHOVEL.get(), 1)
            .pattern(" N ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_HOE.get(), 1)
            .pattern(" NN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.NAUTILUS_HELMET.get(), 1)
            .pattern("NNN")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.NAUTILUS_PAULDRONS.get(), 1)
            .pattern("N N")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(output)

        //#regione Bone Set Recipes
        stair(
            HAItems.BONE_STAIRS.get(),
            Blocks.BONE_BLOCK
        )

        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.BONE_SLAB.get(),
            Blocks.BONE_BLOCK
        )

        wall(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.BONE_WALL.get(),
            Blocks.BONE_BLOCK
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_STAIRS.get(),
            Blocks.BONE_BLOCK,
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_SLAB.get(),
            Blocks.BONE_BLOCK,
            2
        )

        stonecutterResultFromBase(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_WALL.get(),
            Blocks.BONE_BLOCK
        )

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BONE_FENCE.get(), 3)
            .pattern("BIB")
            .pattern("BIB")
            .pattern("   ")
            .define('I', Items.BONE)
            .define('B', Items.BONE_BLOCK)
            .unlockedBy("has_bone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE))
            .save(output)
        //#endregion

        //#region Wood Recipes
        offerRaftRecipes(raftTypeMap)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.RAFT.get(), 2)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', Items.STICK)
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BUOY.get(), 2)
            .pattern(" L ")
            .pattern(" S ")
            .pattern(" W ")
            .define('S', Items.STICK)
            .define('L', Items.LANTERN)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_lantern", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LANTERN))
            .save(output)

        shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BELL_BUOY.get(), 2)
            .pattern(" G ")
            .pattern(" S ")
            .pattern(" W ")
            .define('S', Items.STICK)
            .define('G', Items.GOLD_INGOT)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
            .save(output)

        trapdoor(
            HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get(),
            HAPlatformItems.DRIFTWOOD_PLANKS.get(),
        )

        door(
            HAPlatformBlocks.DRIFTWOOD_DOOR.get(),
            HAPlatformItems.DRIFTWOOD_PLANKS.get(),
        )

        stair(
            HAPlatformBlocks.DRIFTWOOD_STAIRS.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )
        
        slab(
            RecipeCategory.BUILDING_BLOCKS,
            HAPlatformBlocks.DRIFTWOOD_SLAB.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        fence(
            HAPlatformBlocks.DRIFTWOOD_FENCE.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        fenceGate(
            HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        woodFromLogs(
            HAPlatformBlocks.DRIFTWOOD_WOOD.get(),
            HAPlatformBlocks.DRIFTWOOD_LOG.get()
        )

        planksFromLog(
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get(),
            HAItemTags.DRIFTWOOD_LOG_WOOD,
            4
        )

        pressurePlate(
            HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAPlatformBlocks.DRIFTWOOD_BUTTON.get(),
            1
        )
            .requires(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
            .unlockedBy(
                "has_driftwood_planks",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
            )
            .save(output)
        //#endregion

        //#region Armor
        shaped(RecipeCategory.COMBAT, HAItems.TURTLE_CHESTPLATE.get())
            .pattern("S S")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', Items.TURTLE_SCUTE)
            .unlockedBy("has_scute", InventoryChangeTrigger.TriggerInstance.hasItems(Items.TURTLE_SCUTE))
            .save(output)

        shaped(RecipeCategory.COMBAT, HAItems.DIVING_HELMET.get())
            .pattern("CCC")
            .pattern("CGC")
            .pattern("CCC")
            .define('C', Items.COPPER_INGOT)
            .define('G', Items.GLASS_PANE)
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .unlockedBy("has_glass_pane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS_PANE))
            .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
            .save(output)

        shaped(RecipeCategory.COMBAT, HAItems.DIVING_SUIT.get())
            .pattern("C C")
            .pattern("LCL")
            .pattern("LLL")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(output)

        shaped(RecipeCategory.COMBAT, HAItems.DIVING_LEGGINGS.get())
            .pattern("CCC")
            .pattern("L L")
            .pattern("L L")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(output)

        shaped(RecipeCategory.COMBAT, HAItems.DIVING_BOOTS.get())
            .pattern("C C")
            .pattern("C C")
            .pattern("   ")
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(output)

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_HELMET.get()),
            Ingredient.of(HAItems.BLACK_PEARL.get()),
            RecipeCategory.COMBAT,
            HAItems.REINFORCED_DIVING_HELMET.get()
        )
            .unlocks(
                "has_diving_helmet",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_HELMET.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "reinforced_diving_helmet_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_SUIT.get()),
            Ingredient.of(HAItems.BLACK_PEARL.get()),
            RecipeCategory.COMBAT,
            HAItems.REINFORCED_DIVING_SUIT.get()
        )
            .unlocks(
                "has_diving_suit",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_SUIT.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "reinforced_diving_suit_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_LEGGINGS.get()),
            Ingredient.of(HAItems.BLACK_PEARL.get()),
            RecipeCategory.COMBAT,
            HAItems.REINFORCED_DIVING_LEGGINGS.get()
        )
            .unlocks(
                "has_diving_leggings",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_LEGGINGS.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "reinforced_diving_leggings_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_BOOTS.get()),
            Ingredient.of(HAItems.BLACK_PEARL.get()),
            RecipeCategory.COMBAT,
            HAItems.REINFORCED_DIVING_BOOTS.get()
        )
            .unlocks(
                "has_diving_boots",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_BOOTS.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "reinforced_diving_boots_upgrade")))


        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_HELMET.get()),
            Ingredient.of(HAItems.GLOWSLIME.get()),
            RecipeCategory.COMBAT,
            HAItems.GLOWING_DIVING_HELMET.get()
        )
            .unlocks(
                "has_diving_helmet",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_HELMET.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowing_diving_helmet_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_SUIT.get()),
            Ingredient.of(HAItems.GLOWSLIME.get()),
            RecipeCategory.COMBAT,
            HAItems.GLOWING_DIVING_SUIT.get()
        )
            .unlocks(
                "has_diving_suit",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_SUIT.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowing_diving_suit_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_LEGGINGS.get()),
            Ingredient.of(HAItems.GLOWSLIME.get()),
            RecipeCategory.COMBAT,
            HAItems.GLOWING_DIVING_LEGGINGS.get()
        )
            .unlocks(
                "has_diving_leggings",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_LEGGINGS.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowing_diving_leggings_upgrade")))

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()),
            Ingredient.of(HAItems.DIVING_BOOTS.get()),
            Ingredient.of(HAItems.GLOWSLIME.get()),
            RecipeCategory.COMBAT,
            HAItems.GLOWING_DIVING_BOOTS.get()
        )
            .unlocks(
                "has_diving_boots",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    HAItems.DIVING_BOOTS.get()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowing_diving_boots_upgrade")))

        //#endregion

        //#region Fishing Lures
        shaped(RecipeCategory.TOOLS, HAItems.BARBED_HOOK.get())
            .pattern("N  ")
            .pattern("N N")
            .pattern("NNN")
            .define('N', Items.IRON_NUGGET)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(output)

        shapeless(RecipeCategory.TOOLS, HAItems.GLOWING_HOOK.get())
            .requires(HAItems.BARBED_HOOK.get())
            .requires(HAItems.GLOWSLIME.get())
            .unlockedBy(
                "has_barbed_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BARBED_HOOK.get())
            )
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME.get())
            )
            .save(output)

        shaped(RecipeCategory.TOOLS, HAItems.MAGNETIC_HOOK.get())
            .pattern("NIN")
            .pattern("N N")
            .pattern("I I")
            .define('N', Items.IRON_NUGGET)
            .define('I', Items.IRON_INGOT)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(output)

        shapeless(RecipeCategory.MISC, HAItems.CREEPERMAGNET_HOOK.get())
            .requires(HAItems.MAGNETIC_HOOK.get())
            .requires(Items.GUNPOWDER)
            .unlockedBy(
                "has_magnetic_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.MAGNETIC_HOOK.get())
            )
            .save(output)

        shapeless(RecipeCategory.TOOLS, HAItems.OMINOUS_HOOK.get())
            .requires(HAItems.COCONUT_CRAB_CLAW.get())
            .requires(HAItems.YETI_CRAB_CLAW.get())
            .requires(HAItems.GHOST_CRAB_CLAW.get())
            .requires(HAItems.FLOWER_CRAB_CLAW.get())
            .requires(HAItems.SPIDER_CRAB_CLAW.get())
            .requires(HAItems.FIDDLER_CRAB_CLAW.get())
            .requires(HAItems.VAMPIRE_CRAB_CLAW.get())
            .requires(HAItems.DUNGENESS_CRAB_CLAW.get())
            .requires(HAItems.LIGHTFOOT_CRAB_CLAW.get())
            .unlockedBy(
                "has_crab_claw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(itemLookup, HAItemTags.CRAB_CLAW).build()
                )
            )
            .save(output)
        //#endregion

        shapeless(RecipeCategory.MISC, HAItems.GLOWSLIME.get())
            .requires(Items.SLIME_BALL)
            .requires(Items.GLOW_INK_SAC)
            .unlockedBy("has_slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
            .unlockedBy("has_glow_ink_sac", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOW_INK_SAC))
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowslime_from_slime")))

        shapeless(RecipeCategory.BUILDING_BLOCKS, HAItems.GLOWSTICK.get(), 4)
            .requires(Items.STICK)
            .requires(HAItems.GLOWSLIME.get())
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME.get())
            )
            .save(output)

        shapeless(RecipeCategory.MISC, Items.BONE_MEAL)
            .requires(HAItems.CUTTLEBONE.get())
            .unlockedBy(
                "has_cuttlebone",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CUTTLEBONE.get())
            )
            .save(output)

        shapeless(RecipeCategory.MISC, HAItems.FISH_FOOD.get())
            .requires(ItemTags.FISHES)
            .requires(Items.WHEAT)
            .unlockedBy(
                "has_wheat",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT)
            )
            .save(output)

        shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2)
            .requires(HAItems.SULFUR.get())
            .requires(Items.COAL)
            .requires(Items.BONE_MEAL)
            .requires(Items.BONE_MEAL)
            .unlockedBy("has_sulfur", InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get()))
            .save(output)

        //#region Foodstuffs
        shapeless(RecipeCategory.FOOD, HAItems.RAW_CRAB.get(), 1)
            .requires(HAItemTags.CRAB_CLAW)
            .unlockedBy(
                "has_crab_claw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(itemLookup, HAItemTags.CRAB_CLAW).build()
                )
            )
            .save(output)

        shapeless(RecipeCategory.FOOD, HAItems.RAW_LOBSTER.get(), 1)
            .requires(HAItems.LOBSTER_CLAW.get())
            .unlockedBy(
                "has_lobster_claw",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.LOBSTER_CLAW.get())
            )
            .save(output)

        shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_MEAT.get(), 1)
            .requires(HAItemTags.SMALL_FISH)
            .unlockedBy(
                "has_small_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(itemLookup, HAItemTags.SMALL_FISH).build()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "raw_fish_meat_small")))

        shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_MEAT.get(), 2)
            .requires(HAItemTags.MEDIUM_FISH)
            .unlockedBy(
                "has_medium_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(itemLookup, HAItemTags.MEDIUM_FISH).build()
                )
            )
            .save(output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("hybrid_aquatic", "raw_fish_meat_medium")))

        shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_STEAK.get(), 2)
            .requires(HAItemTags.LARGE_FISH)
            .unlockedBy(
                "has_large_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(itemLookup, HAItemTags.LARGE_FISH).build()
                )
            )
            .save(output)
        //#endregion

        //#region Cooking Recipes
        offerCookingRecipes(
            HAItems.RAW_CRAB.get(),
            HAItems.COOKED_CRAB.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.RAW_SHRIMP.get(),
            HAItems.COOKED_SHRIMP.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.RAW_CRAYFISH.get(),
            HAItems.COOKED_CRAYFISH.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.RAW_LOBSTER.get(),
            HAItems.COOKED_LOBSTER.get(),
            0.3f
        )

        offerCookingRecipes(
            HAItems.RAW_LOBSTER_TAIL.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
            0.3f
        )

        offerCookingRecipes(
            HAItems.RAW_FISH_STEAK.get(),
            HAItems.COOKED_FISH_STEAK.get(),
            0.3f
        )

        offerCookingRecipes(
            HAItems.SIRENIAN_BEEF.get(),
            HAItems.SIRENIAN_STEAK.get(),
            0.3f
        )

        offerCookingRecipes(
            HAItems.RAW_FISH_MEAT.get(),
            HAItems.COOKED_FISH_MEAT.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.RAW_TENTACLE.get(),
            HAItems.COOKED_TENTACLE.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.CLAM.get(),
            HAItems.COOKED_CLAM.get(),
            0.15f
        )

        offerCookingRecipes(
            HAItems.MUSSEL.get(),
            HAItems.COOKED_MUSSEL.get(),
            0.15f
        )

        offerKelpCookingRecipes(HAItemTags.KELPS, Items.DRIED_KELP, 0.15f)
        //#endregion

        offerBleachingRecipes(bleachedCoralBlockMap)
        offerBleachingRecipes(bleachedCoralMap)
        offerBleachingRecipes(bleachedCoralFanMap)
    }

    private fun offerCookingRecipes(
        input: Item,
        result: Item,
        experience: Float,
    ) {
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(input), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, 200
        ).unlockedBy(getHasName(input), has(input))
            .save(output, getItemName(result) + "_from_smelting")
        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(input), RecipeCategory.FOOD, result, experience, 100
        ).unlockedBy(getHasName(input), has(input))
            .save(output, getItemName(result) + "_from_smoking")
        SimpleCookingRecipeBuilder.campfireCooking(
            Ingredient.of(input), RecipeCategory.FOOD, result, experience, 600
        ).unlockedBy(getHasName(input), has(input))
            .save(output, getItemName(result) + "_from_campfire_cooking")
    }

    //#region Kelp Cooking Recipes
    private fun offerKelpCookingRecipes(
        inputTag: TagKey<Item>,
        result: Item,
        experience: Float,
    ) {
        val ingredient = Ingredient.of(itemLookup.getOrThrow(inputTag))
        SimpleCookingRecipeBuilder.smelting(
            ingredient, RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, 200
        ).unlockedBy("has_kelp", has(inputTag))
            .save(output, getItemName(result) + "_from_smelting")
        SimpleCookingRecipeBuilder.smoking(
            ingredient, RecipeCategory.FOOD, result, experience, 100
        ).unlockedBy("has_kelp", has(inputTag))
            .save(output, getItemName(result) + "_from_smoking")
        SimpleCookingRecipeBuilder.campfireCooking(
            ingredient, RecipeCategory.FOOD, result, experience, 600
        ).unlockedBy("has_kelp", has(inputTag))
            .save(output, getItemName(result) + "_from_campfire_cooking")
    }
    //#endregion

    //#region Wooden Raft Maps
    private val raftTypeMap = mapOf(
        HAPlatformBlocks.DRIFTWOOD_PLANKS.get() to HABlocks.DRIFTWOOD_RAFT.get(),
        Blocks.OAK_PLANKS to HABlocks.OAK_RAFT.get(),
        Blocks.SPRUCE_PLANKS to HABlocks.SPRUCE_RAFT.get(),
        Blocks.BIRCH_PLANKS to HABlocks.BIRCH_RAFT.get(),
        Blocks.DARK_OAK_PLANKS to HABlocks.DARK_OAK_RAFT.get(),
        Blocks.CHERRY_PLANKS to HABlocks.CHERRY_RAFT.get(),
        Blocks.MANGROVE_PLANKS to HABlocks.MANGROVE_RAFT.get(),
        Blocks.ACACIA_PLANKS to HABlocks.ACACIA_RAFT.get(),
        Blocks.JUNGLE_PLANKS to HABlocks.JUNGLE_RAFT.get()
    )

    private fun offerRaftRecipes(
        map: Map<Block, Block>,
    ) {
        for ((woodType, raftType) in map) {
            shaped(RecipeCategory.BUILDING_BLOCKS, raftType, 2)
                .pattern("SS ")
                .pattern("SW ")
                .pattern("   ")
                .define('W', woodType)
                .define('S', Items.STICK)
                .unlockedBy(
                    "has_${getItemName(woodType.asItem())}",
                    has(woodType.asItem())
                )
                .save(
                    output,
                    "${getItemName(raftType.asItem())}_from_${getItemName(woodType.asItem())}"
                )
        }
    }
    //#endregion

    //#region Bleached Coral Maps
    private val bleachedCoralBlockMap = mapOf(
        Blocks.DEAD_FIRE_CORAL_BLOCK to HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get(),
        Blocks.DEAD_TUBE_CORAL_BLOCK to HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get(),
        Blocks.DEAD_HORN_CORAL_BLOCK to HABlocks.BLEACHED_HORN_CORAL_BLOCK.get(),
        Blocks.DEAD_BRAIN_CORAL_BLOCK to HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get(),
        Blocks.DEAD_BUBBLE_CORAL_BLOCK to HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get(),

        HABlocks.DEAD_ROSE_CORAL_BLOCK.get() to HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get(),
        HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get() to HABlocks.BLEACHED_BAMBOO_CORAL_BLOCK.get(),
        HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get() to HABlocks.BLEACHED_ZIGZAG_CORAL_BLOCK.get(),
        HABlocks.DEAD_LEAF_CORAL_BLOCK.get() to HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get(),
        HABlocks.DEAD_THORN_CORAL_BLOCK.get() to HABlocks.BLEACHED_THORN_CORAL_BLOCK.get(),
        HABlocks.DEAD_BUTTON_CORAL_BLOCK.get() to HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get(),
        HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get() to HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get(),
        HABlocks.DEAD_SUN_CORAL_BLOCK.get() to HABlocks.BLEACHED_SUN_CORAL_BLOCK.get(),
    )

    private val bleachedCoralMap = mapOf(
        Blocks.DEAD_FIRE_CORAL to HABlocks.BLEACHED_FIRE_CORAL.get(),
        Blocks.DEAD_TUBE_CORAL to HABlocks.BLEACHED_TUBE_CORAL.get(),
        Blocks.DEAD_HORN_CORAL to HABlocks.BLEACHED_HORN_CORAL.get(),
        Blocks.DEAD_BRAIN_CORAL to HABlocks.BLEACHED_BRAIN_CORAL.get(),
        Blocks.DEAD_BUBBLE_CORAL to HABlocks.BLEACHED_BUBBLE_CORAL.get(),

        HABlocks.DEAD_ROSE_CORAL.get() to HABlocks.BLEACHED_ROSE_CORAL.get(),
        HABlocks.DEAD_BAMBOO_CORAL.get() to HABlocks.BLEACHED_BAMBOO_CORAL.get(),
        HABlocks.DEAD_ZIGZAG_CORAL.get() to HABlocks.BLEACHED_ZIGZAG_CORAL.get(),
        HABlocks.DEAD_LEAF_CORAL.get() to HABlocks.BLEACHED_LEAF_CORAL.get(),
        HABlocks.DEAD_THORN_CORAL.get() to HABlocks.BLEACHED_THORN_CORAL.get(),
        HABlocks.DEAD_BUTTON_CORAL.get() to HABlocks.BLEACHED_BUTTON_CORAL.get(),
        HABlocks.DEAD_LOPHELIA_CORAL.get() to HABlocks.BLEACHED_LOPHELIA_CORAL.get(),
        HABlocks.DEAD_SUN_CORAL.get() to HABlocks.BLEACHED_SUN_CORAL.get(),
    )

    private val bleachedCoralFanMap = mapOf(
        Blocks.DEAD_FIRE_CORAL_FAN to HABlocks.BLEACHED_FIRE_CORAL_FAN.get(),
        Blocks.DEAD_TUBE_CORAL_FAN to HABlocks.BLEACHED_TUBE_CORAL_FAN.get(),
        Blocks.DEAD_HORN_CORAL_FAN to HABlocks.BLEACHED_HORN_CORAL_FAN.get(),
        Blocks.DEAD_BRAIN_CORAL_FAN to HABlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
        Blocks.DEAD_BUBBLE_CORAL_FAN to HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),

        HABlocks.DEAD_ROSE_CORAL_FAN.get() to HABlocks.BLEACHED_ROSE_CORAL_FAN.get(),
        HABlocks.DEAD_BAMBOO_CORAL_FAN.get() to HABlocks.BLEACHED_BAMBOO_CORAL_FAN.get(),
        HABlocks.DEAD_ZIGZAG_CORAL_FAN.get() to HABlocks.BLEACHED_ZIGZAG_CORAL_FAN.get(),
        HABlocks.DEAD_LEAF_CORAL_FAN.get() to HABlocks.BLEACHED_LEAF_CORAL_FAN.get(),
        HABlocks.DEAD_THORN_CORAL_FAN.get() to HABlocks.BLEACHED_THORN_CORAL_FAN.get(),
        HABlocks.DEAD_BUTTON_CORAL_FAN.get() to HABlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
        HABlocks.DEAD_LOPHELIA_CORAL_FAN.get() to HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
        HABlocks.DEAD_SUN_CORAL_FAN.get() to HABlocks.BLEACHED_SUN_CORAL_FAN.get(),
    )

    private fun offerBleachingRecipes(
        map: Map<Block, Block>,
    ) {
        for ((deadCoral, bleachedCoral) in map) {
            shaped(RecipeCategory.BUILDING_BLOCKS, bleachedCoral, 8)
                .pattern("CCC")
                .pattern("CWC")
                .pattern("CCC")
                .define('C', deadCoral)
                .define('W', Items.WHITE_DYE)
                .unlockedBy(
                    "has_${getItemName(deadCoral.asItem())}",
                    has(deadCoral.asItem())
                )
                .save(
                    output,
                    "${getItemName(bleachedCoral.asItem())}_from_${getItemName(deadCoral.asItem())}"
                )
        }
    }
    //#endregion

    private fun door(result: ItemLike, material: ItemLike) {
        doorBuilder(result, Ingredient.of(material)).unlockedBy(getHasName(material), has(material))
            .save(output)
    }

    private fun trapdoor(result: ItemLike, material: ItemLike) {
        trapdoorBuilder(result, Ingredient.of(material)).unlockedBy(getHasName(material), has(material))
            .save(output)
    }

    private fun stair(result: ItemLike, material: ItemLike) {
        stairBuilder(result, Ingredient.of(material)).unlockedBy(getHasName(material), has(material))
            .save(output)
    }

    private fun fence(result: ItemLike, material: ItemLike) {
        val count = if (result == Blocks.NETHER_BRICK_FENCE) 6 else 3
        val stick = if (result == Blocks.NETHER_BRICK_FENCE) Items.NETHER_BRICK else Items.STICK
        shaped(RecipeCategory.DECORATIONS, result, count)
            .define('W', material)
            .define('#', stick)
            .pattern("W#W")
            .pattern("W#W")
            .unlockedBy(getHasName(material), has(material))
            .save(output)
    }

    private fun fenceGate(result: ItemLike, material: ItemLike) {
        shaped(RecipeCategory.REDSTONE, result)
            .define('#', Items.STICK)
            .define('W', material)
            .pattern("#W#")
            .pattern("#W#")
            .unlockedBy(getHasName(material), has(material))
            .save(output)
    }
}
}
