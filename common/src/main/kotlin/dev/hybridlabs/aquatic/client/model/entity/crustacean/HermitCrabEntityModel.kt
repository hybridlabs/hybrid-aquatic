package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import net.minecraft.resources.ResourceLocation

class HermitCrabEntityModel : HybridAquaticCrustaceanEntityModel<HermitCrabEntity>("hermit_crab") {

    private val SHELL_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_shell.png")
    private val SKULL_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_skull.png")

    private val SHELL_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/crustacean/hermit_crab/hermit_crab_shell.geo.json")
    private val SKULL_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/crustacean/hermit_crab/hermit_crab_skull.geo.json")

    override fun getTextureResource(animatable: HermitCrabEntity): ResourceLocation {
        return when (animatable.variant) {
            HermitCrabEntity.Companion.Type.SHELL -> SHELL_TEXTURE
            HermitCrabEntity.Companion.Type.SKULL -> SKULL_TEXTURE
        }
    }

    override fun getModelResource(animatable: HermitCrabEntity): ResourceLocation {
        return when (animatable.variant) {
            HermitCrabEntity.Companion.Type.SKULL -> SKULL_MODEL
            else -> SHELL_MODEL
        }
    }
}
