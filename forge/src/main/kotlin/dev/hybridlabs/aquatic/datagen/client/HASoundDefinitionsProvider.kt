package dev.hybridlabs.aquatic.datagen.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.SoundDefinition
import net.minecraftforge.common.data.SoundDefinitionsProvider
import java.util.function.Supplier

class HASoundDefinitionsProvider(output: PackOutput, helper: ExistingFileHelper) :
    SoundDefinitionsProvider(output, Constants.MOD_ID, helper) {

    override fun registerSounds() {
        addSound("item/ominous_conch_blows", "item.ominous_conch.blows", HASoundEvents.OMINOUS_CONCH_BLOWS)

        addEntity("manatee",
            HASoundEvents.MANATEE_AMBIENT, CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.MANATEE_DIE, CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.MANATEE_HURT, CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.MANATEE_SWIM, CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.MANATEE_SPLASH, CommonClass.locate("entity/sirenian_splash")
        )

        addEntity("dugong",
            HASoundEvents.DUGONG_AMBIENT, CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.DUGONG_DIE, CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.DUGONG_HURT, CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.DUGONG_SWIM, CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.DUGONG_SPLASH, CommonClass.locate("entity/sirenian_splash")
        )

        addEntity("karkinos",
            ambient = HASoundEvents.KARKINOS_AMBIENT,
            die = HASoundEvents.KARKINOS_DIE,
            hurt = HASoundEvents.KARKINOS_HURT
        )

        addEntity("karcinoma",
            ambient = HASoundEvents.KARCINOMA_AMBIENT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            die = HASoundEvents.KARCINOMA_DIE, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            hurt = HASoundEvents.KARCINOMA_HURT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
        )

        addEntity("karcinogen",
            ambient = HASoundEvents.KARCINOGEN_AMBIENT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            die = HASoundEvents.KARCINOGEN_DIE, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            hurt = HASoundEvents.KARCINOGEN_HURT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
        )

        addEntity("shell_beast",
            ambient = HASoundEvents.SHELL_BEAST_AMBIENT, CommonClass.locate("entity/shell_beast_ambient"),
            die = HASoundEvents.SHELL_BEAST_DIE, CommonClass.locate("entity/shell_beast_die"),
            hurt = HASoundEvents.SHELL_BEAST_HURT, CommonClass.locate("entity/shell_beast_hurt"),
        )
        addEntitySound("shell_beast", "shoot", HASoundEvents.SHELL_BEAST_SHOOT, CommonClass.locate("entity/shell_beast_shoot"))

        addEntity("hypnautilus",
            ambient = HASoundEvents.HYPNAUTILUS_AMBIENT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            die = HASoundEvents.HYPNAUTILUS_DIE, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
            hurt = HASoundEvents.HYPNAUTILUS_HURT, ResourceLocation.withDefaultNamespace("entity.turtle.egg_crack"),
        )
    }

    fun addEntity(id: String,
                  ambient: Supplier<SoundEvent>, ambientAudio: ResourceLocation? = null,
                  die: Supplier<SoundEvent>, dieAudio: ResourceLocation? = null,
                  hurt: Supplier<SoundEvent>, hurtAudio: ResourceLocation? = null,
                  swim: Supplier<SoundEvent>? = null, swimAudio: ResourceLocation? = null,
                  splash: Supplier<SoundEvent>? = null, splashAudio: ResourceLocation? = null
    ) {
        mapOf(
            ("ambient" to ambientAudio) to ambient,
            ("die" to dieAudio) to die,
            ("hurt" to hurtAudio) to hurt,
            ("swim" to swimAudio) to swim,
            ("splash" to splashAudio) to splash
        ).forEach { (path, sound) ->
            addEntitySound(id, path.first, sound, path.second)
        }
    }

    fun addEntitySound(entityId: String, path: String, soundEvent: Supplier<SoundEvent>? = null, soundLocation: ResourceLocation? = null) {
        addSound("entity/${entityId}_$path", "entity.${entityId}.$path", soundEvent, soundLocation)
    }

    fun addSound(defaultSound: String, subtitle: String, soundEvent: Supplier<SoundEvent>? = null, soundLocation: ResourceLocation? = null) {
        if (soundEvent != null) {
            add(soundEvent, definition()
                .subtitle("sounds.${Constants.MOD_ID}.$subtitle")
                .with(
                    sound(soundLocation ?: CommonClass.locate(defaultSound),
                        if (soundLocation != null && soundLocation.namespace.equals("minecraft")) SoundDefinition.SoundType.EVENT
                        else SoundDefinition.SoundType.SOUND )
                )
            )
        }
    }
}