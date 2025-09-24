package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState

class SeahorseEntityModel : HybridAquaticFishEntityModel<SeahorseEntity>("seahorse") {

    private val COMMON_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_common.png")
    private val PYGMY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_pygmy.png")
    private val THORNY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_thorny.png")
    private val BIG_BELLY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_big_belly.png")

    override fun getTextureResource(animatable: SeahorseEntity): ResourceLocation {
        return when (animatable.variant) {
            SeahorseEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            SeahorseEntity.Companion.Type.PYGMY -> PYGMY_TEXTURE
            SeahorseEntity.Companion.Type.THORNY -> THORNY_TEXTURE
            SeahorseEntity.Companion.Type.BIG_BELLY -> BIG_BELLY_TEXTURE
        }
    }


    override fun setCustomAnimations(
        animatable: SeahorseEntity,
        instanceId: Long,
        animationState: AnimationState<SeahorseEntity>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}