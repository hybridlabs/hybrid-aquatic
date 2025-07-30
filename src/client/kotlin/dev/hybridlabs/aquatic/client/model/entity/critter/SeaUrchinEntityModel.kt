package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class SeaUrchinEntityModel : HybridAquaticCritterEntityModel<SeaUrchinEntity>("sea_urchin") {

    private val largeTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_black.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_blue.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_purple.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_long_red.png"),
    )

    private val smallTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_black.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_blue.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_purple.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/sea_urchin/sea_urchin_red.png"),
    )

    override fun getTextureResource(animatable: SeaUrchinEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)

        return when (animatable.variant) {
            SeaUrchinEntity.Type.SMALL -> smallTextures[random.nextInt(smallTextures.size)]
            SeaUrchinEntity.Type.LARGE -> largeTextures[random.nextInt(largeTextures.size)]
        }
    }
}