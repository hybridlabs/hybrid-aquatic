package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.NudibranchEntity
import net.minecraft.util.Identifier
import kotlin.random.Random

class NudibranchEntityModel : HybridAquaticCritterEntityModel<NudibranchEntity>("nudibranch") {

    private val commonTextures = listOf(
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_baba.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_bullock.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_festiva.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_kubaryana.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_kuniei.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_magnificent.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_pyjama.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_sagami.png"),
        Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_yonowae.png")
    )

    override fun getTextureResource(animatable: NudibranchEntity): Identifier {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}