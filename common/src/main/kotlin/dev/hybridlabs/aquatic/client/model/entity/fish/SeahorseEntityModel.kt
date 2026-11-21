package dev.hybridlabs.aquatic.client.model.entity.fish

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.constant.DataTickets

class SeahorseEntityModel : BaseFishEntityModel<SeahorseEntity>("hybrid_aquatic", "seahorse"), HABoneAdjustingModel {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeahorseEntity.Companion.Type.entries)!!) {
            SeahorseEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            SeahorseEntity.Companion.Type.PYGMY -> PYGMY_TEXTURE
            SeahorseEntity.Companion.Type.THORNY -> THORNY_TEXTURE
            SeahorseEntity.Companion.Type.BIG_BELLY -> BIG_BELLY_TEXTURE
        }
    }

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val headPitch = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { head ->
            head.setRotX(headPitch * Mth.DEG_TO_RAD)
            head.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
    }

    companion object {
        private val COMMON_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seahorse/seahorse_common.png")
        private val PYGMY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seahorse/seahorse_pygmy.png")
        private val THORNY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seahorse/seahorse_thorny.png")
        private val BIG_BELLY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seahorse/seahorse_big_belly.png")
    }
}
