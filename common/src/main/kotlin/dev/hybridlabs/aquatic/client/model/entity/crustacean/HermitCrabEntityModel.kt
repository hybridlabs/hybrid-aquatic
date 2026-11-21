package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.Identifier
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class HermitCrabEntityModel : BaseCrustaceanEntityModel<HermitCrabEntity>("hybrid_aquatic", "hermit_crab") {

    companion object {
        private val SHELL_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_shell.png")
        private val OMINOUS_CONCH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_ominous_conch.png")
        private val BLOCK_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_block.png")
        private val NONE_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab.png")

        private val SHELL_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/hermit_crab/hermit_crab_shell")
        private val OMINOUS_CONCH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/hermit_crab/hermit_crab_ominous_conch")
        private val BLOCK_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/hermit_crab/hermit_crab_block")
        private val NONE_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "crustacean/hermit_crab/hermit_crab")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).`is`(Items.NAUTILUS_SHELL)) SHELL_TEXTURE
        else if ((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).`is`(HAItems.OMINOUS_CONCH.get())) OMINOUS_CONCH_TEXTURE
        else if ((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).isEmpty) NONE_TEXTURE
        else BLOCK_TEXTURE
    }

    override fun addAdditionalStateData(
        animatable: HermitCrabEntity,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.SHELL_ITEM, animatable.shellItem)
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).`is`(Items.NAUTILUS_SHELL)) SHELL_MODEL
        else if (((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).`is`(HAItems.OMINOUS_CONCH.get()))) OMINOUS_CONCH_MODEL
        else if ((renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY).isEmpty) NONE_MODEL
        else BLOCK_MODEL
    }
}
