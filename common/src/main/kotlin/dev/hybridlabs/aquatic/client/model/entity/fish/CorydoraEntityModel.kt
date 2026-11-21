package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.CorydoraEntity
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import kotlin.random.Random

class CorydoraEntityModel : BaseFishEntityModel<CorydoraEntity>("hybrid_aquatic", "corydora") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        val seed = (renderState.getGeckolibData(HAPIDataTickets.ENTITY_UUID)!!).leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }

    companion object {
        private val commonTextures = listOf(
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_albino.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_panda.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_bronze.png"),
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_nattereri.png"),
        )
    }
}
