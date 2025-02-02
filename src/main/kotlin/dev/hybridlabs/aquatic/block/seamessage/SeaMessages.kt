package dev.hybridlabs.aquatic.block.seamessage

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import java.util.Optional

object SeaMessages {
    val THE_CREEPERS_CODE = register("the_creepers_code")
    val POYO = register("poyo")
    val RICK_ROLL = register("rick_roll")
    val BOLD_MUDDY = register("bold_muddy")
    val CATPENJOE = register("catpenjoe")
    val WILLOWSHINE = register("willowshine")
    val LOSS = register("loss")
    val THREATS = register("threats")
    val WARRANTY = register("warranty")
    val POKE = register("poke")
    val ONE_PIECE = register("one_piece")
    val MYLO = register("mylo")
    val WOMP_WOMP = register("womp_womp")
    val CROCODILE = register("crocodile")
    val BAD_LUCK = register("bad_luck")
    val CRYPTIC_GUN_MESSAGE = register("cryptic_gun_message")
    val BOO = register("boo")
    val YASHAA = register("yashaa")
    val RIVER_TO_SEA = register("river_to_sea")
    val FREE_PALESTINE = register("free_palestine")
    val FREE_GAZA = register("free_gaza")
    val CONTROL_OOP = register("control_oop", "FBC")
    val DYLAN = register("dylan", "FBC", true)

    fun register(id: String, author: String? = null, infinite: Boolean = false): RegistryEntry<SeaMessage> {
        val message = SeaMessage(infinite, Optional.ofNullable(author))
        return Registry.registerReference(HybridAquaticRegistries.SEA_MESSAGE, Identifier.of(HybridAquatic.MOD_ID, id), message)
    }
}
