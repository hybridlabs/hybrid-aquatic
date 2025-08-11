package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.WreckfishEntity
import net.minecraft.util.Identifier

class WreckfishEntityModel : HybridAquaticFishEntityModel<WreckfishEntity>("wreckfish") {

    private val GIANT_SEA_BASS_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/wreckfish/giant_sea_bass.png")

    private val GIANT_SEA_BASS_MODEL = Identifier.of("hybrid-aquatic", "geo/fish/wreckfish/giant_sea_bass.geo.json")

    override fun getTextureResource(animatable: WreckfishEntity): Identifier {
        return when (animatable.variant) {
            WreckfishEntity.Type.GIANT_SEA_BASS -> GIANT_SEA_BASS_TEXTURE
        }
    }

    override fun getModelResource(animatable: WreckfishEntity): Identifier {
        return when (animatable.variant) {
            WreckfishEntity.Type.GIANT_SEA_BASS -> GIANT_SEA_BASS_MODEL
        }
    }
}