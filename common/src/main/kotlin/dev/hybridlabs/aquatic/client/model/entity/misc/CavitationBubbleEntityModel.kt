package dev.hybridlabs.aquatic.client.model.entity.misc

import com.geckolib.constant.DataTickets

import com.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import com.geckolib.animation.state.AnimationTest
import com.geckolib.model.GeoModel

class CavitationBubbleEntityModel() :
    GeoModel<CavitationBubbleEntity>(), HABoneAdjustingModel {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("misc/cavitation_bubble")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("textures/entity/misc/cavitation_bubble/cavitation_bubble.png")
    }

    override fun getAnimationResource(p0: CavitationBubbleEntity): Identifier {
        return CommonClass.locate("entity/misc/cavitation_bubble")
    }

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val pitch = Mth.clamp(renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f, -45f, 45f)

        snapshots.get(PartNames.BODY).ifPresent { bone ->
            bone.setRotX(pitch * -Mth.DEG_TO_RAD)
        }
    }

}