package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items

class HermitCrabEntityModel : HybridAquaticCrustaceanEntityModel<HermitCrabEntity>("hermit_crab") {

    private val SHELL_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_shell.png")
    private val BLOCK_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab_block.png")
    private val NONE_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/crustacean/hermit_crab/hermit_crab.png")

    private val SHELL_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/hermit_crab/hermit_crab_shell.geo.json")
    private val BLOCK_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/hermit_crab/hermit_crab_block.geo.json")
    private val NONE_MODEL = ResourceLocation("hybrid-aquatic", "geo/crustacean/hermit_crab/hermit_crab.geo.json")

    override fun getTextureResource(animatable: HermitCrabEntity): ResourceLocation {
        return if (animatable.shellItem.`is`(Items.NAUTILUS_SHELL)) SHELL_TEXTURE
        else if (animatable.shellItem.isEmpty) NONE_TEXTURE
        else BLOCK_TEXTURE
    }

    override fun getModelResource(animatable: HermitCrabEntity): ResourceLocation {
        return if (animatable.shellItem.`is`(Items.NAUTILUS_SHELL)) SHELL_MODEL
        else if (animatable.shellItem.isEmpty) NONE_MODEL
        else BLOCK_MODEL
    }
}
