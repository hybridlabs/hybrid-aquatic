package dev.hybridlabs.aquatic.client.render.entity.fish

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.cache.`object`.GeoBone
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer

@Suppress("LeakingThis")
open class HAFishEntityRenderer<T : HAFishEntity>(
    context: Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false,
) : GeoEntityRenderer<T>(context, model) {

    protected var mainHandItem: ItemStack? = null
    private val itemBone: String = "item"

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    init {
        if (canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.3f

        // Add some held item rendering
        addRenderLayer(object : BlockAndItemGeoLayer<T>(this) {

            override fun getStackForBone(bone: GeoBone, animatable: T): ItemStack? {
                return when (bone.name) {
                    itemBone -> animatable.mainHandItem
                    else -> null
                }
            }

            override fun getTransformTypeForStack(
                bone: GeoBone,
                stack: ItemStack,
                animatable: T
            ): ItemDisplayContext {
                return ItemDisplayContext.GROUND
            }

            override fun renderStackForBone(
                poseStack: PoseStack,
                bone: GeoBone,
                stack: ItemStack,
                animatable: T,
                bufferSource: MultiBufferSource,
                partialTick: Float,
                packedLight: Int,
                packedOverlay: Int
            ) {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90f))

                super.renderStackForBone(
                    poseStack,
                    bone,
                    stack,
                    animatable,
                    bufferSource,
                    partialTick,
                    packedLight,
                    packedOverlay
                )
            }
        })
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 180f
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
    ) {
        if (variableSize) {
            val size = HAWaterAnimal.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }

    override fun preRender(
        poseStack: PoseStack,
        animatable: T,
        model: BakedGeoModel,
        bufferSource: MultiBufferSource?,
        buffer: VertexConsumer?,
        isReRender: Boolean,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float,
    ) {
        super.preRender(
            poseStack,
            animatable,
            model,
            bufferSource,
            buffer,
            isReRender,
            partialTick,
            packedLight,
            packedOverlay,
            red,
            green,
            blue,
            alpha
        )

        this.mainHandItem = animatable.mainHandItem
    }
}