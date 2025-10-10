package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class HammerheadSharkEntityModel : HybridAquaticSharkEntityModel<HammerheadSharkEntity>("hammerhead_shark") {

    private val commonTextures = listOf(
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/shark/hammerhead_shark/hammerhead_shark.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/shark/hammerhead_shark/hammerhead_shark_brown.png"),
        ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/shark/hammerhead_shark/hammerhead_shark_olive.png")
    )

    override fun getTextureResource(animatable: HammerheadSharkEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}