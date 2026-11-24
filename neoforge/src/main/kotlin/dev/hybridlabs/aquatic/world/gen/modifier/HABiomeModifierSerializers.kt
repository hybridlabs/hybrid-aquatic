package dev.hybridlabs.aquatic.world.gen.modifier

import dev.hybridlabs.aquatic.Constants
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

object HABiomeModifierSerializers {
    private val SERIALIZERS =
        DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.MOD_ID)

    @Suppress("unused")
    val CONFIG_GATED_FEATURES = SERIALIZERS.register(
        "config_gated_features",
        java.util.function.Supplier { ConfigGatedFeatureBiomeModifier.CODEC }
    )

    fun register() {
        SERIALIZERS.register(MOD_BUS)
    }
}
