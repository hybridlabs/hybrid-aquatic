package dev.hybridlabs.aquatic.client.model.entity.critter

import dev.hybridlabs.aquatic.entity.critter.NudibranchEntity
import net.minecraft.util.Identifier

class NudibranchEntityModel : HybridAquaticCritterEntityModel<NudibranchEntity>("nudibranch") {

    private val BABA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_baba.png")
    private val BULLOCK_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_bullock.png")
    private val FESTIVA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_festiva.png")
    private val KUBARYANA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_kubaryana.png")
    private val KUNIEI_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_kuniei.png")
    private val MAGNIFICENT_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_magnificent.png")
    private val PYJAMA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_pyjama.png")
    private val SAGAMI_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_sagami.png")
    private val YONOWAE_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/critter/nudibranch/nudibranch_yonowae.png")

    override fun getTextureResource(animatable: NudibranchEntity): Identifier {
        return when (animatable.variant) {
            NudibranchEntity.Type.BABA -> BABA_TEXTURE
            NudibranchEntity.Type.BULLOCK -> BULLOCK_TEXTURE
            NudibranchEntity.Type.FESTIVA -> FESTIVA_TEXTURE
            NudibranchEntity.Type.KUBARYANA -> KUBARYANA_TEXTURE
            NudibranchEntity.Type.KUNIEI -> KUNIEI_TEXTURE
            NudibranchEntity.Type.MAGNIFICENT -> MAGNIFICENT_TEXTURE
            NudibranchEntity.Type.PYJAMA -> PYJAMA_TEXTURE
            NudibranchEntity.Type.SAGAMI -> SAGAMI_TEXTURE
            NudibranchEntity.Type.YONOWAE -> YONOWAE_TEXTURE
        }
    }
}