package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TetraEntity
import net.minecraft.resources.ResourceLocation

class TetraEntityModel : HAFishEntityModel<TetraEntity>("tetra") {

    private val NEON_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/neon_tetra.png")
    private val GREEN_NEON_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/green_neon_tetra.png")
    private val BLACK_NEON_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/black_neon_tetra.png")
    private val EMBER_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/ember_tetra.png")
    private val GLOWLIGHT_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/glowlight_tetra.png")
    private val RUMMYNOSE_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/rummynose_tetra.png")
    private val CARDINAL_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/cardinal_tetra.png")
    private val BLIND_CAVE_TETRA_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/tetra/cave_tetra.png")

    override fun getTextureResource(animatable: TetraEntity): ResourceLocation {
        return when (animatable.variant) {
            TetraEntity.Companion.Type.NEON_TETRA -> NEON_TETRA_TEXTURE
            TetraEntity.Companion.Type.GREEN_NEON_TETRA -> GREEN_NEON_TETRA_TEXTURE
            TetraEntity.Companion.Type.BLACK_NEON_TETRA -> BLACK_NEON_TETRA_TEXTURE
            TetraEntity.Companion.Type.EMBER_TETRA -> EMBER_TETRA_TEXTURE
            TetraEntity.Companion.Type.GLOWLIGHT_TETRA -> GLOWLIGHT_TETRA_TEXTURE
            TetraEntity.Companion.Type.RUMMYNOSE_TETRA -> RUMMYNOSE_TETRA_TEXTURE
            TetraEntity.Companion.Type.CARDINAL_TETRA -> CARDINAL_TETRA_TEXTURE
            TetraEntity.Companion.Type.BLIND_CAVE_TETRA -> BLIND_CAVE_TETRA_TEXTURE
        }
    }
}
