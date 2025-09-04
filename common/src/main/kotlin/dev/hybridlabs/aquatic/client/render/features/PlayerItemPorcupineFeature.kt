package dev.hybridlabs.aquatic.client.renderer.features

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.interfaces.Porcupine
import net.minecraft.client.Minecraft
import net.minecraft.client.model.PlayerModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.layers.RenderType
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemDisplayContext

class PlayerItemPorcupineFeature(entityRenderer: LivingEntityRenderer<Player, PlayerModel<Player>>?) :
    RenderType<Player, PlayerModel<Player>>(entityRenderer) {

    val itemRenderer = Minecraft.getInstance().itemRenderer

    override fun render(
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        entity: Player,
        limbAngle: Float,
        limbDistance: Float,
        tickDelta: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        if (entity is Porcupine) {
            val porcupine = entity as Porcupine
            val impaledStacks = porcupine.`hybrid_aquatic$getImpaledStacks`()
            val impaledItemCount = impaledStacks.size

            val random = RandomSource.create(entity.id.toLong())
            if (impaledItemCount > 0) { // This weird thing was somehow more efficient????? idk im tired
                for (stack in impaledStacks) {
                    matrices.pushPose()
                    val modelPart = (this.parentModel as PlayerModel<*>).getRandomModelPart(random)
                    val cube = modelPart.getRandomCube(random)
                    modelPart.translateAndRotate(matrices)
                    var o = random.nextFloat()
                    var p = random.nextFloat()
                    var q = random.nextFloat()
                    val r = Mth.lerp(o, cube.minX, cube.maxX) / 16.0f
                    val s = Mth.lerp(p, cube.minY, cube.maxY) / 16.0f
                    val t = Mth.lerp(q, cube.minZ, cube.maxZ) / 16.0f
                    matrices.translate(r, s, t)
                    o = -1.0f * (o * 2.0f - 1.0f)
                    p = -1.0f * (p * 2.0f - 1.0f)
                    q = -1.0f * (q * 2.0f - 1.0f)
                    // Render thingy here
                    itemRenderer.renderStatic(
                        stack,
                        ItemDisplayContext.NONE,
                        light,
                        0,
                        matrices,
                        vertexConsumers,
                        entity.level(),
                        entity.id
                    )
                    matrices.popPose()
                }
            }
        }
    }
}