package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SurgeonfishEntity
import net.minecraft.resources.ResourceLocation

class SurgeonfishEntityModel : HybridAquaticFishEntityModel<SurgeonfishEntity>("surgeonfish") {

    private val BLUE_TANG_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_blue_tang.png")
    private val POWDER_BLUE_TANG_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_powder_blue_tang.png")
    private val YELLOW_TANG_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_yellow_tang.png")
    private val LINED_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_lined.png")
    private val ORANGESHOULDER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_orangeshoulder.png")
    private val SOHAL_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_sohal.png")
    private val UNICORNFISH_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/surgeonfish/surgeonfish_unicornfish.png")

    private val BLUE_TANG_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_blue_tang.geo.json")
    private val POWDER_BLUE_TANG_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_powder_blue_tang.geo.json")
    private val YELLOW_TANG_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_yellow_tang.geo.json")
    private val LINED_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_lined.geo.json")
    private val ORANGESHOULDER_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_orangeshoulder.geo.json")
    private val SOHAL_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_sohal.geo.json")
    private val UNICORNFISH_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/surgeonfish/surgeonfish_unicornfish.geo.json")

    override fun getTextureResource(animatable: SurgeonfishEntity): ResourceLocation {
        return when (animatable.variant) {
            SurgeonfishEntity.Companion.Type.BLUE_TANG -> BLUE_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.POWDER_BLUE_TANG -> POWDER_BLUE_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.YELLOW_TANG -> YELLOW_TANG_TEXTURE
            SurgeonfishEntity.Companion.Type.LINED -> LINED_TEXTURE
            SurgeonfishEntity.Companion.Type.ORANGESHOULDER -> ORANGESHOULDER_TEXTURE
            SurgeonfishEntity.Companion.Type.SOHAL -> SOHAL_TEXTURE
            SurgeonfishEntity.Companion.Type.UNICORNFISH -> UNICORNFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: SurgeonfishEntity): ResourceLocation {
        return when (animatable.variant) {
            SurgeonfishEntity.Companion.Type.BLUE_TANG -> BLUE_TANG_MODEL
            SurgeonfishEntity.Companion.Type.POWDER_BLUE_TANG -> POWDER_BLUE_TANG_MODEL
            SurgeonfishEntity.Companion.Type.YELLOW_TANG -> YELLOW_TANG_MODEL
            SurgeonfishEntity.Companion.Type.LINED -> LINED_MODEL
            SurgeonfishEntity.Companion.Type.ORANGESHOULDER -> ORANGESHOULDER_MODEL
            SurgeonfishEntity.Companion.Type.SOHAL -> SOHAL_MODEL
            SurgeonfishEntity.Companion.Type.UNICORNFISH -> UNICORNFISH_MODEL
            else -> BLUE_TANG_MODEL
        }
    }
}