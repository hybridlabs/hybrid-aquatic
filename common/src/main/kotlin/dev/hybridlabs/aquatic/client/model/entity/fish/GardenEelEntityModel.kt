package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.GardenEelEntity
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState

class GardenEelEntityModel : HAFishEntityModel<GardenEelEntity>("garden_eel") {

    override fun setCustomAnimations(
        animatable: GardenEelEntity,
        instanceId: Long,
        animationState: AnimationState<GardenEelEntity>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}
