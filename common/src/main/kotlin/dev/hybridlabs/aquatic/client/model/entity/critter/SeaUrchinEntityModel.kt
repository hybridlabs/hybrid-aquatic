package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class SeaUrchinEntityModel : HybridAquaticCritterEntityModel<SeaUrchinEntity>("sea_urchin") {

    private val largeTextures = listOf(
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_black.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_blue.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_purple.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_red.png"),
    )

    private val smallTextures = listOf(
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_black.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_blue.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_purple.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_red.png"),
    )

    override fun getTextureResource(animatable: SeaUrchinEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return when (animatable.variant) {
            SeaUrchinEntity.Companion.Type.SMALL -> smallTextures[random.nextInt(smallTextures.size)]
            SeaUrchinEntity.Companion.Type.LARGE -> largeTextures[random.nextInt(largeTextures.size)]
        }
    }
}