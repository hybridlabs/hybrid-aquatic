package dev.hybridlabs.aquatic.sound

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

object HybridAquaticSoundEvents {

    val SIRENIAN_AMBIENT = register("sirenian_ambient")
    val SIRENIAN_HURT = register("sirenian_hurt")
    val SIRENIAN_DIE = register("sirenian_die")

    val OMINOUS_CONCH_SOUND = register("dies_irae")

    private fun register(id: String): RegistryObject<SoundEvent> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        return CommonClass.SOUND_EVENTS.register(id){SoundEvent.createVariableRangeEvent(identifier)}
    }
}