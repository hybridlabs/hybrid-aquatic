package dev.hybridlabs.aquatic.client.render.hud

import com.mojang.blaze3d.systems.RenderSystem
import dev.hybridlabs.aquatic.item.FishingNetItem
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.DiffuseLighting
import net.minecraft.entity.EntityType

class FishingNetHUDRenderer : HudRenderCallback {
    override fun onHudRender(drawContext: DrawContext?, tickDelta: Float) {
        val client = MinecraftClient.getInstance()
        val dispatcher = client.entityRenderDispatcher

        if(client.player?.mainHandStack!!.isOf(HybridAquaticItems.FISHING_NET)) {
            // TODO testing, will make it grab from the net once i get display properties correct
            val entity = EntityType.ALLAY.create(client.world) //FishingNetItem.getEntityFromNet(client.player?.mainHandStack!!, client.world)
            drawContext?.matrices?.push()
            drawContext?.matrices?.translate(5.0, 5.0, 50.0)
            DiffuseLighting.method_34742()

            dispatcher.setRenderShadows(false)
            RenderSystem.runAsFancy {
                dispatcher.render(
                    entity,
                    0.0,
                    0.0,
                    0.0,
                    0.0f,
                    1.0f,
                    drawContext?.matrices,
                    drawContext?.vertexConsumers,
                    15728880
                )
            }
            drawContext?.draw();
            dispatcher.setRenderShadows(true)
            drawContext?.matrices?.pop()
            DiffuseLighting.enableGuiDepthLighting()
        }
    }
}