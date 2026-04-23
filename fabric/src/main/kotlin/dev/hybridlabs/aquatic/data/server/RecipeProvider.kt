package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.AbstractCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun buildRecipes(exporter: Consumer<FinishedRecipe>) {

        //#region Sandstone Block Recipes
        stairBuilder(
            HAItems.WHITE_SANDSTONE_STAIRS.get(),
            Ingredient.of(HAItems.WHITE_SANDSTONE.get())
        )

        stairBuilder(
            HAItems.SMOOTH_WHITE_SANDSTONE_STAIRS.get(),
            Ingredient.of(HAItems.SMOOTH_WHITE_SANDSTONE.get())
        )

        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_SLAB.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE_SLAB.get(),
            HAItems.CUT_WHITE_SANDSTONE.get()
        )

        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_SLAB.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get()
        )

        chiseled(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_SLAB.get(),
            HAItems.WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE_SLAB.get(),
            HAItems.CUT_WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_SLAB.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get(),
            2
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_STAIRS.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE_STAIRS.get(),
            HAItems.SMOOTH_WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.WHITE_SANDSTONE_WALL.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CHISELED_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SMOOTH_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.CUT_WHITE_SANDSTONE.get(),
            HAItems.WHITE_SANDSTONE.get()
        )
        //#endregion

        // misc recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, HAItems.ARGONAUT.get())
            .pattern("IBF")
            .pattern("GSC")
            .pattern("IBW")
            .define('I', Items.IRON_INGOT)
            .define('B', Items.BARREL)
            .define('G', HAItems.GLOWSLIME.get())
            .define('S', HAItems.GIANT_NAUTILUS_SHELL.get())
            .define('C', Items.CHEST)
            .define('F', Items.FURNACE)
            .define('W', ItemTags.WOOL)
            .unlockedBy(
                "has_giant_nautilus_shell",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GIANT_NAUTILUS_SHELL.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.DIVING_WEIGHT.get())
            .pattern("III")
            .pattern("I I")
            .pattern("IBI")
            .define('I', Items.IRON_INGOT)
            .define('B', Items.IRON_BLOCK)
            .unlockedBy(
                "has_iron_ingot",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT)
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HAItems.PRISMARINE_ROD.get())
            .pattern("P  ")
            .pattern("P  ")
            .pattern("   ")
            .define('P', Items.PRISMARINE_SHARD)
            .unlockedBy(
                "has_prismarine",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.PRISMARINE_SHARD)
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(), 2)
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
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, HAItems.DEPTH_CHARGE.get())
            .pattern("SIS")
            .pattern("ISI")
            .pattern("SIS")
            .define('S', HAItems.SULFUR.get())
            .define('I', Items.IRON_NUGGET)
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.AERATED_SAND.get())
            .pattern("SSS")
            .pattern("SPS")
            .pattern("SSS")
            .define('P', HAItems.PEARL.get())
            .define('S', Items.SAND)
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "aerated_sand_from_pearl"))

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.AERATED_SAND.get(),
            1
        )
            .requires(HAItems.BUBBLE_GEYSER.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "aerated_sand_from_bubble_geyser"))

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BUBBLE_GEYSER.get(),
            1
        )
            .requires(HAItems.AERATED_SAND.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "bubble_geyser_from_aerated_sand"))

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', HAItems.TUBE_SPONGE.get())
            .unlockedBy(
                "has_tube_sponge",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.TUBE_SPONGE.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HAItems.PEARL.get())
            .unlockedBy(
                "has_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HAItems.PEARL.get(),
            9
        )
            .requires(HAItems.PEARL_BLOCK.get())
            .unlockedBy(
                "has_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.PEARL_BLOCK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BLACK_PEARL_BLOCK.get())
            .pattern("PPP")
            .pattern("PPP")
            .pattern("PPP")
            .define('P', HAItems.BLACK_PEARL.get())
            .unlockedBy(
                "has_black_pearl",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HAItems.BLACK_PEARL.get(),
            9
        )
            .requires(HAItems.BLACK_PEARL_BLOCK.get())
            .unlockedBy(
                "has_black_pearl_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.BLACK_PEARL_BLOCK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.CRYSTALLINE_SULFUR.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', HAItems.SULFUR.get())
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.SULFUR.get(),
            9
        )
            .requires(HAItems.CRYSTALLINE_SULFUR.get())
            .unlockedBy(
                "has_sulfur",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.GLOWSLIME_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', HAItems.GLOWSLIME.get())
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HAItems.GLOWSLIME.get(),
            9
        )
            .requires(HAItems.GLOWSLIME_BLOCK.get())
            .unlockedBy(
                "has_glowslime_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME_BLOCK.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowslime_from_block"))

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.HAGSLIME_BLOCK.get())
            .pattern("HHH")
            .pattern("HHH")
            .pattern("HHH")
            .define('H', HAItems.HAGSLIME.get())
            .unlockedBy(
                "has_hagslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.HAGSLIME.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC,
            HAItems.HAGSLIME.get(),
            9
        )
            .requires(HAItems.HAGSLIME_BLOCK.get())
            .unlockedBy(
                "has_hagslime_block",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.HAGSLIME_BLOCK.get())
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "hagslime_from_block"))

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.CORAL_BLADE.get(), 1)
            .pattern(" C ")
            .pattern(" C ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.TRIDENT, 1)
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
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.CORAL_PICKAXE.get(), 1)
            .pattern("CCC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.CORAL_AXE.get(), 1)
            .pattern(" CC")
            .pattern(" SC")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.CORAL_SHOVEL.get(), 1)
            .pattern(" C ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.CORAL_HOE.get(), 1)
            .pattern(" CC")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('C', HAItems.CORAL_CHUNK.get())
            .unlockedBy(
                "has_coral_chunk",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CORAL_CHUNK.get())
            )
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_SPEAR.get(), 1)
            .pattern(" N ")
            .pattern(" N ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_PICKAXE.get(), 1)
            .pattern("NNN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_AXE.get(), 1)
            .pattern(" NN")
            .pattern(" SN")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_SHOVEL.get(), 1)
            .pattern(" N ")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.SEASHELL_HOE.get(), 1)
            .pattern(" NN")
            .pattern(" S ")
            .pattern(" S ")
            .define('S', Items.STICK)
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.NAUTILUS_HELMET.get(), 1)
            .pattern("NNN")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.NAUTILUS_PAULDRONS.get(), 1)
            .pattern("N N")
            .pattern("N N")
            .pattern("   ")
            .define('N', Items.NAUTILUS_SHELL)
            .unlockedBy("has_shell", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NAUTILUS_SHELL))
            .save(exporter)

        //#regione Bone Set Recipes
        stairBuilder(
            HAItems.BONE_STAIRS.get(),
            Ingredient.of(Blocks.BONE_BLOCK)
        )

        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.BONE_SLAB.get(),
            Blocks.BONE_BLOCK
        )

        wallBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            HABlocks.BONE_WALL.get(),
            Ingredient.of(Blocks.BONE_BLOCK),
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_STAIRS.get(),
            Blocks.BONE_BLOCK,
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_SLAB.get(),
            Blocks.BONE_BLOCK,
            2
        )

        stonecutterResultFromBase(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAItems.BONE_WALL.get(),
            Blocks.BONE_BLOCK
        )

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BONE_FENCE.get(), 3)
            .pattern("BIB")
            .pattern("BIB")
            .pattern("   ")
            .define('I', Items.BONE)
            .define('B', Items.BONE_BLOCK)
            .unlockedBy("has_bone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BONE))
            .save(exporter)
        //#endregion

        //#region Wood Recipes
        offerRaftRecipes(exporter, raftTypeMap)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.RAFT.get(), 2)
            .pattern("SS ")
            .pattern("SS ")
            .pattern("   ")
            .define('S', Items.STICK)
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BUOY.get(), 2)
            .pattern(" L ")
            .pattern(" S ")
            .pattern(" W ")
            .define('S', Items.STICK)
            .define('L', Items.LANTERN)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_lantern", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LANTERN))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HAItems.BELL_BUOY.get(), 2)
            .pattern(" G ")
            .pattern(" S ")
            .pattern(" W ")
            .define('S', Items.STICK)
            .define('G', Items.GOLD_INGOT)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
            .save(exporter)

        stairBuilder(
            HAPlatformBlocks.DRIFTWOOD_STAIRS.get(),
            Ingredient.of(HAPlatformBlocks.DRIFTWOOD_PLANKS.get()),
        )

        slab(
            exporter,
            RecipeCategory.BUILDING_BLOCKS,
            HAPlatformBlocks.DRIFTWOOD_SLAB.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        fenceBuilder(
            HAPlatformBlocks.DRIFTWOOD_FENCE.get(),
            Ingredient.of(HAPlatformBlocks.DRIFTWOOD_PLANKS.get()),
        )

        fenceGateBuilder(
            HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get(),
            Ingredient.of(HAPlatformBlocks.DRIFTWOOD_PLANKS.get()),
        )

        woodFromLogs(
            exporter,
            HAPlatformBlocks.DRIFTWOOD_WOOD.get(),
            HAPlatformBlocks.DRIFTWOOD_LOG.get()
        )

        planksFromLog(
            exporter,
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get(),
            HAItemTags.DRIFTWOOD_LOG_WOOD,
            4
        )

        pressurePlate(
            exporter,
            HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get(),
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get()
        )

        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS,
            HAPlatformBlocks.DRIFTWOOD_BUTTON.get(),
            1
        )
            .requires(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
            .unlockedBy(
                "has_driftwood_planks",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
            )
            .save(exporter)
        //#endregion

        //#region Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HAItems.TURTLE_CHESTPLATE.get())
            .pattern("S S")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', Items.SCUTE)
            .unlockedBy("has_scute", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SCUTE))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HAItems.DIVING_HELMET.get())
            .pattern("CCC")
            .pattern("CGC")
            .pattern("CCC")
            .define('C', Items.COPPER_INGOT)
            .define('G', Items.GLASS_PANE)
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .unlockedBy("has_glass_pane", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS_PANE))
            .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HAItems.DIVING_SUIT.get())
            .pattern("C C")
            .pattern("LCL")
            .pattern("LLL")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HAItems.DIVING_LEGGINGS.get())
            .pattern("CCC")
            .pattern("L L")
            .pattern("L L")
            .define('L', Items.LEATHER)
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HAItems.DIVING_BOOTS.get())
            .pattern("C C")
            .pattern("C C")
            .pattern("   ")
            .define('C', Items.COPPER_INGOT)
            .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
            .unlockedBy("has_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
            .save(exporter)

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "reinforced_diving_helmet_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "reinforced_diving_suit_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "reinforced_diving_leggings_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "reinforced_diving_boots_upgrade"))


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
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowing_diving_helmet_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowing_diving_suit_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowing_diving_leggings_upgrade"))

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
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowing_diving_boots_upgrade"))

        //#endregion

        //#region Fishing Lures
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.BARBED_HOOK.get())
            .pattern("N  ")
            .pattern("N N")
            .pattern("NNN")
            .define('N', Items.IRON_NUGGET)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, HAItems.GLOWING_HOOK.get())
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
            .save(exporter)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.MAGNETIC_HOOK.get())
            .pattern("NIN")
            .pattern("N N")
            .pattern("I I")
            .define('N', Items.IRON_NUGGET)
            .define('I', Items.IRON_INGOT)
            .unlockedBy("has_iron_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HAItems.CREEPERMAGNET_HOOK.get())
            .requires(HAItems.MAGNETIC_HOOK.get())
            .requires(Items.GUNPOWDER)
            .unlockedBy(
                "has_magnetic_hook",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.MAGNETIC_HOOK.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, HAItems.OMINOUS_HOOK.get())
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
                    ItemPredicate.Builder.item().of(HAItemTags.CRAB_CLAW).build()
                )
            )
            .save(exporter)
        //#endregion

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HAItems.FISHING_NET.get())
            .pattern("  S")
            .pattern(" IS")
            .pattern("I  ")
            .define('I', Items.STICK)
            .define('S', Items.STRING)
            .unlockedBy("string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HAItems.GLOWSLIME.get())
            .requires(Items.SLIME_BALL)
            .requires(Items.GLOW_INK_SAC)
            .unlockedBy("has_slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SLIME_BALL))
            .unlockedBy("has_glow_ink_sac", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLOW_INK_SAC))
            .save(exporter, ResourceLocation("hybrid-aquatic", "glowslime_from_slime"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HAItems.GLOWSTICK.get(), 4)
            .requires(Items.STICK)
            .requires(HAItems.GLOWSLIME.get())
            .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
            .unlockedBy(
                "has_glowslime",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.GLOWSLIME.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL)
            .requires(HAItems.CUTTLEBONE.get())
            .unlockedBy(
                "has_cuttlebone",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.CUTTLEBONE.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HAItems.FISH_FOOD.get())
            .requires(ItemTags.FISHES)
            .requires(Items.WHEAT)
            .unlockedBy(
                "has_wheat",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT)
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2)
            .requires(HAItems.SULFUR.get())
            .requires(Items.COAL)
            .requires(Items.BONE_MEAL)
            .requires(Items.BONE_MEAL)
            .unlockedBy("has_sulfur", InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.SULFUR.get()))
            .save(exporter)

        //#region Foodstuffs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HAItems.RAW_CRAB.get(), 1)
            .requires(HAItemTags.CRAB_CLAW)
            .unlockedBy(
                "has_crab_claw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.CRAB_CLAW).build()
                )
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HAItems.RAW_LOBSTER.get(), 1)
            .requires(HAItems.LOBSTER_CLAW.get())
            .unlockedBy(
                "has_lobster_claw",
                InventoryChangeTrigger.TriggerInstance.hasItems(HAItems.LOBSTER_CLAW.get())
            )
            .save(exporter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_MEAT.get(), 1)
            .requires(HAItemTags.SMALL_FISH)
            .unlockedBy(
                "has_small_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.SMALL_FISH).build()
                )
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "raw_fish_meat_small"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_MEAT.get(), 2)
            .requires(HAItemTags.MEDIUM_FISH)
            .unlockedBy(
                "has_medium_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.MEDIUM_FISH).build()
                )
            )
            .save(exporter, ResourceLocation("hybrid-aquatic", "raw_fish_meat_medium"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HAItems.RAW_FISH_STEAK.get(), 2)
            .requires(HAItemTags.LARGE_FISH)
            .unlockedBy(
                "has_large_fish", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HAItemTags.LARGE_FISH).build()
                )
            )
            .save(exporter)
        //#endregion

        //#region Cooking Recipes
        offerCookingRecipes(
            exporter,
            HAItems.RAW_CRAB.get(),
            HAItems.COOKED_CRAB.get(),
            0.15f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_SHRIMP.get(),
            HAItems.COOKED_SHRIMP.get(),
            0.15f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_CRAYFISH.get(),
            HAItems.COOKED_CRAYFISH.get(),
            0.15f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_LOBSTER.get(),
            HAItems.COOKED_LOBSTER.get(),
            0.3f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_LOBSTER_TAIL.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
            0.3f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_FISH_STEAK.get(),
            HAItems.COOKED_FISH_STEAK.get(),
            0.3f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_FISH_MEAT.get(),
            HAItems.COOKED_FISH_MEAT.get(),
            0.15f
        )

        offerCookingRecipes(
            exporter,
            HAItems.RAW_TENTACLE.get(),
            HAItems.COOKED_TENTACLE.get(),
            0.15f
        )

        offerCookingRecipes(
            exporter,
            HAItems.CLAM.get(),
            HAItems.COOKED_CLAM.get(),
            0.15f
        )

        offerKelpCookingRecipes(exporter, HAItemTags.KELPS, Items.DRIED_KELP, 0.15f)
        //#endregion

        offerBleachingRecipes(exporter, bleachedCoralBlockMap)
        offerBleachingRecipes(exporter, bleachedCoralMap)
        offerBleachingRecipes(exporter, bleachedCoralFanMap)
    }

    private fun offerCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        input: Item,
        output: Item,
        experience: Float,
    ) {
        simpleCookingRecipe(
            exporter,
            "smelting",
            RecipeSerializer.SMELTING_RECIPE,
            200,
            input,
            output,
            experience
        )
        simpleCookingRecipe(
            exporter,
            "smoking",
            RecipeSerializer.SMOKING_RECIPE,
            100,
            input,
            output,
            experience
        )
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

    //#region Kelp Cooking Recipes
    private fun offerKelpCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float,
    ) {
        offerKelpCookingRecipe(
            exporter,
            "smelting",
            RecipeSerializer.SMELTING_RECIPE,
            200,
            inputTag,
            output,
            experience
        )
        offerKelpCookingRecipe(
            exporter,
            "smoking",
            RecipeSerializer.SMOKING_RECIPE,
            100,
            inputTag,
            output,
            experience
        )
        offerKelpCookingRecipe(
            exporter,
            "campfire_cooking",
            RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
            600,
            inputTag,
            output,
            experience
        )
    }

    private fun offerKelpCookingRecipe(
        exporter: Consumer<FinishedRecipe>,
        cooker: String,
        serializer: RecipeSerializer<out AbstractCookingRecipe>,
        cookingTime: Int,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float,
    ) {
        val builder = SimpleCookingRecipeBuilder
            .generic(Ingredient.of(inputTag), RecipeCategory.FOOD, output, experience, cookingTime, serializer)
            .unlockedBy("has_kelp", has(inputTag))

        val recipeId = getItemName(output) + "_from_" + cooker
        builder.save(exporter, recipeId)
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
        exporter: Consumer<FinishedRecipe>,
        map: Map<Block, Block>,
    ) {
        for ((woodType, raftType) in map) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, raftType, 2)
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
                    exporter,
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
        HABlocks.DEAD_LEAF_CORAL_FAN.get() to HABlocks.BLEACHED_LEAF_CORAL_FAN.get(),
        HABlocks.DEAD_THORN_CORAL_FAN.get() to HABlocks.BLEACHED_THORN_CORAL_FAN.get(),
        HABlocks.DEAD_BUTTON_CORAL_FAN.get() to HABlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
        HABlocks.DEAD_LOPHELIA_CORAL_FAN.get() to HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
        HABlocks.DEAD_SUN_CORAL_FAN.get() to HABlocks.BLEACHED_SUN_CORAL_FAN.get(),
    )

    private fun offerBleachingRecipes(
        exporter: Consumer<FinishedRecipe>,
        map: Map<Block, Block>,
    ) {
        for ((deadCoral, bleachedCoral) in map) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bleachedCoral, 8)
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
                    exporter,
                    "${getItemName(bleachedCoral.asItem())}_from_${getItemName(deadCoral.asItem())}"
                )
        }
    }
    //#endregion
}
