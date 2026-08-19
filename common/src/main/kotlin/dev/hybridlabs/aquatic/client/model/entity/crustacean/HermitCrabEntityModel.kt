package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCrustaceanEntityModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items

class HermitCrabEntityModel : BaseCrustaceanEntityModel<HermitCrabEntity>("hybrid_aquatic", "hermit_crab") {

    companion object {
        private val SHELL_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_shell.png")
        private val OMINOUS_CONCH_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_ominous_conch.png")
        private val BLOCK_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_block.png")
        private val NONE_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab.png")

        private val SHELL_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/crustacean/hermit_crab/hermit_crab_shell.geo.json")
        private val OMINOUS_CONCH_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/crustacean/hermit_crab/hermit_crab_ominous_conch.geo.json")
        private val BLOCK_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/crustacean/hermit_crab/hermit_crab_block.geo.json")
        private val NONE_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/crustacean/hermit_crab/hermit_crab.geo.json")
    }

    override fun getTextureResource(animatable: HermitCrabEntity): ResourceLocation {
        return if (animatable.shellItem.`is`(Items.NAUTILUS_SHELL)) SHELL_TEXTURE
        else if (animatable.shellItem.`is`(HAItems.OMINOUS_CONCH.get())) OMINOUS_CONCH_TEXTURE
        else if (animatable.shellItem.isEmpty) NONE_TEXTURE
        else BLOCK_TEXTURE
    }

    override fun getModelResource(animatable: HermitCrabEntity): ResourceLocation {
        return if (animatable.shellItem.`is`(Items.NAUTILUS_SHELL)) SHELL_MODEL
        else if ((animatable.shellItem.`is`(HAItems.OMINOUS_CONCH.get()))) OMINOUS_CONCH_MODEL
        else if (animatable.shellItem.isEmpty) NONE_MODEL
        else BLOCK_MODEL
    }
}
