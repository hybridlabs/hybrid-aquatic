package dev.hybridlabs.aquatic.client.render.features

import dev.hybridlabs.aquatic.interfaces.Porcupine
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.random.Random

class PlayerItemPorcupineFeature(entityRenderer: LivingEntityRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>>?) : FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>>(entityRenderer) {

    val itemRenderer = MinecraftClient.getInstance().itemRenderer;

    override fun render(matrices: MatrixStack?, vertexConsumers: VertexConsumerProvider?, light: Int, entity: PlayerEntity?, limbAngle: Float, limbDistance: Float, tickDelta: Float, animationProgress: Float, headYaw: Float, headPitch: Float) {
        if (entity is Porcupine) {
            val porcupine = entity as Porcupine
            val impaledStacks = porcupine.`hybrid_aquatic$getImpaledStacks`()
            val impaledItemCount = impaledStacks.size

            val random = Random.create(entity.id.toLong())
            if (impaledItemCount > 0) { // This weird thing was somehow more efficient????? idk im tired
                for (stack in impaledStacks) {
                    matrices!!.push()
                    val modelPart = (this.contextModel as PlayerEntityModel<*>).getRandomPart(random)
                    val cuboid = modelPart.getRandomCuboid(random)
                    modelPart.rotate(matrices)
                    var o = random.nextFloat()
                    var p = random.nextFloat()
                    var q = random.nextFloat()
                    val r = MathHelper.lerp(o, cuboid.minX, cuboid.maxX) / 16.0f
                    val s = MathHelper.lerp(p, cuboid.minY, cuboid.maxY) / 16.0f
                    val t = MathHelper.lerp(q, cuboid.minZ, cuboid.maxZ) / 16.0f
                    matrices.translate(r, s, t)
                    o = -1.0f * (o * 2.0f - 1.0f)
                    p = -1.0f * (p * 2.0f - 1.0f)
                    q = -1.0f * (q * 2.0f - 1.0f)
                    // Render thingy here
                    itemRenderer.renderItem(stack, ModelTransformationMode.NONE, light, 0, matrices, vertexConsumers, entity.world, entity.id)
                    matrices.pop()
                }
            }
        }
    }
}