package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object HybridAquaticPotions {
    private val registeredData: MutableList<RecipeData> = mutableListOf()

    init {
        FabricBrewingRecipeRegistryBuilder.BUILD.register { builder ->
            registeredData.forEach { data ->
                builder.registerPotionRecipe(data.inputPotion, data.ingredient, data.potion)
            }
        }
    }

    val GLOWING_POTION = registerPotionWithRecipe(
        "glowing",
        Potion("glowing", StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)),
        HybridAquaticItems.GLOW_SLIME,
    )

    val CLARITY_POTION = registerPotionWithRecipe(
        "clarity",
        Potion("clarity", StatusEffectInstance(HybridAquaticStatusEffects.CLARITY, 1200, 0)),
        HybridAquaticItems.BARRELEYE,
    )

    val THALASSOPHOBIA_POTION = registerPotionWithRecipe(
        "thalassophobia",
        Potion("thalassophobia", StatusEffectInstance(HybridAquaticStatusEffects.THALASSOPHOBIA, 1200, 0)),
        HybridAquaticItems.ANGLERFISH,
    )

    val MINOR_LUCK_POTION = registerPotionWithRecipe(
        "minor_luck",
        Potion("minor_luck", StatusEffectInstance(StatusEffects.LUCK, 1200, 0)),
        HybridAquaticItems.PEARL,
    )

    val MAJOR_LUCK_POTION = registerPotionWithRecipe(
        "major_luck",
        Potion("major_luck", StatusEffectInstance(StatusEffects.LUCK, 600, 1)),
        HybridAquaticItems.BLACK_PEARL,
    )

    val BLEEDING_POTION = registerPotionWithRecipe(
        "bleeding",
        Potion("bleeding", StatusEffectInstance(HybridAquaticStatusEffects.BLEEDING, 200, 0)),
        HybridAquaticItems.SHARK_TOOTH,
    )

    val SWIMMING_POTION = registerPotionWithRecipe(
        "swimming",
        Potion("swimming", StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1200, 0),
            StatusEffectInstance(StatusEffects.HUNGER, 600, 0)),
        HybridAquaticItems.MAHI,
    )

    val BUOYANCY_POTION = registerPotionWithRecipe(
        "buoyancy",
        Potion("buoyancy", StatusEffectInstance(HybridAquaticStatusEffects.BUOYANCY, 200, 0)),
        Items.KELP,
    )

    val SPININESS_POTION = registerPotionWithRecipe(
        "spininess",
        Potion("spininess", StatusEffectInstance(HybridAquaticStatusEffects.SPININESS, 300, 0)),
        HybridAquaticItems.SEA_URCHIN_SPINE,
    )

    private fun registerPotionWithRecipe(id: String, potion: Potion, ingredient: Item, inputPotion: RegistryEntry<Potion> = Potions.AWKWARD): RegistryEntry<Potion> {
        val entry = register(id, potion)
        registeredData.add(RecipeData(entry, ingredient, inputPotion))
        return entry
    }

    private fun register(id: String, potion: Potion): RegistryEntry<Potion> {
        return Registry.registerReference(Registries.POTION, Identifier.of(HybridAquatic.MOD_ID, id), potion)
    }

    private data class RecipeData(
        val potion: RegistryEntry<Potion>,
        val ingredient: Item,
        val inputPotion: RegistryEntry<Potion>,
    )
}
