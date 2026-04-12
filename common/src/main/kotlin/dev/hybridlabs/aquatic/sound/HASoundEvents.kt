package dev.hybridlabs.aquatic.sound

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

object HASoundEvents {

    val MANATEE_AMBIENT = register("entity.manatee.ambient")
    val MANATEE_HURT = register("entity.manatee.hurt")
    val MANATEE_DIE = register("entity.manatee.die")
    val MANATEE_SWIM = register("entity.manatee.swim")
    val MANATEE_SPLASH = register("entity.manatee.splash")

    val DUGONG_AMBIENT = register("entity.dugong.ambient")
    val DUGONG_HURT = register("entity.dugong.hurt")
    val DUGONG_DIE = register("entity.dugong.die")
    val DUGONG_SWIM = register("entity.dugong.swim")
    val DUGONG_SPLASH = register("entity.dugong.splash")

    val SIRENIAN_EAT = register("entity.sirenian.eat")

    val KARKINOS_AMBIENT = register("entity.karkinos.ambient")
    val KARKINOS_HURT = register("entity.karkinos.hurt")
    val KARKINOS_DIE = register("entity.karkinos.die")

    val KARCINOMA_AMBIENT = register("entity.karcinoma.ambient")
    val KARCINOMA_HURT = register("entity.karcinoma.hurt")
    val KARCINOMA_DIE = register("entity.karcinoma.die")

    val KARCINOGEN_AMBIENT = register("entity.karcinogen.ambient")
    val KARCINOGEN_HURT = register("entity.karcinogen.hurt")
    val KARCINOGEN_DIE = register("entity.karcinogen.die")

    val SHELL_BEAST_SHOOT = register("entity.shell_beast.shoot", 64f)
    val SHELL_BEAST_AMBIENT = register("entity.shell_beast.ambient")
    val SHELL_BEAST_HURT = register("entity.shell_beast.hurt")
    val SHELL_BEAST_DIE = register("entity.shell_beast.die")

    val HYPNAUTILUS_AMBIENT = register("entity.hypnautilus.ambient")
    val HYPNAUTILUS_HURT = register("entity.hypnautilus.hurt")
    val HYPNAUTILUS_DIE = register("entity.hypnautilus.die")

    private fun register(id: String, range: Float = -1.0f): RegistryObject<SoundEvent> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        return if (range < 0)
            CommonClass.SOUND_EVENTS.register(id) { SoundEvent.createVariableRangeEvent(identifier) }
        else
            CommonClass.SOUND_EVENTS.register(id) { SoundEvent.createFixedRangeEvent(identifier, range) }
    }
}