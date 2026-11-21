package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.SurgeonfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class SurgeonfishEntityModel : BaseFishEntityModel<SurgeonfishEntity>("hybrid_aquatic", "surgeonfish") {
    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SurgeonfishEntity.Companion.Type.entries)!!) {
            SurgeonfishEntity.Companion.Type.BLUE_TANG -> BLUE_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.POWDER_BLUE_TANG -> POWDER_BLUE_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.YELLOW_TANG -> YELLOW_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.LINED -> LINED_TEXTURE
            SurgeonfishEntity.Companion.Type.ORANGESHOULDER -> ORANGESHOULDER_TEXTURE
            SurgeonfishEntity.Companion.Type.SOHAL -> SOHAL_TEXTURE
            SurgeonfishEntity.Companion.Type.UNICORNFISH -> UNICORNFISH_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SurgeonfishEntity.Companion.Type.entries)!!) {
            SurgeonfishEntity.Companion.Type.BLUE_TANG -> BLUE_TANG_MODEL
            SurgeonfishEntity.Companion.Type.POWDER_BLUE_TANG -> POWDER_BLUE_TANG_MODEL
            SurgeonfishEntity.Companion.Type.YELLOW_TANG -> YELLOW_TANG_MODEL
            SurgeonfishEntity.Companion.Type.LINED -> LINED_MODEL
            SurgeonfishEntity.Companion.Type.ORANGESHOULDER -> ORANGESHOULDER_MODEL
            SurgeonfishEntity.Companion.Type.SOHAL -> SOHAL_MODEL
            SurgeonfishEntity.Companion.Type.UNICORNFISH -> UNICORNFISH_MODEL
        }
    }

    companion object {
        private val BLUE_TANG_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_blue_tang.png")
        private val POWDER_BLUE_TANG_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_powder_blue_tang.png")
        private val YELLOW_TANG_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_yellow_tang.png")
        private val LINED_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_lined.png")
        private val ORANGESHOULDER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_orangeshoulder.png")
        private val SOHAL_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_sohal.png")
        private val UNICORNFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/surgeonfish/surgeonfish_unicornfish.png")

        private val BLUE_TANG_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_blue_tang")
        private val POWDER_BLUE_TANG_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_powder_blue_tang")
        private val YELLOW_TANG_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_yellow_tang")
        private val LINED_MODEL = Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_lined")
        private val ORANGESHOULDER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_orangeshoulder")
        private val SOHAL_MODEL = Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_sohal")
        private val UNICORNFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/surgeonfish/surgeonfish_unicornfish")
    }
}
